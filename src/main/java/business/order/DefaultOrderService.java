package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

    private BookDao bookDao;
    private OrderDao orderDao;
    private CustomerDao customerDao;
    private LineItemDao lineItemDao;
    private Random random = new Random();

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setLineItemDao(LineItemDao lineItemDao) {
        this.lineItemDao = lineItemDao;
    }

    @Override
    public OrderDetails getOrderDetails(long orderId) {
        Order order = orderDao.findByOrderId(orderId);
        Customer customer = customerDao.findByCustomerId(order.customerId());
        List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
        List<Book> books = lineItems.stream()
                .map(lineItem -> bookDao.findByBookId(lineItem.bookId()))
                .collect(Collectors.toList());
        return new OrderDetails(order, customer, lineItems, books);
    }

    @Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {
        validateCustomer(customerForm);
        validateCart(cart);

        try (Connection connection = JdbcUtils.getConnection()) {
            Date ccExpDate = getCardExpirationDate(
                    customerForm.getCcExpiryMonth(),
                    customerForm.getCcExpiryYear());
            return performPlaceOrderTransaction(
                    customerForm.getName(),
                    customerForm.getAddress(),
                    customerForm.getPhone(),
                    customerForm.getEmail(),
                    customerForm.getCcNumber(),
                    ccExpDate, cart, connection);
        } catch (SQLException e) {
            throw new BookstoreDbException("Error during close connection for customer order", e);
        }
    }

    private Date getCardExpirationDate(String monthString, String yearString) {
        try {
            int month = Integer.parseInt(monthString);
            int year = Integer.parseInt(yearString);
            YearMonth yearMonth = YearMonth.of(year, month);
            return Date.from(yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (NumberFormatException | DateTimeException e) {
            throw new ApiException.ValidationFailure("Invalid expiration date format.");
        }
    }

    private long performPlaceOrderTransaction(
            String name, String address, String phone,
            String email, String ccNumber, Date date,
            ShoppingCart cart, Connection connection) {
        try {
            connection.setAutoCommit(false);
            long customerId = customerDao.create(
                    connection, name, address, phone, email,
                    ccNumber, date);
            long customerOrderId = orderDao.create(
                    connection,
                    cart.getComputedSubtotal() + cart.getSurcharge(),
                    generateConfirmationNumber(), customerId);
            for (ShoppingCartItem item : cart.getItems()) {
                lineItemDao.create(connection,
                        item.getBookId(), customerOrderId, item.getQuantity());
            }
            connection.commit();
            return customerOrderId;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new BookstoreDbException("Failed to roll back transaction", e1);
            }
            return 0;
        }
    }

    private int generateConfirmationNumber() {
        return random.nextInt(999999999);
    }


    private void validateCustomer(CustomerForm customerForm) {

        String name = customerForm.getName();
        if (name == null || name.trim().isEmpty() || name.length() < 4 || name.length() > 45) {
            throw new ApiException.ValidationFailure("name", "Name must be between 4 and 45 characters."
                    + " Received: `" + name + "`");
        }

        String address = customerForm.getAddress();
        if (address == null || address.trim().isEmpty() || address.length() < 4 || address.length() > 45) {
            throw new ApiException.ValidationFailure("address", "Address must be between 4 and 45 characters."
                    + " Received: `" + address + "`");
        }

        String phone = customerForm.getPhone();
        if (!isMobilePhoneValid(phone)) {
            throw new ApiException.ValidationFailure("phone", "Phone number should be exactly 10 characters."
                    + " Received: `" + phone + "`");
        }

        String email = customerForm.getEmail();
        if (email == null || email.trim().isEmpty() || !isEmailValid(email)) {
            throw new ApiException.ValidationFailure("email", "Email should not contain spaces, should contain '@' and the last character should not end with '.'."
                    + " Received: `" + email + "`");
        }

        String ccNumber = customerForm.getCcNumber();
        if (!isCreditCardValid(ccNumber)) {
            throw new ApiException.ValidationFailure("ccNumber", "Credit card number should be between 14 and 16 characters." +
                    " Received: `" + ccNumber + "`");
        }

        if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
            throw new ApiException.ValidationFailure("Please enter a valid expiration date.");
        }
    }

    private boolean isMobilePhoneValid(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        String phoneAfter = phone.replaceAll("[()]", "").replaceAll("[- ]", "");
        return phoneAfter.length() == 10;
    }

    private boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        if (email.contains(" ")) {
            return false;
        }
        if (!email.contains("@")) {
            return false;
        }
        return !email.endsWith(".");
    }

    private boolean isCreditCardValid(String ccNumber) {
        if (ccNumber == null || ccNumber.trim().isEmpty()) {
            return false;
        }
        String ccNumberAfter = ccNumber.replaceAll("[- ]", "");
        return ccNumberAfter.length() >= 14 && ccNumberAfter.length() <= 16;
    }

    private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
        try {
            YearMonth expiry = YearMonth.of(Integer.parseInt(ccExpiryYear), Integer.parseInt(ccExpiryMonth));
            YearMonth now = YearMonth.now();
            return expiry.isBefore(now);
        } catch (DateTimeException | NumberFormatException e) {
            return true;
        }
    }

    private void validateCart(ShoppingCart cart) {

        if (cart.getItems().isEmpty()) {
            throw new ApiException.ValidationFailure("Cart is empty.");
        }

        cart.getItems().forEach(item -> {
            if (item.getQuantity() <= 0 || item.getQuantity() > 99) {
                throw new ApiException.ValidationFailure("quantity", "Quantity must be between 1 and 99."
                        + " Received: " + item.getQuantity());
            }

            Book databaseBook = bookDao.findByBookId(item.getBookId());
            if (databaseBook == null) {
                throw new ApiException.ValidationFailure("bookId", "Book ID not found: " + item.getBookId() + ".");
            }

            if (item.getBookForm().getPrice() != databaseBook.price()) {
                throw new ApiException.ValidationFailure("price", "Price mismatch for book ID " + item.getBookId() + "."
                        + " Expected: " + databaseBook.price() + ", Received: " + item.getBookForm().getPrice());
            }

            if (item.getBookForm().getCategoryId() != databaseBook.categoryId()) {
                throw new ApiException.ValidationFailure("categoryId", "Category mismatch for book ID " + item.getBookId() + ".");
            }

        });
    }

}

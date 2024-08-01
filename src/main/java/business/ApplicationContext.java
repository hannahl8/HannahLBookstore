
package business;

import business.book.BookDao;
import business.book.BookDaoJdbc;
import business.category.CategoryDao;
import business.category.CategoryDaoJdbc;
import business.customer.CustomerDao;
import business.customer.CustomerDaoJdbc;
import business.order.*;

public class ApplicationContext {

    private BookDao bookDao;
    private CategoryDao categoryDao;
    private OrderDao orderDao;
    private CustomerDao customerDao;
    private LineItemDao lineItemDao;
    private OrderService orderService;

    public static ApplicationContext INSTANCE = new ApplicationContext();

    private ApplicationContext() {
        bookDao = new BookDaoJdbc();
        orderDao = new OrderDaoJdbc();
        customerDao = new CustomerDaoJdbc();
        lineItemDao = new LineItemDaoJdbc();
        categoryDao = new CategoryDaoJdbc();
        orderService = new DefaultOrderService();
        ((DefaultOrderService) orderService).setBookDao(bookDao);
        ((DefaultOrderService) orderService).setCustomerDao(customerDao);
        ((DefaultOrderService) orderService).setOrderDao(orderDao);
        ((DefaultOrderService) orderService).setLineItemDao(lineItemDao);
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public LineItemDao getLineItemDao() {
        return lineItemDao;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}

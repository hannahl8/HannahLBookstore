
package business;

import business.book.BookDao;
import business.book.BookDaoJdbc;
import business.category.CategoryDao;
import business.category.CategoryDaoJdbc;
import business.order.DefaultOrderService;
import business.order.OrderService;

public class ApplicationContext {

    private BookDao bookDao;

    private CategoryDao categoryDao;

    private OrderService orderService;

    public static ApplicationContext INSTANCE = new ApplicationContext();

    private ApplicationContext() {
        bookDao = new BookDaoJdbc();
        categoryDao = new CategoryDaoJdbc();
        orderService = new DefaultOrderService();
        ((DefaultOrderService) orderService).setBookDao(bookDao);
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}

package services;

import com.google.inject.Inject;
import dao.Dao;
import dao.OrderDao;
import models.Order;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderService {
    
    private final Dao<Order> orderDao;

    @Inject
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    public List<Order> getAll() {
        return (List<Order>) orderDao.getAll();
    }
    public Order get(String id) {
        return orderDao.get(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Order> getMyOrders(String fieldName, String value) {
        return (List<Order>)  orderDao.search(fieldName,value);
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public void update(String id, Order order) throws  NoSuchElementException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        orderDao.update(id,order);
    }

    public void delete(String id) { orderDao.delete(id);}
}

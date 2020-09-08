package dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {
    Collection<T> getAll();
    Collection<T> search(String fieldName, String value);
    Optional<T> get(String id);
    void save(T t);
    void update(String id, T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    void delete(String id);
}

/*
db.menuitems.insertOne({"name": "english breakfast",  "price": 12.34, "qty":1,"restaurant": "Macdonald",    "image": "img/1.jpg",   "description": "english breakfast"})
db.menuitems.insertOne({"name": "pizza",              "price": 16.95, "qty":1,"restaurant": "Domino",       "image": "img/2.jpg",   "description": "pizza"})
db.menuitems.insertOne({"name": "hamburger",          "price": 15.45, "qty":1,"restaurant": "Macdonald",    "image": "img/3.jpg",   "description": "hamburger"})
db.menuitems.insertOne({"name": "Hash Brown",         "price": 5.45,  "qty":1,"restaurant": "Macdonald",    "image": "img/10.jpeg", "description": "hash brown"})
db.menuitems.insertOne({"name": "Special Beef Noodle","price": 12.95, "qty":1,"restaurant": "Ms Pho",       "image": "img/20.jpeg", "description": "special beef noodle"})
db.menuitems.insertOne({"name": "Rice Roll",          "price": 11.45, "qty":1,"restaurant": "Ms Pho",       "image": "img/21.jpeg", "description": "rice rool"})
db.menuitems.insertOne({"name": "Chicken Noodle",     "price": 13.45, "qty":1,"restaurant": "Ms Pho",       "image": "img/22.jpeg", "description": "chicken noodle"})
db.menuitems.insertOne({"name": "Crispy Chicken Rice","price": 14.45, "qty":1,"restaurant": "Ms Pho",       "image": "img/23.jpeg", "description": "crispy chicken rice"})

db.orders.insertOne({"orderdate": "2020-07-01", "subtotal": 59.24, "status": "Processing","username": "george", "orderlines": [{"id":"5f56ea5d9e7d4dc85e182680","name": "english breakfast","restaurant": "Macdonald","qty":1, "price": 12.34},{"id":"5f56ea5d9e7d4dc85e182681","name": "pizza","restaurant": "Domino","qty":2,"price":23.45}] })
db.orders.insertOne({"orderdate": "2020-07-02", "subtotal": 59.24, "status": "Delivered" ,"username": "ethan",  "orderlines": [{"id":1,"name": "english breakfast","restaurant": "Macdonald","qty":1, "price": 12.34},{"id":2,"name": "pizza","restaurant": "Domino","qty":2,"price":23.45}] })
db.orders.insertOne({"orderdate": "2020-07-03", "subtotal": 59.24, "status": "Processing","username": "george", "orderlines": [{"id":1,"name": "english breakfast","restaurant": "Macdonald","qty":1, "price": 12.34},{"id":5,"name": "special beef noodle","restaurant": "Ms Pho","qty":2,"price":23.45}] })
db.orders.insertOne({"orderdate": "2020-07-04", "subtotal": 37.24, "status": "Delivered" ,"username": "ethan",  "orderlines": [{"id":1,"name": "english breakfast","restaurant": "Macdonald","qty":1, "price": 12.34},{"id":6,"name": "rice rool","restaurant": "Ms Pho","qty":2,"price":12.45}] })

 */
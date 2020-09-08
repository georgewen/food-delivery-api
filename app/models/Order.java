package models;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.awt.*;
import java.util.Date;
import java.util.List;

@Entity("orders")
public class Order {

/*    @Embedded
    class OrderLine {

        private String id;
        private String name;
        private float price;
        private int qty;
        private String restaurant;
        //private String image;
        //private String description;
        public OrderLine(){};

        public OrderLine(String id, String name, float price, int qty, String restaurant) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.qty = qty;
            this.restaurant = restaurant;
        }
    }*/

    @Id
    private String ordernumber;
    private String orderdate;
    private float subtotal;
    private String status;
    private String username;
    private List<OrderLine> orderlines;

    public Order(){}

    public Order(String ordernumber, String orderdate, float subtotal, String status, String username, List<OrderLine> orderlines) {
        this.ordernumber = ordernumber;
        this.orderdate = orderdate;
        this.subtotal = subtotal;
        this.status = status;
        this.username = username;
        this.orderlines = orderlines;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderLine> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<OrderLine> orderlines) {
        this.orderlines = orderlines;
    }
}


/*
{
    OrderNumber:1,
    OrderDate: '2020-07-01',
    SubTotal: 59.24,
    Status: 'Processing',
    UserName: 'george',
    OrderLines: [
        {id:1,name: "english breakfast",restaurant: 'Macdonald',qty:1, price: 12.34},
        {id:2,name: "pizza",restaurant: 'Domino',qty:2,price:23.45}
    ]
},
 */

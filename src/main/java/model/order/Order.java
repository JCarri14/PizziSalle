package model.order;

import model.user.User;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private User customer;
    private String comments;
    private List<OrderItem> items;

    public Order(int id, User customer, String comments, List<OrderItem> items) {
        this.id = id;
        this.customer = customer;
        this.comments = comments;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}

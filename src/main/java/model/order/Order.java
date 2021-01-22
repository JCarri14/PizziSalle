package model.order;

import db.model.DBObject;
import model.delegation.Delegation;
import model.user.User;

import java.util.List;

public class Order {
    public static DBObject TYPE = DBObject.ORDER;
    private int id;
    private User customer;
    private Delegation delegation;
    private String comments;
    private List<OrderItem> items;

    public Order(int id, User customer, Delegation delegation, String comments, List<OrderItem> items) {
        this.id = id;
        this.customer = customer;
        this.delegation = delegation;
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

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
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

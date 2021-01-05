package builders.v1;

import model.delegation.Delegation;
import model.order.Order;
import model.order.OrderItem;
import model.user.User;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    private int id;
    private User customer;
    private Delegation delegation;
    private String comments;
    private List<OrderItem> items;

    public OrderBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public OrderBuilder withDelegation(Delegation delegation) {
        this.delegation = delegation;
        return this;
    }

    public OrderBuilder withComments(String comments) {
        if (this.comments != null) this.comments += ", " + comments;
        else this.comments = comments;
        return this;
    }

    public OrderBuilder withItems(List<OrderItem> items) {
        this.items = items;
        return this;
    }

    public OrderBuilder withItem(OrderItem item) {
        if (this.items == null) this.items = new ArrayList<>();
        this.items.add(item);
        return this;
    }

    public Order createOrder() {
        return new Order(id, customer, delegation, comments, items);
    }
}

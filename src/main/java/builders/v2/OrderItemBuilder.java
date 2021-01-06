package builders.v2;

import model.order.OrderItem;
import model.pizza.Drink;
import model.pizza.Mass;
import model.pizza.Pizza;

import java.util.function.Consumer;

public class OrderItemBuilder {
    public int id;
    public int quantity;
    public Pizza pizza;
    public Drink drink;
    public Mass mass;

    public OrderItemBuilder with(Consumer<OrderItemBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public OrderItem createOrderItem() {
        return new OrderItem(id, quantity, pizza, drink, mass);
    }
}

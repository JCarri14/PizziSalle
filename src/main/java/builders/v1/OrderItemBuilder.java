package builders.v1;

import model.order.OrderItem;
import model.pizza.Drink;
import model.pizza.Mass;
import model.pizza.Pizza;

public class OrderItemBuilder {
    private int id;
    private int quantity;
    private Pizza pizza;
    private Drink drink;
    private Mass mass;

    public OrderItemBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public OrderItemBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderItemBuilder withPizza(Pizza pizza) {
        this.pizza = pizza;
        return this;
    }

    public OrderItemBuilder withDrink(Drink drink) {
        this.drink = drink;
        return this;
    }

    public OrderItemBuilder withMass(Mass mass) {
        this.mass = mass;
        return this;
    }

    public OrderItem createOrderItem() {
        return new OrderItem(id, quantity, pizza, drink, mass);
    }
}

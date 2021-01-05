package model.order;

import model.pizza.Drink;
import model.pizza.Mass;
import model.pizza.Pizza;

public class OrderItem {
    private int id;
    private int quantity;
    private Pizza pizza;
    private Drink drink;
    private Mass mass;

    public OrderItem(int id, int quantity, Pizza pizza) {
        this.id = id;
        this.quantity = quantity;
        this.pizza = pizza;
    }

    public OrderItem(int id, int quantity, Drink drink) {
        this.id = id;
        this.quantity = quantity;
        this.drink = drink;
    }

    public OrderItem(int id, int quantity, Mass mass) {
        this.id = id;
        this.quantity = quantity;
        this.mass = mass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Mass getMass() {
        return mass;
    }

    public void setMass(Mass mass) {
        this.mass = mass;
    }
}

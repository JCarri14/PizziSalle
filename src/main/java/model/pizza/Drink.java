package model.pizza;

import db.enums.DBObject;
import model.order.OrderItem;
import model.order.OrderType;

public class Drink {
    public final static DBObject type = DBObject.DRINK;
    private int id;
    private String name;

    public Drink(int id) {
        this.id = id;
    }

    public Drink(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

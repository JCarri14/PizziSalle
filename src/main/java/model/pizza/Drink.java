package model.pizza;

import db.model.DBObject;

public class Drink {
    public static DBObject TYPE = DBObject.DRINK;
    private int id;
    private String name;

    public Drink(int id) {
        this.id = id;
    }

    public Drink(String name) {
        this.name = name;
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

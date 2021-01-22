package model.pizza;

import db.model.DBObject;

public class Ingredient {
    public static DBObject TYPE = DBObject.INGREDIENT;
    private int id;
    private String name;
    private String description;

    public Ingredient(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Ingredient(String name) {
        this.id = -1;
        this.name = name;
        this.description = null;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

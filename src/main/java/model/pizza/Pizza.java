package model.pizza;

import model.pizza.Ingredient;
import model.order.OrderItem;
import model.order.OrderType;

import java.util.List;

public class Pizza {
    private int id;
    private String name;
    private List<PizzaItem> ingredients;
    private List<PizzaItem> extras;
    private Mass mass;

    public Pizza(int id, String name, List<PizzaItem> ingredients, List<PizzaItem> extras, Mass mass) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.extras = extras;
        this.mass = mass;
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

    public List<PizzaItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<PizzaItem> ingredients) {
        this.ingredients = ingredients;
    }

    public List<PizzaItem> getExtras() {
        return extras;
    }

    public void setExtras(List<PizzaItem> extras) {
        this.extras = extras;
    }

    public Mass getMass() {
        return mass;
    }

    public void setMass(Mass mass) {
        this.mass = mass;
    }
}

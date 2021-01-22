package model.pizza;

import db.model.DBObject;

public class PizzaItem {
    public static DBObject TYPE = DBObject.PIZZA_ITEM;
    private Ingredient ingredient;
    private int quantity;

    public PizzaItem(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

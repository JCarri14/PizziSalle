package builders.v1;

import model.pizza.Ingredient;
import model.pizza.Mass;
import model.pizza.Pizza;
import model.pizza.PizzaItem;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private int id;
    private String name;
    private List<PizzaItem> ingredients;
    private List<PizzaItem> extras;
    private Mass mass;

    public PizzaBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public PizzaBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PizzaBuilder withIngredients(List<PizzaItem> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public PizzaBuilder withIngredient(PizzaItem ingredient) {
        if (this.ingredients == null) this.ingredients = new ArrayList<>();
        this.ingredients.add(ingredient);
        return this;
    }

    public PizzaBuilder withExtras(List<PizzaItem> extras) {
        this.extras = extras;
        return this;
    }

    public PizzaBuilder withExtra(PizzaItem extra) {
        if (this.extras == null) this.extras = new ArrayList<>();
        this.extras.add(extra);
        return this;
    }

    public PizzaBuilder withMass(Mass mass) {
        this.mass = mass;
        return this;
    }

    public Pizza createPizza() {
        return new Pizza(id, name, ingredients, extras, mass);
    }
}

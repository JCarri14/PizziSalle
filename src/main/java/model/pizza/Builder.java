package model.pizza;

public interface Builder {
    void setBasePizza(Pizza pizza);
    void setType();
    void setMassType();
    void addIngredient(Ingredient ingredient);
    void addExtra(Ingredient extra);
    Pizza build();
}

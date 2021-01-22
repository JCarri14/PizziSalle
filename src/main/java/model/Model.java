package model;


import db.DBConnector;
import db.model.DBObject;
import db.mysql.MySQLManagerFactory;
import db.repositories.DrinkRepository;
import db.repositories.IngredientRepository;
import db.repositories.MassRepository;
import db.repositories.PizzaRepository;
import model.delegation.Delegation;
import model.pizza.Drink;
import model.pizza.Ingredient;
import model.pizza.Mass;
import model.pizza.Pizza;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/* Observer */
public class Model implements PropertyChangeListener{

    private List<Pizza> pizzas;
    private List<Ingredient> ingredients;
    private List<Mass> massas;
    private List<Drink> drinks;
    private List<Delegation> delegations;

    public Model() {
        this.pizzas = new ArrayList<>();
        this.ingredients = new ArrayList<>();
        this.massas = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.delegations = new ArrayList<>();
        startObserving();
    }

    public Model(List<Pizza> pizzas, List<Ingredient> ingredients, List<Mass> massas, List<Drink> drinks) {
        this.pizzas = pizzas;
        this.ingredients = ingredients;
        this.massas = massas;
        this.drinks = drinks;
        startObserving();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case "pizzas":
                setPizzas((List<Pizza>) event.getNewValue());
                break;
            case "ingredients":
                setIngredients((List<Ingredient>) event.getNewValue());
                break;
            case "masses":
                setMassas((List<Mass>) event.getNewValue());
                break;
            case "drinks":
                setDrinks((List<Drink>) event.getNewValue());
                break;
            case "delegations":
                setDelegations((List<Delegation>) event.getNewValue());
        }
    }

    public void startObserving() {
        PizzaRepository.getInstance().addObserver(this);
        IngredientRepository.getInstance().addObserver(this);
        MassRepository.getInstance().addObserver(this);
        DrinkRepository.getInstance().addObserver(this);
    }

    public void stopObserving() {
        PizzaRepository.getInstance().removeObserver(this);
        IngredientRepository.getInstance().removeObserver(this);
        MassRepository.getInstance().removeObserver(this);
        DrinkRepository.getInstance().removeObserver(this);
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Mass> getMassas() {
        return massas;
    }

    public void setMassas(List<Mass> massas) {
        this.massas = massas;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Delegation> getDelegations() {
        return delegations;
    }

    public void setDelegations(List<Delegation> delegations) {
        this.delegations = delegations;
    }
}

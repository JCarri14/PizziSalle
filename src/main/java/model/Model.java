package model;


import controller.SessionContext;
import db.enums.DBObject;
import db.enums.DBType;
import db.mysql.MySQLManagerFactory;
import model.pizza.Drink;
import model.pizza.Ingredient;
import model.pizza.Mass;
import model.pizza.Pizza;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/* Observer */
public class Model implements PropertyChangeListener{

    private List<Pizza> pizzas;
    private List<Ingredient> ingredients;
    private List<Mass> massas;
    private List<Drink> drinks;

    public Model() {
        this.pizzas = new ArrayList<>();
        this.ingredients = new ArrayList<>();
        this.massas = new ArrayList<>();
        this.drinks = new ArrayList<>();
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
            case "massas":
                setMassas((List<Mass>) event.getNewValue());
                break;
            case "drinks":
                setDrinks((List<Drink>) event.getNewValue());
                break;
        }
    }

    public void startObserving() {
        //Could add some logic to control dbType ObjectManagerFactory
        //DBType dbType = SessionContext.getInstance().getDbType();

        // **ATENTION**
        // It is assumed **ALL** Object Managers have already been initialised
        MySQLManagerFactory.get(DBObject.PIZZA,null).addObserver(this);
        MySQLManagerFactory.get(DBObject.INGREDIENT,null).addObserver(this);
        MySQLManagerFactory.get(DBObject.MASS,null).addObserver(this);
        MySQLManagerFactory.get(DBObject.DRINK,null).addObserver(this);
    }

    public void stopObserving() {
        MySQLManagerFactory.get(DBObject.PIZZA,null).removeObserver(this);
        MySQLManagerFactory.get(DBObject.INGREDIENT,null).removeObserver(this);
        MySQLManagerFactory.get(DBObject.MASS,null).removeObserver(this);
        MySQLManagerFactory.get(DBObject.DRINK,null).removeObserver(this);
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
}

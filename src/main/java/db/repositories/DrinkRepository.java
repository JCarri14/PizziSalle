package db.repositories;

import db.DBConnector;
import db.callbacks.DBCallback;
import db.model.DBResponse;
import model.pizza.Drink;
import model.pizza.Ingredient;
import model.pizza.Pizza;

import java.util.ArrayList;
import java.util.List;

public class DrinkRepository extends BaseRepository<Drink> implements DBCallback {
    private static DrinkRepository instance;

    private List<Drink> drinks;

    private DrinkRepository() {
        drinks = new ArrayList<>();
    }

    public static DrinkRepository getInstance() {
        if (instance == null) {
            synchronized (DrinkRepository.class) {
                if (instance == null) {
                    instance = new DrinkRepository();
                }
            }
        }
        return instance;
    }

    public Drink getDrinkByName(DBConnector dbConnector, String name) {
        return new GetByNameAsync(dbConnector, Drink.TYPE, this).execute(name);
    }

    public List<Drink> getAllDrinks(DBConnector dbConnector) {
        return new GetAllAsync(dbConnector, Drink.TYPE, this).execute();
    }

    public void insertDrink(DBConnector dbConnector, Drink drink) {
        new InsertOneAsync(dbConnector, Drink.TYPE, this).execute(drink);
    }

    public void deleteDrinkById(DBConnector dbConnector, int drinkId) {
        new DeleteByIdAsync(dbConnector, Drink.TYPE, this).execute(drinkId);
    }

    public void deleteDrinkByName(DBConnector dbConnector, String name) {
        new DeleteByNameAsync(dbConnector, Drink.TYPE, this).execute(name);
    }

    @Override
    public void onResponse(DBResponse DBResponse) {
        if (DBResponse.body() instanceof List) {
            support.firePropertyChange("drinks", this.drinks, DBResponse.body());
            this.drinks = (List<Drink>) DBResponse.body();
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }
}

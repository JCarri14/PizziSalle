package db.repositories;

import db.DBConnector;
import model.pizza.Drink;
import model.pizza.Ingredient;

import java.util.List;

public class DrinkRepository extends BaseRepository<Drink> {
    private static DrinkRepository instance;

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
        return new GetByNameAsync(dbConnector, Drink.type).execute(name);
    }

    public List<Drink> getAllDrinks(DBConnector dbConnector) {
        return new GetAllAsync(dbConnector, Drink.type).execute();
    }

    public void insertDrink(DBConnector dbConnector, Drink drink) {
        new InsertOneAsync(dbConnector, Drink.type).execute(drink);
    }

    public void deleteDrinkById(DBConnector dbConnector, int drinkId) {
        new DeleteByIdAsync(dbConnector, Drink.type).execute(drinkId);
    }

    public void deleteDrinkByName(DBConnector dbConnector, String name) {
        new DeleteByNameAsync(dbConnector, Drink.type).execute(name);
    }

}

package db.repositories;

import db.DBConnector;
import model.pizza.Ingredient;

import java.util.List;

public class IngredientRepository extends BaseRepository<Ingredient> {
    private static IngredientRepository instance;

    public static IngredientRepository getInstance() {
        if (instance == null) {
            synchronized (IngredientRepository.class) {
                if (instance == null) {
                    instance = new IngredientRepository();
                }
            }
        }
        return instance;
    }

    public Ingredient getIngredientByName(DBConnector dbConnector, String name) {
        return new GetByNameAsync(dbConnector, Ingredient.type).execute(name);
    }

    public List<Ingredient> getAllIngredients(DBConnector dbConnector) {
        return new GetAllAsync(dbConnector, Ingredient.type).execute();
    }

    public void insertIngredient(DBConnector dbConnector, Ingredient ingredient) {
        new InsertOneAsync(dbConnector, Ingredient.type).execute(ingredient);
    }

    public void deleteIngredientById(DBConnector dbConnector, int ingredientId) {
        new DeleteByIdAsync(dbConnector, Ingredient.type).execute(ingredientId);
    }

    public void deleteIngredientByName(DBConnector dbConnector, String name) {
        new DeleteByNameAsync(dbConnector, Ingredient.type).execute(name);
    }

}

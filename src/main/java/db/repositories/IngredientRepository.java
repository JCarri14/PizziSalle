package db.repositories;

import db.DBConnector;
import db.callbacks.DBCallback;
import db.model.DBResponse;
import model.pizza.Ingredient;
import model.pizza.Pizza;

import java.util.ArrayList;
import java.util.List;

public class IngredientRepository extends BaseRepository<Ingredient> implements DBCallback {
    private static IngredientRepository instance;

    private List<Ingredient> ingredients;

    private IngredientRepository() {
        this.ingredients = new ArrayList<>();
    }

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
        return new GetByNameAsync(dbConnector, Ingredient.TYPE, this).execute(name);
    }

    public List<Ingredient> getAllIngredients(DBConnector dbConnector) {
        return new GetAllAsync(dbConnector, Ingredient.TYPE, this).execute();
    }

    public void insertIngredient(DBConnector dbConnector, Ingredient ingredient) {
        new InsertOneAsync(dbConnector, Ingredient.TYPE, this).execute(ingredient);
    }

    public void deleteIngredientById(DBConnector dbConnector, int ingredientId) {
        new DeleteByIdAsync(dbConnector, Ingredient.TYPE, this).execute(ingredientId);
    }

    public void deleteIngredientByName(DBConnector dbConnector, String name) {
        new DeleteByNameAsync(dbConnector, Ingredient.TYPE, this).execute(name);
    }

    @Override
    public void onResponse(DBResponse DBResponse) {
        if (DBResponse.body() instanceof List) {
            support.firePropertyChange("ingredients", this.ingredients, DBResponse.body());
            this.ingredients = (List<Ingredient>) DBResponse.body();
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }
}

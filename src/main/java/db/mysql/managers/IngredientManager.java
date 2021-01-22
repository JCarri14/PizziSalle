package db.mysql.managers;

import db.managers.MySQLEntityManager;
import db.model.DBObject;
import model.pizza.Ingredient;

import java.sql.Connection;

public class IngredientManager extends MySQLEntityManager<Ingredient> {
    private static IngredientManager instance;

    protected IngredientManager(Connection conn) {
        super(conn, DBObject.INGREDIENT);
    }

    public static IngredientManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (IngredientManager.class) {
                if (instance == null) {
                    instance = new IngredientManager(conn);
                }
            }
        }
        return instance;
    }


    @Override
    public String getTable() {
        return "Ingredient";
    }
}

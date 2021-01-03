package db.mysql.managers;

import db.interfaces.DBCallback;
import db.managers.DBObjectManager;
import db.managers.MySQLManager;
import model.pizza.Ingredient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLIngredientManager extends MySQLManager {
    private static MySQLIngredientManager instance;

    protected MySQLIngredientManager(Connection conn) {
        super(conn);
    }

    public static MySQLIngredientManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLIngredientManager.class) {
                if (instance == null) {
                    instance = new MySQLIngredientManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public void get(Map<String, String> filters, DBCallback callback) throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        query = "SELECT * FROM Ingredient";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            //Ingredient i = new Ingredient(rs.getString("name"));
            //ingredients.add(i);
        }
    }

    @Override
    public void post(Object element, DBCallback callback) {

    }

    @Override
    public void delete(int elementId, DBCallback callback) {

    }

    @Override
    public void update(Object element, DBCallback callback) {

    }
}

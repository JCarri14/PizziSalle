package db.mysql.managers;

import db.callbacks.DBCallback;
import db.managers.MySQLManager;
import model.pizza.Drink;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLDrinkManager extends MySQLManager {
    private static MySQLDrinkManager instance;

    protected MySQLDrinkManager(Connection conn) {
        super(conn);
    }

    public static MySQLDrinkManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLDrinkManager.class) {
                if (instance == null) {
                    instance = new MySQLDrinkManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public void get(Map<String, String> filters, DBCallback callback) throws SQLException {

    }

    @Override
    public void getAll(DBCallback callback) throws SQLException {
        List<Drink> drinks = new ArrayList<>();
        query = "SELECT * FROM Drink";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            //Drink d = new Drink(rs.getInt("id_drink"), rs.getString("name"));
            //drinks.add(d);
        }
        callback.onSuccess((Map<String, Object>) new HashMap<>().put("content", drinks));
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

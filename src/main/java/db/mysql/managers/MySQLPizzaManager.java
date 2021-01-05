package db.mysql.managers;

import db.callbacks.DBCallback;
import db.managers.MySQLManager;
import model.pizza.Pizza;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLPizzaManager extends MySQLManager {
    private static MySQLPizzaManager instance;
    protected List<Pizza> pizzas;

    protected MySQLPizzaManager(Connection conn) {
        super(conn);
    }

    public static MySQLPizzaManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLPizzaManager.class) {
                if (instance == null) {
                    instance = new MySQLPizzaManager(conn);
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
        List<Pizza> pizzas = new ArrayList<>();
        query = "SELECT * FROM Pizza";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            //Pizza p = new Pizza(rs.getInt("id_pizza"),rs.getString("name"));
            //pizzas.add(p);
        }
        callback.onSuccess((Map<String, Object>) new HashMap<>().put("content", pizzas));
    }

    @Override
    public void post(Object element, DBCallback callback) {

    }

    @Override
    public void update(Object element, DBCallback callback) {

    }

    @Override
    public void delete(int elementId, DBCallback callback) {

    }


}

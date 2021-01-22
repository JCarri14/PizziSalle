package db.mysql.managers;

import db.callbacks.DBCallback;
import db.managers.MySQLEntityManager;
import db.mappers.PizzaMapper;
import db.model.DBObject;
import model.pizza.Pizza;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PizzaManager extends MySQLEntityManager<Pizza> {
    private static PizzaManager instance;

    protected PizzaManager(Connection conn) {
        super(conn, DBObject.PIZZA);
    }

    public static PizzaManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (PizzaManager.class) {
                if (instance == null) {
                    instance = new PizzaManager(conn);
                }
            }
        }
        return instance;
    }


    @Override
    public String getTable() {
        return "Pizza";
    }
}

package db.mysql.managers;

import db.managers.MySQLEntityManager;
import db.model.DBObject;
import model.pizza.Drink;

import java.sql.Connection;

public class DrinkManager extends MySQLEntityManager<Drink> {
    private static DrinkManager instance;

    protected DrinkManager(Connection conn) {
        super(conn, DBObject.DRINK);
    }

    public static DrinkManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (DrinkManager.class) {
                if (instance == null) {
                    instance = new DrinkManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public String getTable() {
        return "Drink";
    }
}

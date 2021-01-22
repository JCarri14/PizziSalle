package db.mysql.managers;

import db.managers.MySQLEntityManager;
import db.model.DBObject;
import model.pizza.Mass;

import java.sql.Connection;

public class MassManager extends MySQLEntityManager<Mass> {
    private static MassManager instance;

    protected MassManager(Connection conn) {
        super(conn, DBObject.MASS);
    }

    public static MassManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MassManager.class) {
                if (instance == null) {
                    instance = new MassManager(conn);
                }
            }
        }
        return instance;
    }


    @Override
    public String getTable() {
        return "Mass";
    }
}

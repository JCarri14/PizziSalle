package db.mysql.managers;

import db.interfaces.DBCallback;
import db.managers.DBObjectManager;
import db.managers.MySQLManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class MySQLOrderManager extends MySQLManager {
    private static MySQLOrderManager instance;

    protected MySQLOrderManager(Connection conn) {
        super(conn);
    }

    public static MySQLOrderManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLOrderManager.class) {
                if (instance == null) {
                    instance = new MySQLOrderManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public void get(Map<String, String> filters, DBCallback callback) {

    }

    @Override
    public void getAll(DBCallback callback) throws SQLException {

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

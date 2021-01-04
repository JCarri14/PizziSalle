package db.mysql.managers;

import db.interfaces.DBCallback;
import db.managers.MySQLManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class MySQLMassManager extends MySQLManager {
    private static MySQLMassManager instance;

    protected MySQLMassManager(Connection conn) {
        super(conn);
    }

    public static MySQLMassManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLMassManager.class) {
                if (instance == null) {
                    instance = new MySQLMassManager(conn);
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

    }

    @Override
    public void post(Object element, DBCallback callback) throws SQLException {

    }

    @Override
    public void update(Object element, DBCallback callback) throws SQLException {

    }

    @Override
    public void delete(int elementId, DBCallback callback) throws SQLException {

    }
}

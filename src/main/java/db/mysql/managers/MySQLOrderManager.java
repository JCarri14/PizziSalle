package db.mysql.managers;

import db.callbacks.DBCallback;
import db.managers.MySQLManager;
import model.order.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MySQLOrderManager extends MySQLManager<Order> {
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
    public List<Order> get(Map<String, String> filters) throws SQLException {
        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public int insert(Order element) throws SQLException {
        return 0;
    }

    @Override
    public boolean update(Order element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Map<String, String> filters) throws SQLException {
        return false;
    }
}

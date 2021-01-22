package db.mysql.managers;

import db.managers.MySQLEntityManager;
import db.model.DBObject;
import model.order.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderManager extends MySQLEntityManager<Order> {
    private static OrderManager instance;

    protected OrderManager(Connection conn) {
        super(conn, DBObject.ORDER);
    }

    public static OrderManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (OrderManager.class) {
                if (instance == null) {
                    instance = new OrderManager(conn);
                }
            }
        }
        return instance;
    }


    @Override
    public String getTable() {
        return "Order";
    }
}

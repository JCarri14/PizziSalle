package db.mysql.managers;

import db.callbacks.DBCallback;
import db.managers.MySQLEntityManager;
import db.mappers.EntityMapper;
import db.mappers.MapperFactory;
import db.mappers.OrderItemMapper;
import db.mappers.OrderMapper;
import db.model.*;
import model.order.Order;
import model.order.OrderItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderItemManager extends MySQLEntityManager<Order> {
    private static OrderItemManager instance;

    protected OrderItemManager(Connection conn) {
        super(conn, DBObject.ORDER_ITEM);
    }

    public static OrderItemManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (OrderItemManager.class) {
                if (instance == null) {
                    instance = new OrderItemManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public String getTable() {
        return "OrderItem";
    }

    public void insertOrderItem(OrderItem element, Integer parentId, DBCallback dbCallback) {
        try {
            OrderItemMapper mapper = new OrderItemMapper(DBType.MYSQL);
            String query = "";
            try {
                query = mapper.MySQLInsert(element, parentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stt = conn.createStatement();
            stt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            rs = stt.getGeneratedKeys();

            DBResponse<Integer> DBResponse = new DBResponse<>();

            if (rs.next()) {
                DBResponse.setCode(DBResponseCode.CREATED);
                DBResponse.setBody(rs.getInt(1));
                if (dbCallback != null) dbCallback.onResponse(DBResponse);
            } else {
                DBResponse.setCode(DBResponseCode.BAD_REQUEST);
                if (dbCallback != null) dbCallback.onResponse(DBResponse);
            }
        } catch (SQLException throwable) {
            if (dbCallback != null) dbCallback.onFailure(throwable);
        }
    }

}

package db.repositories;

import db.DBConnector;
import db.managers.DBObjectManager;
import model.order.Order;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    private static OrderRepository instance;

    public static OrderRepository getInstance() {
        if (instance == null) {
            synchronized (OrderRepository.class) {
                if (instance == null) {
                    instance = new OrderRepository();
                }
            }
        }
        return instance;
    }

    public Order getOrderById(DBConnector dbConnector, int orderId) {
        return new GetOrderByIdAsync(dbConnector).execute(orderId);
    }

    public void insertOrder(DBConnector dbConnector, Order order) {

    }

    private static class GetOrderByIdAsync extends AsyncTask<Integer, Order> {

        public GetOrderByIdAsync(DBConnector dbConnector) {
            super(dbConnector);
        }

        @Override
        public Order doInBackground(Integer... integers) {
            try {
                DBObjectManager manager = getManager(dbConnector, Order.type);
                Map<String, String> filters = new HashMap<>();
                filters.put("id", String.valueOf(integers[0]));
                manager.get(filters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            while (this.waitResponse) {}
            return this.response;
        }
    }
}

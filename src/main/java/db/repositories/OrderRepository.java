package db.repositories;

import db.DBConnector;
import db.callbacks.DBCallback;
import db.criteria.Criteria;
import db.interfaces.DBEntityManager;
import db.model.DBFilter;
import db.model.DBObject;
import db.model.DBResponse;
import db.model.DBType;
import model.order.Order;
import model.pizza.Pizza;

import java.sql.SQLException;
import java.util.*;

public class OrderRepository extends BaseRepository<Order> implements DBCallback {
    private static OrderRepository instance;

    private List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

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

    public void getOrderById(DBConnector dbConnector, int orderId) {
        new GetOrderByIdAsync(dbConnector).execute(orderId);
    }

    public void insertOrder(DBConnector dbConnector, Order order, final DBCallback dbCallback) {
        new InsertOneAsync(dbConnector, DBObject.ORDER, Objects.requireNonNullElse(dbCallback, this)).execute(order);
    }

    @Override
    public void onResponse(DBResponse DBResponse) {
        if (DBResponse.body() instanceof List) {
            this.orders = (List<Order>) DBResponse.body();
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }

    private static class GetOrderByIdAsync extends AsyncTask<Integer, Order> {

        public GetOrderByIdAsync(DBConnector dbConnector) {
            super(dbConnector);
        }

        @Override
        public Order doInBackground(Integer... integers) {
            DBEntityManager manager = getManager(dbConnector, Order.TYPE);
            Criteria criteria = new Criteria(DBType.MYSQL);
            criteria.addFilter(DBFilter.ID, String.valueOf(integers[0]));
            manager.get(criteria, null);
            while (this.waitResponse) {}
            return null;
        }
    }

    private class InsertOrderAsync extends AsyncTask<Order,Integer> {

        private DBObject object;
        private DBCallback dbCallback;

        public InsertOrderAsync(DBConnector dbConnector, DBObject object, DBCallback dbCallback) {
            super(dbConnector);
            this.object = object;
            this.dbCallback = dbCallback;
        }

        @Override
        public Integer doInBackground(Order... orders) {
            DBEntityManager manager = getManager(dbConnector, object);
            manager.insert(orders[0], null, dbCallback);
            return 0;
        }
    }

}

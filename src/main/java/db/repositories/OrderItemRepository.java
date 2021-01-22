package db.repositories;

import db.DBConnector;
import db.callbacks.DBCallback;
import db.interfaces.DBEntityManager;
import db.model.DBObject;
import db.mysql.managers.OrderItemManager;
import model.order.Order;
import model.order.OrderItem;
import model.pizza.Mass;

import java.util.List;

public class OrderItemRepository extends BaseRepository<OrderItem> {
    private static OrderItemRepository instance;

    public static OrderItemRepository getInstance() {
        if (instance == null) {
            synchronized (OrderItemRepository.class) {
                if (instance == null) {
                    instance = new OrderItemRepository();
                }
            }
        }
        return instance;
    }

    public void insertOrderItem(DBConnector dbConnector, OrderItem orderItem, final DBCallback dbCallback) {
        new InsertOneAsync(dbConnector, DBObject.ORDER_ITEM, dbCallback).execute(orderItem);
    }

    public void insertOrderItems(DBConnector dbConnector, List<OrderItem> items, int parentID, final DBCallback dbCallback) {
        for(OrderItem o: items) {
            new InsertOrderItemAsync(dbConnector, DBObject.ORDER_ITEM, parentID, dbCallback).execute(o);
        }
    }

    protected class InsertOrderItemAsync extends AsyncTask<OrderItem,Integer> {

        private DBObject object;
        private int parentID;
        private DBCallback dbCallback;

        public InsertOrderItemAsync(DBConnector dbConnector, DBObject object, int parentID, DBCallback dbCallback) {
            super(dbConnector);
            this.object = object;
            this.parentID = parentID;
            this.dbCallback = dbCallback;
        }

        @Override
        public Integer doInBackground(OrderItem... params) {
            OrderItemManager manager = (OrderItemManager) getManager(dbConnector, object);
            manager.insertOrderItem(params[0], parentID, dbCallback);
            return 0;
        }
    }

}

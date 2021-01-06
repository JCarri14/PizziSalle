package db.repositories;

import db.DBConnector;
import model.pizza.Mass;

import java.util.List;

public class OrderItemRepository extends BaseRepository<Mass> {
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

}

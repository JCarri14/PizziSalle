package db.repositories;

import db.DBConnector;
import db.enums.DBObject;
import db.managers.DBObjectManager;
import model.pizza.Mass;
import model.pizza.Pizza;
import model.pizza.PizzaItem;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaItemRepository extends BaseRepository<Mass> {
    private static PizzaItemRepository instance;

    public static PizzaItemRepository getInstance() {
        if (instance == null) {
            synchronized (PizzaItemRepository.class) {
                if (instance == null) {
                    instance = new PizzaItemRepository();
                }
            }
        }
        return instance;
    }

    public List<PizzaItem> getItemsByPizzaId(DBConnector dbConnector, int pizzaId) {
        return new GetItemsByPizzaIdAsync(dbConnector).execute(pizzaId);
    }

    public List<PizzaItem> getItemsByPizzaName(DBConnector dbConnector, String name) {
        // to be implemented...
        return null;
    }

    public void insertItemToPizza(DBConnector dbConnector, PizzaItem item, int pizzaId) {
        new InsertItemToPizzaAsync(dbConnector).execute();
    }

    private class GetItemsByPizzaIdAsync extends AsyncTask<Integer, List<PizzaItem>> {

        public GetItemsByPizzaIdAsync(DBConnector dbConnector) {
            super(dbConnector);
        }

        @Override
        public List<PizzaItem> doInBackground(Integer... integers) {
            try {
                DBObjectManager manager = getManager(dbConnector, PizzaItem.type);
                Map<String, String> filters = new HashMap<>();
                filters.put("id_pizza", String.valueOf(integers[0]));
                return manager.get(filters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }

    private class InsertItemToPizzaAsync extends AsyncTask<Integer, Void> {

        public InsertItemToPizzaAsync(DBConnector dbConnector) {
            super(dbConnector);
        }

        @Override
        public Void doInBackground(Integer... integers) {
            try {
                DBObjectManager manager = getManager(dbConnector, PizzaItem.type);
                Map<String, String> filters = new HashMap<>();
                filters.put("id_pizza", String.valueOf(integers[0]));
                return manager.get(filters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }


}

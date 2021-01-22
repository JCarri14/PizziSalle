package db.repositories;

import db.DBConnector;
import db.criteria.Criteria;
import db.interfaces.DBEntityManager;
import db.model.DBFilter;
import db.model.DBType;
import model.pizza.Mass;
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
        new InsertItemToPizzaAsync(dbConnector, pizzaId).execute(item);
    }

    private class GetItemsByPizzaIdAsync extends AsyncTask<Integer, List<PizzaItem>> {

        public GetItemsByPizzaIdAsync(DBConnector dbConnector) {
            super(dbConnector);
        }

        @Override
        public List<PizzaItem> doInBackground(Integer... integers) {
            DBEntityManager manager = getManager(dbConnector, PizzaItem.TYPE);
            Criteria criteria = new Criteria(DBType.MYSQL);
            criteria.addFilter(DBFilter.ID_PIZZA, String.valueOf(integers[0]));
            manager.get(criteria, null);
            return null;
        }
    }

    private class InsertItemToPizzaAsync extends AsyncTask<PizzaItem, Integer> {

        private int pizzaId;

        public InsertItemToPizzaAsync(DBConnector dbConnector, int pizzaId) {
            super(dbConnector);
            this.pizzaId = pizzaId;
        }

        @Override
        public Integer doInBackground(PizzaItem... items) {
            DBEntityManager manager = getManager(dbConnector, PizzaItem.TYPE);
            manager.insert(items[0], pizzaId, null);
            return null;
        }
    }


}

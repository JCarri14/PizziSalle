package db.repositories;

import db.DBConnector;
import db.managers.DBObjectManager;
import model.pizza.Pizza;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaRepository extends BaseRepository<Pizza> {
    private static PizzaRepository instance;

    public static PizzaRepository getInstance() {
        if (instance == null) {
            synchronized (PizzaRepository.class) {
                if (instance == null) {
                    instance = new PizzaRepository();
                }
            }
        }
        return instance;
    }

    public Pizza getPizzaById(DBConnector dbConnector, int id, boolean withFullInfo) {
        if (!withFullInfo) return new GetByIdAsync(dbConnector, Pizza.type).execute(id);
        return new GetFullPizzaByIdAsync(dbConnector).execute(id);
    }

    public Pizza getPizzaByName(DBConnector dbConnector, String name, boolean withFullInfo) {
        if (!withFullInfo) return new GetByNameAsync(dbConnector, Pizza.type).execute(name);
        return null;
    }

    public List<Pizza> getAllPizzas(DBConnector dbConnector, boolean withFullInfo) {
        if (!withFullInfo) return new GetAllAsync(dbConnector, Pizza.type).execute();
        return null;
    }

    public void insertPizza(DBConnector dbConnector, Pizza pizza) {

    }

    public void deletePizzaById(DBConnector dbConnector, int pizzaId) {
        new DeleteByIdAsync(dbConnector, Pizza.type).execute(pizzaId);
    }

    public void deletePizzaByName(DBConnector dbConnector, String name) {
        new DeleteByNameAsync(dbConnector, Pizza.type).execute(name);
    }

    private class GetFullPizzaByIdAsync extends AsyncTask<Integer, Pizza> {

        public GetFullPizzaByIdAsync(DBConnector dbConnector) {
            super(dbConnector);
        }

        @Override
        public Pizza doInBackground(Integer... integers) {
            try {
                DBObjectManager manager = getManager(dbConnector, Pizza.type);
                Map<String, String> filters = new HashMap<>();
                filters.put("id", String.valueOf(integers[0]));
                return (Pizza) manager.get(filters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }


}

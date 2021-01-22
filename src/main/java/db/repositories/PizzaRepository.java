package db.repositories;

import db.DBConnector;
import db.callbacks.DBCallback;
import db.interfaces.DBEntityManager;
import db.model.DBObject;
import db.model.DBResponse;
import model.pizza.Pizza;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaRepository extends BaseRepository<Pizza> implements DBCallback {
    private static PizzaRepository instance;

    private List<Pizza> pizzas;

    private PizzaRepository() {
        pizzas = new ArrayList<>();
    }

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

    public void getPizzaById(DBConnector dbConnector, int id, boolean withFullInfo, final DBCallback dbCallback) {
        if (!withFullInfo) new GetByIdAsync(dbConnector, Pizza.TYPE, dbCallback).execute(id);
    }

    public void getPizzaByName(DBConnector dbConnector, String name, boolean withFullInfo, final DBCallback dbCallback) {
        if (!withFullInfo) new GetByNameAsync(dbConnector, Pizza.TYPE, dbCallback).execute(name);
    }

    public List<Pizza> getAllPizzas(DBConnector dbConnector, boolean withFullInfo, final DBCallback dbCallback) {
        if (!withFullInfo) return new GetAllAsync(dbConnector, Pizza.TYPE, dbCallback).execute();
        return null;
    }

    public void insertPizza(DBConnector dbConnector, Pizza pizza, final DBCallback dbCallback) {
        new InsertOneAsync(dbConnector, DBObject.PIZZA, dbCallback).execute(pizza);
    }

    public void deletePizzaById(DBConnector dbConnector, int pizzaId, final DBCallback dbCallback) {
        new DeleteByIdAsync(dbConnector, Pizza.TYPE, dbCallback).execute(pizzaId);
    }

    public void deletePizzaByName(DBConnector dbConnector, String name, final DBCallback dbCallback) {
        new DeleteByNameAsync(dbConnector, Pizza.TYPE, dbCallback).execute(name);
    }

    @Override
    public void onResponse(DBResponse DBResponse) {
        if (DBResponse.body() instanceof List) {
            support.firePropertyChange("pizzas", this.pizzas, (List<Pizza>) DBResponse.body());
            this.pizzas = (List<Pizza>) DBResponse.body();
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }
}

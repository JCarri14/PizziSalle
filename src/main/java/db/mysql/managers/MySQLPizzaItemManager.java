package db.mysql.managers;

import db.enums.DBFilters;
import db.managers.MySQLManager;
import db.mappers.PizzaItemMapper;
import model.pizza.Ingredient;
import model.pizza.PizzaItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLPizzaItemManager extends MySQLManager<PizzaItem> {
    private static MySQLPizzaItemManager instance;

    protected MySQLPizzaItemManager(Connection conn) {
        super(conn);
    }

    public static MySQLPizzaItemManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLPizzaItemManager.class) {
                if (instance == null) {
                    instance = new MySQLPizzaItemManager(conn);
                }
            }
        }
        return instance;
    }


    @Override
    public List<PizzaItem> get(Map<String, String> filters) throws SQLException {
        List<PizzaItem> items = new ArrayList<>();
        query = "SELECT * FROM PizzaIngredient";
        query += getQueryFilters(filters);
        stt = conn.createStatement();
        rs = stt.executeQuery(query);

        while(rs.next()) {
            int ingredientId = Integer.parseInt(rs.getString("id_ingredient"));
            Map<String, String> flt = new HashMap<>();
            flt.put(DBFilters.ID.toString(), String.valueOf(ingredientId));

            Ingredient ingredient = MySQLIngredientManager.getInstance(conn).get(flt).get(0);

            PizzaItem p = new PizzaItem(ingredient, Integer.parseInt(rs.getString("quantity")));
            items.add(p);
        }

        return items;
    }

    @Override
    public List<PizzaItem> getAll() throws SQLException {
        List<PizzaItem> items = new ArrayList<>();
        query = "SELECT * FROM PizzaIngredient";
        stt = conn.createStatement();
        rs = stt.executeQuery(query);

        while(rs.next()) {
            int ingredientId = Integer.parseInt(rs.getString("id_ingredient"));
            Map<String, String> filters = new HashMap<>();
            filters.put(DBFilters.ID.toString(), String.valueOf(ingredientId));

            Ingredient ingredient = MySQLIngredientManager.getInstance(conn).get(filters).get(0);

            PizzaItem p = new PizzaItem(
                    ingredient,
                    Integer.parseInt(rs.getString("quantity"))
            );
            items.add(p);
        }
        return items;
    }

    @Override
    public int insert(PizzaItem element) throws SQLException {
        return -1;
    }

    public int insert(PizzaItem element, int pizzaId) throws SQLException {
        query = PizzaItemMapper.ObjectToMySQLQuery(pizzaId, element.getIngredient().getId(), element.getQuantity());
        stt = conn.createStatement();
        stt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        rs = stt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException("KO. Creating new customer failed, no ID obtained.");
        }
    }

    @Override
    public boolean update(PizzaItem element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Map<String, String> filters) throws SQLException {
        return false;
    }
}

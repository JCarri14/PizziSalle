package db.mysql.managers;

import db.managers.MySQLManager;
import db.mappers.PizzaMapper;
import model.pizza.Pizza;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLPizzaManager extends MySQLManager<Pizza> {
    private static MySQLPizzaManager instance;
    protected List<Pizza> pizzas;

    protected MySQLPizzaManager(Connection conn) {
        super(conn);
    }

    public static MySQLPizzaManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLPizzaManager.class) {
                if (instance == null) {
                    instance = new MySQLPizzaManager(conn);
                }
            }
        }
        return instance;
    }


    @Override
    public List<Pizza> get(Map<String, String> filters) throws SQLException {
        List<Pizza> pizzas = new ArrayList<>();
        query = "SELECT * FROM Pizza";
        query += getQueryFilters(filters);
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Pizza p = PizzaMapper.MySQLResponseToObject(rs);
            pizzas.add(p);
        }

        return pizzas;
    }

    @Override
    public List<Pizza> getAll() throws SQLException {
        List<Pizza> pizzas = new ArrayList<>();
        query = "SELECT * FROM Pizza";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Pizza p = PizzaMapper.MySQLResponseToObject(rs);
            pizzas.add(p);
        }
        return pizzas;
    }

    @Override
    public int insert(Pizza element) throws SQLException {
        Pizza pizza = element;
        query = PizzaMapper.ObjectToMySQLQuery(pizza);
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
    public boolean update(Pizza element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Map<String, String> filters) throws SQLException {
        return false;
    }
}

package db.mysql.managers;

import db.managers.MySQLManager;
import db.mappers.DrinkMapper;
import model.pizza.Drink;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLDrinkManager extends MySQLManager<Drink> {
    private static MySQLDrinkManager instance;

    protected MySQLDrinkManager(Connection conn) {
        super(conn);
    }

    public static MySQLDrinkManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLDrinkManager.class) {
                if (instance == null) {
                    instance = new MySQLDrinkManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public List<Drink> get(Map<String, String> filters) throws SQLException {
        List<Drink> ingredients = new ArrayList<>();
        query = "SELECT * FROM Drink";
        query += getQueryFilters(filters);
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Drink p = DrinkMapper.MySQLResponseToObject(rs);
            ingredients.add(p);
        }
        return ingredients;
    }

    @Override
    public List<Drink> getAll() throws SQLException {
        List<Drink> ingredients = new ArrayList<>();
        query = "SELECT * FROM Drink";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Drink p = DrinkMapper.MySQLResponseToObject(rs);
            ingredients.add(p);
        }
        return ingredients;
    }

    @Override
    public int insert(Drink element) throws SQLException {
        query = DrinkMapper.ObjectToMySQLQuery(element);
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
    public boolean update(Drink element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Map<String, String> filters) throws SQLException {
        return false;
    }
}

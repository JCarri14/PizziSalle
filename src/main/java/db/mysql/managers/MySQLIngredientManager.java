package db.mysql.managers;

import db.callbacks.DBCallback;
import db.managers.MySQLManager;
import db.mappers.IngredientMapper;
import db.mappers.MassMapper;
import db.mappers.UserMapper;
import model.pizza.Ingredient;
import model.pizza.Mass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLIngredientManager extends MySQLManager<Ingredient> {
    private static MySQLIngredientManager instance;

    protected MySQLIngredientManager(Connection conn) {
        super(conn);
    }

    public static MySQLIngredientManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLIngredientManager.class) {
                if (instance == null) {
                    instance = new MySQLIngredientManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public List<Ingredient> get(Map<String, String> filters) throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        query = "SELECT * FROM Ingredient";
        query += getQueryFilters(filters);
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Ingredient p = IngredientMapper.MySQLResponseToObject(rs);
            ingredients.add(p);
        }
        return ingredients;
    }

    @Override
    public List<Ingredient> getAll() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        query = "SELECT * FROM Ingredient";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Ingredient p = IngredientMapper.MySQLResponseToObject(rs);
            ingredients.add(p);
        }
        return ingredients;
    }

    @Override
    public int insert(Ingredient element) throws SQLException {
        query = IngredientMapper.ObjectToMySQLQuery(element);
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
    public boolean update(Ingredient element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Map<String, String> filters) throws SQLException {
        return false;
    }
}

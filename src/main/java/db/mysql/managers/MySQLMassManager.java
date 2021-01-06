package db.mysql.managers;

import db.callbacks.DBCallback;
import db.managers.MySQLManager;
import db.mappers.MassMapper;
import db.mappers.PizzaMapper;
import model.pizza.Mass;
import model.pizza.Pizza;
import model.pizza.PizzaItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLMassManager extends MySQLManager<Mass> {
    private static MySQLMassManager instance;

    protected MySQLMassManager(Connection conn) {
        super(conn);
    }

    public static MySQLMassManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLMassManager.class) {
                if (instance == null) {
                    instance = new MySQLMassManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public List<Mass> get(Map<String, String> filters) throws SQLException {
        List<Mass> masses = new ArrayList<>();
        query = "SELECT * FROM Mass";
        query += getQueryFilters(filters);
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Mass p = MassMapper.MySQLResponseToObject(rs);
            masses.add(p);
        }
        return masses;
    }

    @Override
    public List<Mass> getAll() throws SQLException {
        List<Mass> masses = new ArrayList<>();
        query = "SELECT * FROM Mass";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Mass p = MassMapper.MySQLResponseToObject(rs);
            masses.add(p);
        }
        return masses;
    }

    @Override
    public int insert(Mass element) throws SQLException {
        query = MassMapper.ObjectToMySQLQuery(element);
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
    public boolean update(Mass element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Map<String, String> filters) throws SQLException {
        return false;
    }
}

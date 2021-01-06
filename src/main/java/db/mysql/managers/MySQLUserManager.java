package db.mysql.managers;

import db.managers.MySQLManager;
import db.mappers.UserMapper;
import model.user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLUserManager extends MySQLManager<User> {
    private static MySQLUserManager instance;

    protected MySQLUserManager(Connection conn) {
        super(conn);
    }

    public static MySQLUserManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLUserManager.class) {
                if (instance == null) {
                    instance = new MySQLUserManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public List<User> get(Map<String, String> filters) throws SQLException {
        List<User> users = new ArrayList<>();
        query = "SELECT * FROM User";
        query += getQueryFilters(filters);
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            User p = UserMapper.MySQLResponseToObject(rs);
            users.add(p);
        }

        return users;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        query = "SELECT * FROM User";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            User p = UserMapper.MySQLResponseToObject(rs);
            users.add(p);
        }

        return users;
    }

    @Override
    public int insert(User element) throws SQLException {
        query = UserMapper.ObjectToMySQLQuery(element);
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
    public boolean update(User element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Map<String, String> filters) throws SQLException {
        return false;
    }
}

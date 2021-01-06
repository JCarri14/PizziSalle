package db.mysql;

import db.DBConnector;
import db.enums.DBObject;
import db.callbacks.DBCallback;

import java.sql.*;
import java.util.Map;

import static java.lang.System.exit;

public class MySQLConnector extends DBConnector {
    private static MySQLConnector instance;

    private final String username = "user";
    private final String password = "pass";
    private final String url = "jdbc:mysql://localhost:3306/pizzisalle?autoReconnect=true&useSSL=false";

    private Connection conn;

    private MySQLConnector() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
        if (conn == null) {
            System.out.println("DB Connection KO!");
            exit(1);
        }
    }

    public static MySQLConnector getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            synchronized (MySQLConnector.class) {
                if (instance == null) {
                    instance = new MySQLConnector();
                }
            }
        }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    public void get(DBObject objectType, Map<String, String> filters, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).get(filters);
    }

    @Override
    public void getAll(DBObject objectType, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).getAll();
    }

    @Override
    public void insert(DBObject objectType, Object element, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).insert(element);
    }

    @Override
    public void delete(DBObject objectType, Map<String, String> filters, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).delete(filters);
    }

    @Override
    public void update(DBObject objectType, Object element, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).update(element);
    }

    @Override
    public void disconnect() {
        try {
            if (instance != null) {
                conn.close();
                instance = null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

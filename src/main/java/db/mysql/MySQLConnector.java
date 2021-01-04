package db.mysql;

import db.DBConnector;
import db.enums.DBObject;
import db.interfaces.DBCallback;

import java.sql.*;
import java.util.Map;

import static java.lang.System.exit;

public class MySQLConnector extends DBConnector {
    private static MySQLConnector instance;

    private final String username = "user";
    private final String password = "pass";
    private final String url = "jdbc:mysql://localhost:3306/pizzisalle?autoReconnect=true&useSSL=false";

    private Connection conn;
    private String query;
    private Statement stt;
    private ResultSet rs;

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

    @Override
    public void get(DBObject objectType, Map<String, String> filters, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).get(filters, callback);
    }

    @Override
    public void getAll(DBObject objectType, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).getAll(callback);
    }

    @Override
    public void post(DBObject objectType, Object element, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).post(element, callback);
    }

    @Override
    public void delete(DBObject objectType, int elementId, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).delete(elementId, callback);
    }

    @Override
    public void update(DBObject objectType, Object element, DBCallback callback) throws SQLException {
        MySQLManagerFactory.get(objectType, conn).update(element, callback);
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

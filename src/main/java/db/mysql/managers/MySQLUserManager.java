package db.mysql.managers;

import db.interfaces.DBCallback;
import db.managers.DBObjectManager;
import db.managers.MySQLManager;
import model.user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class MySQLUserManager extends MySQLManager {
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
    public void get(Map<String, String> filters, DBCallback callback) {

    }

    @Override
    public void getAll(DBCallback callback) throws SQLException {

    }

    @Override
    public void post(Object element, DBCallback callback) throws SQLException {
        User user = (User) element;
        query = "INSERT INTO Customer (name, surname1, surname2, phone_number, address) VALUES ("
                + '\'' + user.getFirstName() + '\'' + ","
                + '\'' + user.getMiddleName() + '\'' + ","
                + '\'' + user.getLastName() + '\'' + ","
                + '\'' + user.getPhoneNumber() + '\'' + ","
                + '\'' + user.getAddress() + '\'' + ","
                + ");";
        stt = conn.createStatement();
        stt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        rs = stt.getGeneratedKeys();
        if (rs.next()) {
            //return rs.getInt(1);
        } else {
            throw new SQLException("KO. Creating new customer failed, no ID obtained.");
        }
    }

    @Override
    public void update(Object element, DBCallback callback) {

    }

    @Override
    public void delete(int elementId, DBCallback callback) {

    }
}

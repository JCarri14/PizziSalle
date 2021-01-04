package db.mysql.managers;

import db.interfaces.DBCallback;
import db.managers.DBObjectManager;
import db.managers.MySQLManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLDelegationManager extends MySQLManager {
    private static MySQLDelegationManager instance;

    protected MySQLDelegationManager(Connection conn) {
        super(conn);
    }

    public static MySQLDelegationManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (MySQLDelegationManager.class) {
                if (instance == null) {
                    instance = new MySQLDelegationManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public void get(Map<String, String> filters, DBCallback callback) throws SQLException {
    }

    @Override
    public void getAll(DBCallback callback) throws SQLException {
        List<String> delegations = new ArrayList<>();
        query = "SELECT * FROM Delegation";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            String d = rs.getString("name");
            delegations.add(d);
        }
    }

    @Override
    public void post(Object element, DBCallback callback) {

    }

    @Override
    public void delete(int elementId, DBCallback callback) {

    }

    @Override
    public void update(Object element, DBCallback callback) {

    }
}

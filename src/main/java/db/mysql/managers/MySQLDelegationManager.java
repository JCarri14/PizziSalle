package db.mysql.managers;

import db.callbacks.DBCallback;
import db.managers.MySQLManager;
import db.mappers.DelegationMapper;
import db.mappers.UserMapper;
import model.delegation.Delegation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLDelegationManager extends MySQLManager<Delegation> {
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
    public List<Delegation> get(Map<String, String> filters) throws SQLException {
        List<Delegation> delegations = new ArrayList<>();
        query = "SELECT * FROM Delegation";
        query += getQueryFilters(filters);
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Delegation d = DelegationMapper.MySQLResponseToObject(rs);
            delegations.add(d);
        }
        return delegations;
    }

    @Override
    public List<Delegation> getAll() throws SQLException {
        List<Delegation> delegations = new ArrayList<>();
        query = "SELECT * FROM Delegation";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Delegation d = DelegationMapper.MySQLResponseToObject(rs);
            delegations.add(d);
        }
        return delegations;
    }

    @Override
    public int insert(Delegation element) throws SQLException {
        query = DelegationMapper.ObjectToMySQLQuery(element);
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
    public boolean update(Delegation element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Map<String, String> filters) throws SQLException {
        return false;
    }
}

package db.mysql.managers;

import db.managers.MySQLEntityManager;
import db.model.DBObject;
import model.delegation.Delegation;

import java.sql.Connection;

public class DelegationManager extends MySQLEntityManager<Delegation> {
    private static DelegationManager instance;

    protected DelegationManager(Connection conn) {
        super(conn, DBObject.DELEGATION);
    }

    public static DelegationManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (DelegationManager.class) {
                if (instance == null) {
                    instance = new DelegationManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public String getTable() {
        return "Delegation";
    }
}

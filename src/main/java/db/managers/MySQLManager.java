package db.managers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class MySQLManager extends DBObjectManager {
    protected Connection conn;
    protected String query;
    protected Statement stt;
    protected ResultSet rs;

    public MySQLManager(Connection conn) {
        this.conn = conn;
    }
}

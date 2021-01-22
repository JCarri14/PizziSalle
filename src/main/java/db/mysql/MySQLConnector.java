package db.mysql;

import db.callbacks.DBCallback;
import db.criteria.Criteria;
import db.DBConnector;
import db.interfaces.CrudRepository;
import db.model.DBObject;

import java.sql.*;

import static java.lang.System.exit;

/**
 * @author Joan CA
 * @implNote MySQL connector class, implemented as a Singleton to avoid having multiple instances.
 */
public class MySQLConnector extends DBConnector implements CrudRepository {
    private static MySQLConnector instance;
    private Connection conn;
    private final String url = "jdbc:mysql://localhost:3306/pizzisalle?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private MySQLConnector() {
        super();
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

    /**
     * @implNote Username & Password need to be set to avoid exception
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        if (this.username == null || this.password == null) {
            throw new IllegalArgumentException("No username or password given!");
        }
        //Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
        if (conn == null) {
            System.out.println("DB Connection KO!");
            exit(1);
        }
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

    public Connection getConn() {
        return conn;
    }

    /**
     *
     * @param objectType Table of the database to be accessed into
     * @param filters Any specific filtering for querying
     * @param dbCallback Where the result is going to be sent
     */
    @Override
    public void get(DBObject objectType, Criteria filters, DBCallback dbCallback) {
        MySQLManagerFactory.get(objectType, conn).get(filters, dbCallback);
    }

    /**
     *
     * @param objectType Table of the database to be accessed into
     * @param dbCallback Where the result is going to be sent
     */
    @Override
    public void getAll(DBObject objectType, DBCallback dbCallback) {
        MySQLManagerFactory.get(objectType, conn).getAll(dbCallback);
    }

    /**
     *
     * @param objectType Table of the database to be accessed into
     * @param element The entry to be inserted
     * @param dbCallback Where the result is going to be sent
     */
    @Override
    public void insert(DBObject objectType, Object element, DBCallback dbCallback) {
        MySQLManagerFactory.get(objectType, conn).insert(element, null, dbCallback);
    }

    /**
     *
     * @param objectType Table of the database to be accessed into
     * @param element The entry to be inserted
     * @param parentId The parent id needed according to the table params
     * @param dbCallback Where the result is going to be sent
     */
    @Override
    public void insertWithParenId(DBObject objectType, Object element, Integer parentId, DBCallback dbCallback) {
        MySQLManagerFactory.get(objectType, conn).insert(element, parentId, dbCallback);
    }

    /**
     *
     * @param objectType Table of the database to be accessed into
     * @param filters Any specific filtering for querying
     * @param dbCallback Where the result is going to be sent
     */
    @Override
    public void delete(DBObject objectType, Criteria filters, DBCallback dbCallback) {
        MySQLManagerFactory.get(objectType, conn).delete(filters, dbCallback);
    }

    /**
     *
     * @param objectType Table of the database to be accessed into
     * @param element The entry to be updated
     * @param dbCallback Where the result is going to be sent
     */
    @Override
    public void update(DBObject objectType, Object element, DBCallback dbCallback) {
        MySQLManagerFactory.get(objectType, conn).update(element, dbCallback);
    }
}

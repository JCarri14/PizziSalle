package db.mongodb;

import db.DBConnector;
import db.enums.DBObject;
import db.callbacks.DBCallback;

import java.sql.SQLException;
import java.util.Map;

public class MongoDBConnector extends DBConnector {
    private static MongoDBConnector instance;

    public static MongoDBConnector getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            synchronized (MongoDBConnector.class) {
                if (instance == null) {
                    instance = new MongoDBConnector();
                }
            }
        }
        return instance;
    }

    @Override
    public void get(DBObject objectType, Map<String, String> filters, DBCallback callback) {

    }

    @Override
    public void getAll(DBObject objectType, DBCallback callback) throws SQLException {

    }

    @Override
    public void insert(DBObject objectType, Object element, DBCallback callback) {

    }

    @Override
    public void delete(DBObject objectType, int elementId, DBCallback callback) {

    }

    @Override
    public void update(DBObject objectType, Object element, DBCallback callback) {

    }

    @Override
    public void disconnect() {

    }
}

package db.mongodb;

import db.DBConnector;
import db.criteria.Criteria;
import db.model.DBObject;
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
    public void get(DBObject objectType, Criteria filters, DBCallback dbCallback) {

    }

    @Override
    public void getAll(DBObject objectType, DBCallback dbCallback) {

    }

    @Override
    public void insert(DBObject objectType, Object element, DBCallback dbCallback) {

    }

    @Override
    public void insertWithParenId(DBObject objectType, Object element, Integer parentId, DBCallback dbCallback) {

    }

    @Override
    public void delete(DBObject objectType, Criteria filters, DBCallback dbCallback) {

    }

    @Override
    public void update(DBObject objectType, Object element, DBCallback dbCallback) {

    }

    @Override
    public void connect() throws Exception {

    }

    @Override
    public void disconnect() {

    }
}

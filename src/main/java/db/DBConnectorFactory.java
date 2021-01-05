package db;

import db.enums.DBType;
import db.mongodb.MongoDBConnector;
import db.mysql.MySQLConnector;

import java.sql.SQLException;


public class DBConnectorFactory {

    public static DBConnector get(DBType type) throws SQLException, ClassNotFoundException {
        switch (type) {
            case MYSQL:
                return MySQLConnector.getInstance();
            case MONGO_DB:
                return MongoDBConnector.getInstance();
            default:
                return null;
        }
    }
}

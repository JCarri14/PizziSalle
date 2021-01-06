package db.repositories;

import db.DBConnector;
import db.enums.DBObject;
import db.managers.DBObjectManager;
import db.mongodb.MongoDBConnector;
import db.mysql.MySQLConnector;
import db.mysql.MySQLManagerFactory;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* Inspired by AsyncTask concept from Android Programming */
/* Not with the same complexity, but adapted to meet our needs */
public abstract class AsyncTask<Params, Result> {
    protected DBConnector dbConnector;
    protected boolean waitResponse = true;
    protected Result response;

    public AsyncTask(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public Result execute(Params... params) {
        Result response = null;
        ExecutorService thread = Executors.newCachedThreadPool();
        Future<Result> futureTask = thread.submit(() -> doInBackground(params));

        while (!futureTask.isDone()) {
            //System.out.println("FutureTask is not finished yet...");
        }
        try {
            response = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        thread.shutdown();
        return response;
    }

    public Result getById(DBObject objectType, Params... params) {
        try {
            DBObjectManager manager = getManager(dbConnector, objectType);
            Map<String, String> filters = new HashMap<>();
            filters.put("id", String.valueOf(params[0]));
            return (Result) manager.get(filters).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Result getAll(DBObject objectType) {
        try {
            DBObjectManager manager = getManager(dbConnector, objectType);
            return (Result) manager.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int insertOne(DBObject objectType, Params... params) {
        try {
            DBObjectManager manager = getManager(dbConnector, objectType);
            return manager.insert(params[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public boolean deleteOne(DBObject objectType, Params... params) {
        try {
            DBObjectManager manager = getManager(dbConnector, objectType);
            return manager.delete((Map<String, String>) params[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    protected DBObjectManager getManager(DBConnector dbConnector, DBObject objectType) {
        if (dbConnector instanceof MySQLConnector) {
            return MySQLManagerFactory.get(objectType, ((MySQLConnector) dbConnector).getConn());
        } else {
            if (dbConnector instanceof MongoDBConnector) {
                // Currently MongoDB Managers are not implemented as well as its Managers Factory
                return null;
            }
        }
        return null;
    }

    public abstract Result doInBackground(Params... params);
}

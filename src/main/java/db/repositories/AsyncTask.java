package db.repositories;

import db.callbacks.DBCallback;
import db.criteria.Criteria;
import db.DBConnector;
import db.model.DBFilter;
import db.model.DBObject;
import db.interfaces.DBEntityManager;
import db.model.DBType;
import db.model.DBResponse;
import db.mongodb.MongoDBConnector;
import db.mysql.MySQLConnector;
import db.mysql.MySQLManagerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author Joan CA
 * @implNote Inspired by AsyncTask concept from Android Programming
 * @param <Param> Dynamic object type, refering to the parameter type to be passed
 * @param <Result> Dynamic object type, refering to the desired/expected result type
 */
public abstract class AsyncTask<Param, Result> {
    protected DBConnector dbConnector;
    protected boolean waitResponse = true;
    protected DBResponse dbResponse;
    protected boolean didReceive = false;

    public AsyncTask(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public Result execute(Param... params) {
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

    public Param getById(DBObject objectType, Param... params) {
        DBEntityManager manager = getManager(dbConnector, objectType);
        Criteria criteria = new Criteria(DBType.MYSQL);
        criteria.addFilter(DBFilter.ID, String.valueOf(params[0]));
        manager.get(criteria, new DBCallback() {
            @Override
            public void onResponse(db.model.DBResponse DBResponse) {
                AsyncTask.this.didReceive = true;
                AsyncTask.this.dbResponse = DBResponse;
            }

            @Override
            public void onFailure(Throwable t) {
                AsyncTask.this.didReceive = true;
            }
        });
        while(!didReceive) {}
        return (Param) dbResponse.body();
    }

    public List<Param> getAll(DBObject objectType) {
        DBEntityManager manager = getManager(dbConnector, objectType);
        manager.getAll(new DBCallback() {
            @Override
            public void onResponse(db.model.DBResponse DBResponse) {
                AsyncTask.this.didReceive = true;
                AsyncTask.this.dbResponse = DBResponse;
            }

            @Override
            public void onFailure(Throwable t) {
                AsyncTask.this.didReceive = true;
            }
        });
        while(!didReceive) {}
        return (List<Param>) dbResponse.body();
    }

    public Integer insertOne(DBObject objectType, Param... params) {
        DBEntityManager manager = getManager(dbConnector, objectType);
        manager.insert(params[0], null, new DBCallback() {
            @Override
            public void onResponse(db.model.DBResponse DBResponse) {
                AsyncTask.this.didReceive = true;
                AsyncTask.this.dbResponse = DBResponse;
            }

            @Override
            public void onFailure(Throwable t) {
                AsyncTask.this.didReceive = true;
            }
        });
        while(!didReceive) {}
        return (Integer) dbResponse.body();
    }

    public Boolean deleteOne(DBObject objectType, Param... params) {
        DBEntityManager manager = getManager(dbConnector, objectType);
        Criteria criteria = new Criteria(DBType.MYSQL);
        criteria.addFilter(DBFilter.ID, String.valueOf(params[0]));
        manager.delete(criteria, new DBCallback() {
            @Override
            public void onResponse(db.model.DBResponse DBResponse) {
                AsyncTask.this.didReceive = true;
                AsyncTask.this.dbResponse = DBResponse;
            }

            @Override
            public void onFailure(Throwable t) {
                AsyncTask.this.didReceive = true;
            }
        });
        while(!didReceive) {}
        return (Boolean) dbResponse.body();
    }

    protected DBEntityManager getManager(DBConnector dbConnector, DBObject objectType) {
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

    public abstract Result doInBackground(Param... params);
}

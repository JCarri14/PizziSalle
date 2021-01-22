package db.repositories;

import db.callbacks.DBCallback;
import db.criteria.Criteria;
import db.DBConnector;
import db.model.DBFilter;
import db.model.DBObject;
import db.interfaces.DBEntityManager;
import db.model.DBType;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/* Another level of abstraction for end users */
public abstract class BaseRepository<Param> {
    protected PropertyChangeSupport support;

    protected BaseRepository() {
        this.support = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }

    public void removeObserver(PropertyChangeListener pcl) {
        this.support.removePropertyChangeListener(pcl);
    }

    protected class GetByIdAsync extends AsyncTask<Integer, Param> {

        private DBObject object;
        private DBCallback dbCallback;

        public GetByIdAsync(DBConnector dbConnector, DBObject object, DBCallback dbCallback) {
            super(dbConnector);
            this.object = object;
            this.dbCallback = dbCallback;
        }

        @Override
        public Param doInBackground(Integer... integers) {
            DBEntityManager manager = getManager(dbConnector, object);
            Criteria criteria = new Criteria(DBType.MYSQL);
            criteria.addFilter(DBFilter.ID, String.valueOf(integers[0]));
            manager.get(criteria, dbCallback);
            return null;
        }
    }

    protected class GetByNameAsync extends AsyncTask<String, Param> {

        private DBObject object;
        private DBCallback dbCallback;

        public GetByNameAsync(DBConnector dbConnector, DBObject object, DBCallback dbCallback) {
            super(dbConnector);
            this.object = object;
            this.dbCallback = dbCallback;
        }

        @Override
        public Param doInBackground(String... strings) {
            DBEntityManager manager = getManager(dbConnector, object);
            Criteria criteria = new Criteria(DBType.MYSQL);
            criteria.addFilter(DBFilter.NAME, String.valueOf(strings[0]));
            manager.get(criteria, dbCallback);
            return null;
        }
    }

    protected class GetAllAsync extends AsyncTask<Void, List<Param>> {

        private DBObject object;
        private DBCallback dbCallback;

        public GetAllAsync(DBConnector dbConnector, DBObject object, DBCallback dbCallback) {
            super(dbConnector);
            this.object = object;
            this.dbCallback = dbCallback;
        }

        @Override
        public List<Param> doInBackground(Void... voids) {
            DBEntityManager manager = getManager(dbConnector, object);
            manager.getAll(dbCallback);
            return null;
        }
    }

    protected class InsertOneAsync extends AsyncTask<Param,Integer> {

        private DBObject object;
        private DBCallback dbCallback;

        public InsertOneAsync(DBConnector dbConnector, DBObject object, DBCallback dbCallback) {
            super(dbConnector);
            this.object = object;
            this.dbCallback = dbCallback;
        }

        @Override
        public Integer doInBackground(Param... params) {
            DBEntityManager manager = getManager(dbConnector, object);
            manager.insert(params[0], null, dbCallback);
            return 0;
        }
    }

    protected class DeleteByIdAsync extends AsyncTask<Integer,Boolean> {

        private DBObject object;
        private DBCallback dbCallback;

        public DeleteByIdAsync(DBConnector dbConnector, DBObject object, DBCallback dbCallback) {
            super(dbConnector);
            this.object = object;
            this.dbCallback = dbCallback;
        }

        @Override
        public Boolean doInBackground(Integer... integers) {
            DBEntityManager manager = getManager(dbConnector, object);
            Criteria criteria = new Criteria(DBType.MYSQL);
            criteria.addFilter(DBFilter.ID, String.valueOf(integers[0]));
            manager.delete(criteria, dbCallback);
            return true;
        }
    }

    protected class DeleteByNameAsync extends AsyncTask<String,Boolean> {

        private DBObject object;
        private DBCallback dbCallback;

        public DeleteByNameAsync(DBConnector dbConnector, DBObject object, DBCallback dbCallback) {
            super(dbConnector);
            this.object = object;
            this.dbCallback = dbCallback;
        }

        @Override
        public Boolean doInBackground(String... strings) {
            DBEntityManager manager = getManager(dbConnector, object);
            Criteria criteria = new Criteria(DBType.MYSQL);
            criteria.addFilter(DBFilter.NAME, strings[0]);
            manager.delete(criteria, dbCallback);
            return true;
        }
    }

}

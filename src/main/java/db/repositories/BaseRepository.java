package db.repositories;

import db.DBConnector;
import db.enums.DBObject;
import db.managers.DBObjectManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Another level of abstraction for end users */
public class BaseRepository<Param> {
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

        public GetByIdAsync(DBConnector dbConnector, DBObject object) {
            super(dbConnector);
            this.object = object;
        }

        @Override
        public Param doInBackground(Integer... integers) {
            try {
                DBObjectManager manager = getManager(dbConnector, object);
                Map<String, String> filters = new HashMap<>();
                filters.put("id", String.valueOf(integers[0]));
                manager.get(filters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            while (this.waitResponse) {}
            return this.response;
        }
    }

    protected class GetByNameAsync extends AsyncTask<String, Param> {

        private DBObject object;

        public GetByNameAsync(DBConnector dbConnector, DBObject object) {
            super(dbConnector);
            this.object = object;
        }

        @Override
        public Param doInBackground(String... strings) {
            try {
                DBObjectManager manager = getManager(dbConnector, object);
                Map<String, String> filters = new HashMap<>();
                filters.put("name", strings[0]);
                manager.get(filters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            while (this.waitResponse) {}
            return this.response;
        }
    }

    protected class GetAllAsync extends AsyncTask<Void, List<Param>> {

        private DBObject object;

        public GetAllAsync(DBConnector dbConnector, DBObject object) {
            super(dbConnector);
            this.object = object;
        }

        @Override
        public List<Param> doInBackground(Void... voids) {
            try {
                DBObjectManager manager = getManager(dbConnector, object);
                return manager.getAll();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }

    protected class InsertOneAsync extends AsyncTask<Param,Void> {

        private DBObject object;

        public InsertOneAsync(DBConnector dbConnector, DBObject object) {
            super(dbConnector);
            this.object = object;
        }

        @Override
        public Void doInBackground(Param... params) {
            try {
                DBObjectManager manager = getManager(dbConnector, object);
                manager.insert(params[0]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }

    protected class DeleteByIdAsync extends AsyncTask<Integer,Void> {

        private DBObject object;

        public DeleteByIdAsync(DBConnector dbConnector, DBObject object) {
            super(dbConnector);
            this.object = object;
        }

        @Override
        public Void doInBackground(Integer... integers) {
            try {
                DBObjectManager manager = getManager(dbConnector, object);
                Map<String, String> filters = new HashMap<>();
                filters.put("id", String.valueOf(integers[0]));
                manager.delete(filters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }

    protected class DeleteByNameAsync extends AsyncTask<String,Void> {

        private DBObject object;

        public DeleteByNameAsync(DBConnector dbConnector, DBObject object) {
            super(dbConnector);
            this.object = object;
        }

        @Override
        public Void doInBackground(String... strings) {
            try {
                DBObjectManager manager = getManager(dbConnector, object);
                Map<String, String> filters = new HashMap<>();
                filters.put("name", strings[0]);
                manager.delete(filters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }

}

package db.managers;

import db.callbacks.DBCallback;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.Map;

/* Observable */
/* Analogy to DAO pattern */
public abstract class DBObjectManager {
    protected PropertyChangeSupport support;

    protected DBObjectManager() {
        this.support = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }

    public void removeObserver(PropertyChangeListener pcl) {
        this.support.removePropertyChangeListener(pcl);
    }

    public abstract void get(Map<String, String> filters, final DBCallback callback) throws SQLException;
    public abstract void getAll(final DBCallback callback) throws SQLException;
    public abstract void post(Object element, final DBCallback callback) throws SQLException;
    public abstract void update(Object element, final DBCallback callback) throws SQLException;
    public abstract void delete(int elementId, final DBCallback callback) throws SQLException;
}

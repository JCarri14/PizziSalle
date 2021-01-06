package db.managers;

import db.callbacks.DBCallback;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/* Observable */
/* Analogy to DAO pattern */
public abstract class DBObjectManager<Param> {
    public abstract List<Param> get(Map<String, String> filters) throws SQLException;
    public abstract List<Param> getAll() throws SQLException;
    public abstract int insert(Param element) throws SQLException;
    public abstract boolean update(Param element) throws SQLException;
    public abstract boolean delete(Map<String, String> filters) throws SQLException;
}

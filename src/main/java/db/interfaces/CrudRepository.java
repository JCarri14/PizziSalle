package db.interfaces;

import db.DBConnector;
import db.callbacks.DBCallback;
import db.criteria.Criteria;
import db.model.DBObject;

import java.util.List;

public interface CrudRepository {
    void get(DBObject objectType, Criteria filters, DBCallback dbCallback);
    void getAll(DBObject objectType, DBCallback dbCallback);
    void insert(DBObject objectType, Object element, DBCallback dbCallback);
    void insertWithParenId(DBObject objectType, Object element, Integer parentId, DBCallback dbCallback);
    void delete(DBObject objectType, Criteria filters, DBCallback dbCallback);
    void update(DBObject objectType, Object element, DBCallback dbCallback);
}

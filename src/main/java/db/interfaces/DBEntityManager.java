package db.interfaces;

import db.callbacks.DBCallback;
import db.criteria.Criteria;

/**
 * @author Joan CA
 * @param <Param> Dynamic object type
 */
public interface DBEntityManager<Param> {
    void get(Criteria filters, DBCallback dbCallback);
    void getAll(DBCallback dbCallback);
    void insert(Param element, Integer parentId, DBCallback dbCallback);
    void update(Param element, DBCallback dbCallback);
    void delete(Criteria filters, DBCallback dbCallback);
}

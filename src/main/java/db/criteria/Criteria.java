package db.criteria;

import db.model.DBFilter;
import db.model.DBType;

import java.util.Map;

public class Criteria {
    private DBType dbType;
    private Map<String, String> filters;

    public Criteria(DBType dbType) {
        this.dbType = dbType;
    }

    public void addFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    public void addFilter(DBFilter filter, String value) {
        this.filters.put(getFilterKey(filter), value);
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    private String getFilterKey(DBFilter dbFilter) {
        switch (this.dbType) {
            case MYSQL:
                return getMysqlFilterKey(dbFilter);
            case MONGO_DB:
                return getMongoFilterKey(dbFilter);
            default:
                return null;
        }
    }

    private String getMysqlFilterKey(DBFilter dbFilter) {
        switch (dbFilter) {
            case ID:
                return "id";
            case ID_PIZZA:
                return "id_pizza";
            case ID_INGREDIENT:
                return "id_ingredient";
            case ID_MASS:
                return "id_mass";
            case ID_ORDER:
                return "id_order";
            case NAME:
                return "name";
            case MIDDLE_NAME:
                return "middle_name";
            case LAST_NAME:
                return "last_name";
            case AGE:
                return "age";
            case EMAIL:
                return "email";
            case PHONE_NUMBER:
                return "phone_number";
            case QUANTITY:
                return "quantity";
            default:
                return null;
        }
    }

    private String getMongoFilterKey(DBFilter dbFilter) {
        // DBTypes value in noSQL, e.g camelCase values...
        return null;
    }

}

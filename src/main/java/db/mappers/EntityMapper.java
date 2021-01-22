package db.mappers;

import db.model.DBAction;
import db.model.DBType;

import java.sql.ResultSet;

/**
 * @author Joan Ca
 * @implNote Entity Mapper class, custom ORM/ODM, depending on which database is being used
 * @param <Param> Dynamic object type, refering to which entity is being treated
 */
public abstract class EntityMapper<Param> {

    protected DBType dbType;

    public EntityMapper(DBType dbType) {
        this.dbType = dbType;
    }

    public Param dbResponseToEntity(Object data) {
        switch (this.dbType) {
            case MYSQL:
                return mySQLResponseToEntity((ResultSet) data);
            case MONGO_DB:
                return mongoResponseToEntity();
            default:
                return null;
        }
    }

    public String entityToQuery(DBAction action, Param param) {
        switch (action) {
            case INSERT:
                return entityToInsert(param);
            case DELETE:
                return entityToDelete(param);
            case GET:
                return entityToGet(param);
            case UPDATE:
                return entityToUpdate(param);
            default:
                return null;
        }
    }

    private String entityToInsert(Param param) {
        switch (this.dbType) {
            case MYSQL:
                return mySQLInsert(param);
            case MONGO_DB:
                return mongoInsert(param);
            default:
                return null;
        }
    }

    private String entityToDelete(Param param) {
        switch (this.dbType) {
            case MYSQL:
                return mySQLDelete(param);
            case MONGO_DB:
                return mongoDelete(param);
            default:
                return null;
        }
    }

    private String entityToGet(Param param) {
        switch (this.dbType) {
            case MYSQL:
                return mySQLGet(param);
            case MONGO_DB:
                return mongoGet(param);
            default:
                return null;
        }
    }

    private String entityToUpdate(Param param) {
        switch (this.dbType) {
            case MYSQL:
                return mySQLUpdate(param);
            case MONGO_DB:
                return mongoUpdate(param);
            default:
                return null;
        }
    }

    protected abstract Param mySQLResponseToEntity(ResultSet rs);
    protected abstract Param mongoResponseToEntity();

    public abstract String mySQLInsert(Param param);
    public abstract String mongoInsert(Param param);

    public abstract String mySQLDelete(Param param);
    public abstract String mongoDelete(Param param);

    public abstract String mySQLGet(Param param);
    public abstract String mongoGet(Param param);

    public abstract String mySQLUpdate(Param param);
    public abstract String mongoUpdate(Param param);
}

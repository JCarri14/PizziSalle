package db.managers;

import db.callbacks.DBCallback;
import db.criteria.Criteria;
import db.interfaces.DBEntityManager;
import db.mappers.EntityMapper;
import db.mappers.MapperFactory;
import db.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Joan CA
 * @implNote
 * @param <Param> Dynamic object type
 */
public abstract class MySQLEntityManager<Param> implements DBEntityManager<Param> {

    protected Connection conn;
    protected String query;
    protected Statement stt;
    protected ResultSet rs;

    protected DBObject dbObject;

    public MySQLEntityManager(Connection conn, DBObject dbObject) {
        this.conn = conn;
        this.dbObject = dbObject;
    }

    public abstract String getTable();

    @Override
    public void get(Criteria filters, DBCallback dbCallback) {
        try {
            List<Param> elements = new ArrayList<>();
            EntityMapper<Param> mapper = MapperFactory.get(DBType.MYSQL, this.dbObject);

            query = "SELECT * FROM " + getTable();
            query += getQueryFilters(filters);
            stt = conn.createStatement();
            rs = stt.executeQuery (query);

            while(rs.next()) {
                Param d = mapper.dbResponseToEntity(rs);
                elements.add(d);
            }
            DBResponse<List<Param>> DBResponse = new DBResponse<>(DBResponseCode.OK, elements);
            if (dbCallback != null) dbCallback.onResponse(DBResponse);
        } catch (SQLException throwable) {
            if (dbCallback != null) dbCallback.onFailure(throwable);
        }
    }

    @Override
    public void getAll(DBCallback dbCallback) {
        try {
            List<Param> elements = new ArrayList<>();
            EntityMapper<Param> mapper = MapperFactory.get(DBType.MYSQL, this.dbObject);

            query = "SELECT * FROM " + getTable();
            stt = conn.createStatement();
            rs = stt.executeQuery (query);

            while(rs.next()) {
                Param d = mapper.dbResponseToEntity(rs);
                elements.add(d);
            }
            DBResponse<List<Param>> DBResponse = new DBResponse<>(DBResponseCode.OK, elements);
            if (dbCallback != null) dbCallback.onResponse(DBResponse);
        } catch (SQLException throwable) {
            if (dbCallback != null) dbCallback.onFailure(throwable);
        }
    }

    @Override
    public void insert(Param element, Integer parentId, DBCallback dbCallback) {
        try {
            EntityMapper<Param> mapper = MapperFactory.get(DBType.MYSQL, this.dbObject);

            query = mapper.entityToQuery(DBAction.INSERT, element);
            stt = conn.createStatement();
            stt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            rs = stt.getGeneratedKeys();

            DBResponse<Integer> DBResponse = new DBResponse<>();

            if (rs.next()) {
                DBResponse.setCode(DBResponseCode.CREATED);
                DBResponse.setBody(rs.getInt(1));
                if (dbCallback != null) dbCallback.onResponse(DBResponse);
            } else {
                DBResponse.setCode(DBResponseCode.BAD_REQUEST);
                if (dbCallback != null) dbCallback.onResponse(DBResponse);
            }
        } catch (SQLException throwable) {
            if (dbCallback != null) dbCallback.onFailure(throwable);
        }
    }

    @Override
    public void update(Param element, DBCallback dbCallback) {
    }

    @Override
    public void delete(Criteria filters, DBCallback dbCallback) {
    }

    protected String getQueryFilters(Criteria criteria) {
        StringBuilder builder = new StringBuilder();

        Iterator<Map.Entry<String, String>> itr = criteria.getFilters().entrySet().iterator();
        if (!criteria.getFilters().isEmpty()) {
            builder.append(" WHERE ");
            while(itr.hasNext()) {
                Map.Entry<String, String> entry = itr.next();
                builder.append(entry.getKey())
                        .append("= \"")
                        .append(entry.getValue())
                        .append("\"");
                if (itr.hasNext()) builder.append(" AND ");
            }
            builder.append(";");
        }
        return builder.toString();
    }
}

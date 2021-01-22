package db.mysql.managers;

import db.callbacks.DBCallback;
import db.criteria.Criteria;
import db.mappers.EntityMapper;
import db.mappers.MapperFactory;
import db.model.*;
import db.managers.MySQLEntityManager;
import db.mappers.PizzaItemMapper;
import model.pizza.Ingredient;
import model.pizza.PizzaItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaItemManager extends MySQLEntityManager<PizzaItem> {
    private static PizzaItemManager instance;

    protected PizzaItemManager(Connection conn) {
        super(conn, DBObject.PIZZA_ITEM);
    }

    public static PizzaItemManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (PizzaItemManager.class) {
                if (instance == null) {
                    instance = new PizzaItemManager(conn);
                }
            }
        }
        return instance;
    }

    @Override
    public void get(Criteria filters, DBCallback dbCallback){
        try {
            List<PizzaItem> items = new ArrayList<>();
            query = "SELECT * FROM PizzaIngredient";
            query += getQueryFilters(filters);
            stt = conn.createStatement();
            rs = stt.executeQuery(query);
            while(rs.next()) {
                //int ingredientId = Integer.parseInt(rs.getString("id_ingredient"));
                //Map<String, String> flt = new HashMap<>();
                //flt.put(DBFilter.ID.toString(), String.valueOf(ingredientId));
                Ingredient ingredient = new Ingredient(Integer.parseInt(rs.getString("id_ingredient")), null,null);
                PizzaItem p = new PizzaItem(ingredient, Integer.parseInt(rs.getString("quantity")));
                items.add(p);
            }
            DBResponse<List<PizzaItem>> response = new DBResponse(DBResponseCode.OK, items);
            dbCallback.onResponse(response);
        } catch (SQLException throwable) {
            dbCallback.onFailure(throwable);
        }
    }

    @Override
    public void getAll(DBCallback dbCallback) {
        try {
            List<PizzaItem> items = new ArrayList<>();
            query = "SELECT * FROM PizzaIngredient";
            stt = conn.createStatement();
            rs = stt.executeQuery(query);
            while(rs.next()) {
                //int ingredientId = Integer.parseInt(rs.getString("id_ingredient"));
                //Map<String, String> flt = new HashMap<>();
                //flt.put(DBFilter.ID.toString(), String.valueOf(ingredientId));
                Ingredient ingredient = new Ingredient(Integer.parseInt(rs.getString("id_ingredient")), null,null);
                PizzaItem p = new PizzaItem(ingredient, Integer.parseInt(rs.getString("quantity")));
                items.add(p);
            }
            DBResponse<List<PizzaItem>> response = new DBResponse(DBResponseCode.OK, items);
            dbCallback.onResponse(response);
        } catch (SQLException throwable) {
            dbCallback.onFailure(throwable);
        }
    }


    public void insert(PizzaItem element, int parentId,  DBCallback dbCallback) {
        try {
            EntityMapper<PizzaItem> mapper = MapperFactory.get(DBType.MYSQL, this.dbObject);
            query = ((PizzaItemMapper)mapper).MySQLInsert(parentId, element.getIngredient().getId(), element.getQuantity());
            stt = conn.createStatement();
            stt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            rs = stt.getGeneratedKeys();
            DBResponse<Integer> response = new DBResponse<>();
            if (rs.next()) {
                response.setCode(DBResponseCode.OK);
                response.setBody(rs.getInt(1));
                dbCallback.onResponse(response);
            } else {
                response.setCode(DBResponseCode.BAD_REQUEST);
                dbCallback.onResponse(response);
            }
        } catch (SQLException throwable) {
            dbCallback.onFailure(throwable);
        }

    }

    @Override
    public String getTable() {
        return "PizzaIngredient";
    }
}

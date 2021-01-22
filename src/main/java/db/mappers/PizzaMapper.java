package db.mappers;

import builders.v1.PizzaBuilder;
import db.model.DBType;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzaMapper extends EntityMapper<Pizza> {

    public PizzaMapper(DBType dbType) {
        super(dbType);
    }

    @Override
    protected Pizza mySQLResponseToEntity(ResultSet rs) {
        try {
            return new PizzaBuilder()
                    .withId(Integer.parseInt(rs.getString("id_pizza")))
                    .withName(rs.getString("name"))
                    .createPizza();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    protected Pizza mongoResponseToEntity() {
        return null;
    }

    @Override
    public String mySQLInsert(Pizza pizza) {
        return "INSERT INTO Pizza (name) VALUES ("
                + '\'' + pizza.getName() + '\'' + ");";
    }

    @Override
    public String mongoInsert(Pizza pizza) {
        return null;
    }

    @Override
    public String mySQLDelete(Pizza pizza) {
        return null;
    }

    @Override
    public String mongoDelete(Pizza pizza) {
        return null;
    }

    @Override
    public String mySQLGet(Pizza pizza) {
        return null;
    }

    @Override
    public String mongoGet(Pizza pizza) {
        return null;
    }

    @Override
    public String mySQLUpdate(Pizza pizza) {
        return null;
    }

    @Override
    public String mongoUpdate(Pizza pizza) {
        return null;
    }
}

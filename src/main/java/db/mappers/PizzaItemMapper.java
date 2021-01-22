package db.mappers;

import builders.v1.PizzaBuilder;
import db.model.DBType;
import model.pizza.Pizza;
import model.pizza.PizzaItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzaItemMapper extends EntityMapper<PizzaItem> {

    public PizzaItemMapper(DBType dbType) {
        super(dbType);
    }

    @Override
    protected PizzaItem mySQLResponseToEntity(ResultSet rs) {
        return null;
    }

    @Override
    protected PizzaItem mongoResponseToEntity() {
        return null;
    }

    @Override
    public String mySQLInsert(PizzaItem pizzaItem) {
        return null;
    }

    public String MySQLInsert(int pizzaId, int ingredientId, int quantity) {
        return "INSERT INTO PizzaIngredient (id_pizza, id_ingredient, quantity) VALUES ("
                + pizzaId + ", " + ingredientId + ", " + quantity
                + ");";
    }

    @Override
    public String mongoInsert(PizzaItem pizzaItem) {
        return null;
    }

    @Override
    public String mySQLDelete(PizzaItem pizzaItem) {
        return null;
    }

    @Override
    public String mongoDelete(PizzaItem pizzaItem) {
        return null;
    }

    @Override
    public String mySQLGet(PizzaItem pizzaItem) {
        return null;
    }

    @Override
    public String mongoGet(PizzaItem pizzaItem) {
        return null;
    }

    @Override
    public String mySQLUpdate(PizzaItem pizzaItem) {
        return null;
    }

    @Override
    public String mongoUpdate(PizzaItem pizzaItem) {
        return null;
    }
}

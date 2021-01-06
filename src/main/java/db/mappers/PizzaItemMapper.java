package db.mappers;

import builders.v1.PizzaBuilder;
import model.pizza.Pizza;
import model.pizza.PizzaItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzaItemMapper {

    public static PizzaItem MySQLResponseToObject(ResultSet rs) {
        return null;
    }

    public static String ObjectToMySQLQuery(int pizzaId, int ingredientId, int quantity) {
        return "INSERT INTO PizzaIngredient (id_pizza, id_ingredient, quantity) VALUES ("
                + pizzaId + ", " + ingredientId + ", " + quantity
                + ");";
    }
}

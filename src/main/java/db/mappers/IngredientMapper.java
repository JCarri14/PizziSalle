package db.mappers;

import model.pizza.Ingredient;
import model.pizza.Mass;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper {

    public static Ingredient MySQLResponseToObject(ResultSet rs) {
        try {
            return new Ingredient(
                    Integer.parseInt(rs.getString("id")),
                    rs.getString("name"),
                    rs.getString("description")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static String ObjectToMySQLQuery(Ingredient ingredient) {
        return "INSERT INTO Ingredient (name, description) VALUES ("
                + '\'' + ingredient.getName() + '\'' + ","
                + '\'' + ingredient.getDescription() + '\''
                + ");";
    }
}

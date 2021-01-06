package db.mappers;

import builders.v1.PizzaBuilder;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzaMapper {

    public static Pizza MySQLResponseToObject(ResultSet rs) {
        try {
            return new PizzaBuilder()
                    .withId(Integer.parseInt(rs.getString("id")))
                    .withName(rs.getString("name"))
                    .createPizza();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static String ObjectToMySQLQuery(Pizza pizza) {
        return "INSERT INTO Pizza (name) VALUES ("
                + '\'' + pizza.getName() + '\'' + ");";
    }
}

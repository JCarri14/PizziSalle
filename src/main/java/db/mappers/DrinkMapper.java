package db.mappers;

import model.pizza.Drink;
import model.pizza.Mass;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrinkMapper {

    public static Drink MySQLResponseToObject(ResultSet rs) {
        try {
            return new Drink(
                    Integer.parseInt(rs.getString("id")),
                    rs.getString("name")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static String ObjectToMySQLQuery(Drink drink) {
        return "INSERT INTO Drink (name) VALUES ("
                + '\'' + drink.getName() + '\''
                + ");";
    }
}

package db.mappers;

import model.pizza.Mass;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MassMapper {

    public static Mass MySQLResponseToObject(ResultSet rs) {
        try {
            return new Mass(
                    Integer.parseInt(rs.getString("id")),
                    rs.getString("name")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static String ObjectToMySQLQuery(Mass mass) {
        return "INSERT INTO Mass (name) VALUES ("
                + '\'' + mass.getName() + '\''
                + ");";
    }
}

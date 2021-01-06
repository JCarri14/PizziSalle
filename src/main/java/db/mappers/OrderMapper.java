package db.mappers;

import builders.v1.OrderBuilder;
import builders.v1.PizzaBuilder;
import builders.v1.UserBuilder;
import model.delegation.Delegation;
import model.order.Order;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper {

    public static Order MySQLResponseToObject(ResultSet rs) {
        try {
            return new OrderBuilder()
                    .withId(Integer.parseInt(rs.getString("id")))
                    .withComments(rs.getString("comment"))
                    .withCustomer(new UserBuilder()
                            .withId(Integer.parseInt(rs.getString("id_customer")))
                            .createUser())
                    .withDelegation(new Delegation(Integer.parseInt(rs.getString("id_delegation"))))
                    .createOrder();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static String ObjectToMySQLQuery(Order order) {
        return "INSERT INTO Order (id_customer, id_delegation, comment) VALUES ("
                + order.getCustomer().getId() + ", "
                + order.getDelegation().getId() + ", "
                + '\'' + order.getComments() + '\'' + ","
                + ");";
    }
}

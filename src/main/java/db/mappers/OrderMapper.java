package db.mappers;

import builders.v1.OrderBuilder;
import builders.v1.PizzaBuilder;
import builders.v1.UserBuilder;
import db.model.DBType;
import model.delegation.Delegation;
import model.order.Order;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper extends EntityMapper<Order>{

    public OrderMapper(DBType dbType) {
        super(dbType);
    }

    @Override
    protected Order mySQLResponseToEntity(ResultSet rs) {
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

    @Override
    protected Order mongoResponseToEntity() {
        return null;
    }

    @Override
    public String mySQLInsert(Order order) {
        return "INSERT INTO CustomerOrder (id_customer, id_delegation, comment) VALUES ("
                + order.getCustomer().getId() + ", "
                + order.getDelegation().getId() + ", "
                + '\'' + order.getComments() + '\''
                + ");";
    }

    @Override
    public String mongoInsert(Order order) {
        return null;
    }

    @Override
    public String mySQLDelete(Order order) {
        return null;
    }

    @Override
    public String mongoDelete(Order order) {
        return null;
    }

    @Override
    public String mySQLGet(Order order) {
        return null;
    }

    @Override
    public String mongoGet(Order order) {
        return null;
    }

    @Override
    public String mySQLUpdate(Order order) {
        return null;
    }

    @Override
    public String mongoUpdate(Order order) {
        return null;
    }
}

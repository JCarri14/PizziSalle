package db.mappers;

import builders.v1.PizzaBuilder;
import builders.v2.OrderItemBuilder;
import model.order.OrderItem;
import model.pizza.Drink;
import model.pizza.Mass;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper {

    public static OrderItem MySQLResponseToObject(ResultSet rs) {
        return new OrderItemBuilder()
                .with(itemBuilder -> {
                    try {
                        itemBuilder.id = Integer.parseInt(rs.getString("id_order_item"));
                        itemBuilder.quantity = Integer.parseInt(rs.getString("quantity"));
                        itemBuilder.pizza = new PizzaBuilder()
                                .withId(Integer.parseInt(rs.getString("id_pizza"))).createPizza();
                        itemBuilder.drink = new Drink(Integer.parseInt(rs.getString("id_drink")));
                        itemBuilder.mass = new Mass(Integer.parseInt(rs.getString("id_mass")));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                })
                .createOrderItem();
    }

    public static String ObjectToMySQLQuery(OrderItem item, int orderId) {
        return "INSERT INTO Order (id_order, id_pizza, id_drink, id_mass, quantity) VALUES ("
                + orderId + ", "
                + item.getPizza().getId() + ", "
                + item.getDrink().getId() + ", "
                + item.getMass().getId() + ", "
                + item.getQuantity() + ", "
                + ");";
    }
}

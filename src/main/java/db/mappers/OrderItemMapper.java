package db.mappers;

import builders.v1.PizzaBuilder;
import builders.v2.OrderItemBuilder;
import db.model.DBType;
import model.order.OrderItem;
import model.pizza.Drink;
import model.pizza.Mass;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper extends EntityMapper<OrderItem>{

    public OrderItemMapper(DBType dbType) {
        super(dbType);
    }

    @Override
    protected OrderItem mySQLResponseToEntity(ResultSet rs) {
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

    @Override
    protected OrderItem mongoResponseToEntity() {
        return null;
    }

    @Override
    public String mySQLInsert(OrderItem orderItem) {
        return null;
    }

    public String MySQLInsert(OrderItem item, int orderId) {
        int quantity = item.getQuantity();
        if (item.getPizza() != null) {
            int pizzaID = item.getPizza().getId();
            return "INSERT INTO OrderItem (id_order, id_pizza, quantity) VALUES ("
                    + orderId + ", "
                    + pizzaID + ", "
                    + quantity
                    + ");";
        } else if (item.getDrink() != null) {
            int drinkID = item.getDrink().getId();
            return "INSERT INTO OrderItem (id_order, id_drink, quantity) VALUES ("
                    + orderId + ", "
                    + drinkID + ", "
                    + quantity
                    + ");";
        } else if (item.getMass() != null) {
            int massID = item.getMass().getId();
            return "INSERT INTO OrderItem (id_order, id_mass, quantity) VALUES ("
                    + orderId + ", "
                    + massID + ", "
                    + quantity
                    + ");";
        }
        return "";
    }

    @Override
    public String mongoInsert(OrderItem orderItem) {
        return null;
    }

    @Override
    public String mySQLDelete(OrderItem orderItem) {
        return null;
    }

    @Override
    public String mongoDelete(OrderItem orderItem) {
        return null;
    }

    @Override
    public String mySQLGet(OrderItem orderItem) {
        return null;
    }

    @Override
    public String mongoGet(OrderItem orderItem) {
        return null;
    }

    @Override
    public String mySQLUpdate(OrderItem orderItem) {
        return null;
    }

    @Override
    public String mongoUpdate(OrderItem orderItem) {
        return null;
    }

}

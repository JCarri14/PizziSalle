package db;


import db.enums.DBObject;
import db.interfaces.DBCallback;

import java.sql.SQLException;
import java.util.Map;

public abstract class DBConnector {

    public abstract void get(DBObject objectType, Map<String, String> filters, final DBCallback callback) throws SQLException;
    public abstract void getAll(DBObject objectType, final DBCallback callback) throws SQLException;
    public abstract void post(DBObject objectType, Object element, final DBCallback callback) throws SQLException;
    public abstract void delete(DBObject objectType, int elementId, final DBCallback callback) throws SQLException;
    public abstract void update(DBObject objectType, Object element, final DBCallback callback) throws SQLException;
    public abstract void disconnect();
    /*

    public List<Pizza> loadPizzas() throws SQLException {
        List<Pizza> pizzas = new ArrayList<>();
        query = "SELECT * FROM Pizza";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Pizza p = new Pizza(rs.getInt("id_pizza"),rs.getString("name"));
            pizzas.add(p);
        }
        return pizzas;
    }

    public List<Massa> loadMassas() throws SQLException {
        List<Massa> massas = new ArrayList<>();
        query = "SELECT * FROM Massa";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Massa c = new Massa(rs.getInt("id_massa"),rs.getString("name"));
            massas.add(c);
        }
        return massas;
    }

    public List<Drink> loadDrinks() throws SQLException {
        List<Drink> drinks = new ArrayList<>();
        query = "SELECT * FROM Drink";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Drink d = new Drink(rs.getInt("id_drink"), rs.getString("name"));
            drinks.add(d);
        }
        return drinks;
    }

    public void getPizzaIngredients(Pizza pizza, List<Ingredient> ingredients) throws SQLException {
        query =  "SELECT DISTINCT ingredient FROM " +
                        "(SELECT name AS pizza_name, id_pizza, ingredient FROM Pizza NATURAL JOIN " +
                        "(SELECT name AS ingredient, id_pizza FROM " +
                        "(SELECT * FROM PizzaItem NATURAL JOIN Ingredient) AS x) AS y) AS z " +
                        "WHERE pizza_name =\"" + pizza.getName() + "\";";

        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            String ingredient = rs.getString("ingredient");
            pizza.addIngredient(ingredients.get(ingredients.indexOf(new Ingredient(ingredient))));
        }
    }

    public List<String> loadDelegations() throws SQLException {
        List<String> delegations = new ArrayList<>();
        query = "SELECT * FROM Delegation";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            String d = rs.getString("name");
            delegations.add(d);
        }
        return delegations;
    }

    public List<Ingredient> loadIngredients() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        query = "SELECT * FROM Ingredient";
        stt = conn.createStatement();
        rs = stt.executeQuery (query);

        while(rs.next()) {
            Ingredient i = new Ingredient(rs.getString("name"));
            ingredients.add(i);
        }
        return ingredients;
    }

    public Customer getCustomer(String phone) throws SQLException {
        query = "SELECT * FROM Customer WHERE phone_number = " + phone + ";";
        stt = conn.createStatement();
        rs = stt.executeQuery(query);
        if (!rs.next()) return null;
        return new Customer(rs.getInt("id_customer"), rs.getString("name"), rs.getString("surname1"), rs.getString("surname2"),
                rs.getString("phone_number"), rs.getString("address"), rs.getString("city"));

    }

    public int insertCustomer(Customer customer) throws SQLException {
        query = "INSERT INTO Customer (name, surname1, surname2, phone_number, address, city) VALUES ("
                + '\'' + customer.getName() + '\'' + ","
                + '\'' + customer.getSurname1() + '\'' + ","
                + '\'' + customer.getSurname2() + '\'' + ","
                + '\'' + customer.getPhoneNumber() + '\'' + ","
                + '\'' + customer.getAddress() + '\'' + ","
                + '\'' + customer.getCity() + '\''
                + ");";
        stt = conn.createStatement();
        stt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        rs = stt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException("KO. Creating new customer failed, no ID obtained.");
        }
    }

    public void insertOrder(Order order, Customer customer, Delegation delegation) throws SQLException {

        query = "INSERT INTO COrder (id_customer, id_delegation) VALUES ("
                + customer.getId() + ","
                + delegation.getId()
                + ");";

        stt = conn.createStatement();
        stt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        rs = stt.getGeneratedKeys();
        Integer orderID = -1;
        if (rs.next()) {
            orderID = rs.getInt(1);
        } else {
            throw new SQLException("KO. Creating new order failed, no ID obtained.");
        }

        for (OrderItem oi: order.getItems()) {
            if (oi.getDrink() == null) {
                query = "INSERT INTO OrderItem (id_pizza, id_massa, extra, id_order) VALUES ("
                        + oi.getPizza().getId() + ","
                        + oi.getPizza().getMassa().getId() + ","
                        + '\'' + oi.getExtra() + '\'' + "," +
                        + orderID
                        + ");";
                stt = conn.createStatement();
                stt.executeUpdate(query);

            } else {
                query = "INSERT INTO OrderItem (id_drink, id_order) VALUES ("
                        + oi.getDrink().getId() + ","
                        + orderID
                        + ");";
                stt = conn.createStatement();
                stt.executeUpdate(query);
            }

        }
    }

    public int getCustomerOrders(Customer customer) throws SQLException {
        query = "SELECT COUNT(*) AS num_ord FROM COrder WHERE id_customer = " + customer.getId() + ";";
        stt = conn.createStatement();
        rs = stt.executeQuery(query);
        if (!rs.next()) return -1;
        return rs.getInt("num_ord");
    }

   */
}

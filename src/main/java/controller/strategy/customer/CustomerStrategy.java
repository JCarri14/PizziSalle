package controller.strategy.customer;

import builders.v1.OrderBuilder;
import builders.v1.PizzaBuilder;
import builders.v2.AddressBuilder;
import builders.v2.UserBuilder;
import controller.SessionContext;
import db.DBConnectorFactory;
import db.enums.DBResponse;
import db.callbacks.DBCallback;
import db.enums.DBObject;
import db.enums.DBType;
import model.Model;
import model.delegation.Delegation;
import model.delegation.DelegationFactory;
import model.order.Order;
import model.order.OrderItem;
import model.pizza.Drink;
import model.pizza.Ingredient;
import model.pizza.Pizza;
import model.pizza.PizzaItem;
import model.user.Address;
import model.user.User;
import model.user.enums.UserLevel;
import controller.strategy.ControllerStrategy;
import view.View;
import view.components.CustomerComponents;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerStrategy implements ControllerStrategy, DBCallback {
    public static UserLevel userLevel = UserLevel.USER;

    private SessionContext context;
    private View view;
    private Model model;

    private OrderBuilder orderBuilder;

    public CustomerStrategy(View view, Model model) {
        this.view = view;
        this.model = model;
        this.context = SessionContext.getInstance();
        this.orderBuilder = new OrderBuilder();
    }

    @Override
    public void executeMenu() {
        welcomeCustomer();
        boolean keepRunning = true;
        while(keepRunning) {
            keepRunning = manageActions(requestAction());
        }
    }

    private void welcomeCustomer() {
        this.view.printMessage("Welcome!");
    }

    private int requestAction() {
        boolean isCorrect = false;
        int option = 0;
        while (!isCorrect) {
            this.view.printUserInformation(
                    context.getUserCredentials().getName(),
                    context.getDelegation().getName());

            option = Integer.parseInt(
                    this.view.prompt(CustomerComponents.createCustomerMenu()));
            isCorrect = option >= 1 && option <= 5;
            if (!isCorrect) this.view.printMessage("Invalid option. Enter correct value.\n");
        }
        return option;
    }

    private boolean manageActions(int action) {
        switch (action) {
            case 1:
                manageOrder();
                break;
            case 2:
                showCart();
                break;
            case 3:
                requestCredentials();
                break;
            case 4:
                requestDelegation();
                break;
            case 5:
                return false;
        }
        return true;
    }

    private void showCart() {
        this.view.printMessage(
                CustomerComponents.createPizzaCart(
                        this.model.getPizzas().stream().map(Pizza::getName).collect(Collectors.toList())
                ));
    }

    private void requestDelegation() {
        boolean isCorrect = false;
        int option = 0;
        Delegation d = null;

        while(!isCorrect) {
            option = Integer.parseInt(
                    this.view.prompt(CustomerComponents.createDelegationMenu()));
            isCorrect = option >= 1 && option <= 4;
            if (!isCorrect) this.view.printMessage("Invalid option. Enter correct value.\n");
        }
        d = DelegationFactory.getDelegation(option);
        this.context.setDelegation(d == null ? DelegationFactory.getDelegation(1): d);
    }

    private void requestCredentials() {
        boolean isCorrect = false;
        UserBuilder userBuilder;
        AddressBuilder addressBuilder;
        this.view.printMessage("**** User Credentials Form ****");
        String name = this.view.prompt("Name: ");
        String lastName = this.view.prompt("Surname: ");
        String streetName = this.view.prompt("Street name: ");
        String city = this.view.prompt("City: ");
        String postalCode = this.view.prompt("PostalCode: ");

        // Maybe add middleware-kinda-like-object to check valid params values
        User userCredentials = buildUser(name, lastName, streetName, city, postalCode);
        this.context.setUserCredentials(userCredentials);
    }

    private User buildUser(String name, String lastName, String streetName, String city, String postalCode) {
        Address address = new AddressBuilder()
                .with(builder -> {
                    builder.streetName = streetName;
                    builder.city = city;
                    builder.postalCode = postalCode;
                }).createAddress();

        return new UserBuilder()
                .with(personBuilder -> {
                    personBuilder.name = name;
                    personBuilder.lastName = lastName;
                    personBuilder.address = address;
                }).createUser();
    }

    private void requestAdvancedCredentials() {
        String cont = this.view.prompt("Do you want to set Advance parameters? [y] Yes, [n] No\nOption: ");
        if(cont.equals("n")){
        } else {

        }
    }

    private void requestPizza() {
        CustomerComponents.createPizzaCart(
                this.model.getPizzas().stream().map(Pizza::getName).collect(Collectors.toList()));
        int index = Integer.parseInt(this.view.prompt("Pizza(num): "));
        Pizza pizza = new PizzaBuilder()
                .withBasePizza(this.model.getPizzas().get(index-1))
                .withExtras(requestExtras())
                .createPizza();
        int quantity = Integer.parseInt(this.view.prompt("Quantity: "));
        orderBuilder = orderBuilder.withItem(
                new OrderItem(-1, quantity, pizza));

    }

    private List<PizzaItem> requestExtras() {
        this.view.printMessage("Do you want to add any extras? [Y] Yes ; [N] No");
        if (this.view.prompt("> ").toUpperCase().equals("N")) {
            return null;
        }
        boolean keepAdding = true;
        List<PizzaItem> res = new ArrayList<>();

        while (keepAdding) {
            CustomerComponents.createExtraCart(
                    this.model.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList()));
            int index = Integer.parseInt(this.view.prompt("Extra(num): "));
            int quantity = Integer.parseInt(this.view.prompt("Quantity: "));

            res.add(new PizzaItem(this.model.getIngredients().get(index-1), quantity));

            this.view.printMessage("Do you want to add more? [Y] Yes ; [N] No");
            if (this.view.prompt("> ").toUpperCase().equals("N")) {
                keepAdding = false;
            }
        }
        return res;
    }

    private void requestDrink() {
        CustomerComponents.createDrinkCart(
                this.model.getDrinks().stream().map(Drink::getName).collect(Collectors.toList()));
        int index = Integer.parseInt(this.view.prompt("Drink(num): "));
        int quantity = Integer.parseInt(this.view.prompt("Quantity: "));

        orderBuilder = orderBuilder.withItem(
                new OrderItem(-1, quantity, this.model.getDrinks().get(index-1))
        );
    }

    private void requestComment() {
        String comment = this.view.prompt("Comment: ");
        orderBuilder = orderBuilder.withComments(comment);
    }

    private int requestOrderItemOption() {
        boolean isCorrect = false;
        int option = -1;
        while (!isCorrect) {
            this.view.printMessage(CustomerComponents.createOrderOptions());
            option = Integer.parseInt(this.view.prompt("Option: "));
            isCorrect = option >= 1 && option <= 3;
            if (!isCorrect) this.view.printMessage("Invalid option. Enter correct value.\n");
        }
        return option;
    }

    private void manageOrderItems() {
        boolean keepRunning = true;

        while (keepRunning) {
            int option = requestOrderItemOption();
            switch (option) {
                case 1:
                    requestPizza();
                    break;
                case 2:
                    requestDrink();
                    break;
                case 3:
                    requestComment();
                    break;
                case 4:
                    keepRunning = false;
                    break;
            }
        }
    }

    private void manageOrder() {
        this.view.printMessage("**** Order Form ****");
        this.view.printMessage("Checking credentials...");
        if (this.context.getUserCredentials() == null) {
            this.view.printMessage("No credentials found. Launching credentials form...");
            requestCredentials();
        }
        this.view.printUserInformation(
                context.getUserCredentials().getName(),
                context.getDelegation().getName());

        // Start building the Order instance
        orderBuilder = orderBuilder
                .withCustomer(this.context.getUserCredentials())
                .withDelegation(this.context.getDelegation());

        manageOrderItems();

        Order order = orderBuilder.createOrder();
        try {
            DBConnectorFactory
                    .get(DBType.MYSQL)
                    .insert(DBObject.ORDER, order, this);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void onSuccess(Map<DBResponse, Object> res) {
        // do something...
    }

    @Override
    public void onFailure(Map<DBResponse, Object> res) {
        // do something...
    }
}

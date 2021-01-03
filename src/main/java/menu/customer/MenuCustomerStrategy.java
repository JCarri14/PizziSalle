package menu.customer;

import controller.SessionContext;
import model.Model;
import model.delegation.Delegation;
import model.delegation.DelegationFactory;
import model.order.OrderItem;
import model.user.Address;
import model.user.User;
import model.user.builder.AddressBuilder;
import model.user.builder.UserBuilder;
import model.user.enums.UserLevel;
import menu.MenuStrategy;
import view.View;
import view.components.ViewCustomerComponents;

import java.util.stream.Collectors;

public class MenuCustomerStrategy implements MenuStrategy {
    public static UserLevel userLevel = UserLevel.USER;

    private SessionContext context;
    private View view;
    private Model model;

    public MenuCustomerStrategy(View view, Model model) {
        this.view = view;
        this.model = model;
        this.context = SessionContext.getInstance();
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
                    context.getUserCredentials().getFirstName(),
                    context.getDelegation().getName());

            option = Integer.parseInt(
                    this.view.prompt(ViewCustomerComponents.createCustomerMenu()));
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
        this.view.printMessage(ViewCustomerComponents.createPizzaCart());
    }

    private void requestDelegation() {
        boolean isCorrect = false;
        int option = 0;
        Delegation d = null;

        while(!isCorrect) {
            option = Integer.parseInt(
                    this.view.prompt(ViewCustomerComponents.createDelegationMenu()));
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
                    personBuilder.firstName = name;
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
        ViewCustomerComponents.createPizzaCart(
                this.model.getPizzas().stream().map(OrderItem::getName).collect(Collectors.toList()));
        int option = Integer.parseInt(this.view.prompt("Pizza(num): "));
    }

    private void requestDrink() {
        ViewCustomerComponents.createDrinkCart(
                this.model.getDrinks().stream().map(OrderItem::getName).collect(Collectors.toList()));
        int option = Integer.parseInt(this.view.prompt("Drink(num): "));
    }

    private void requestComment() {
        String comment = this.view.prompt("Comment: ");
    }

    private int requestOrderItemOption() {
        boolean isCorrect = false;
        int option = -1;
        while (!isCorrect) {
            this.view.printMessage(ViewCustomerComponents.createOrderOptions());
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
                context.getUserCredentials().getFirstName(),
                context.getDelegation().getName());

        manageOrderItems();

    }
}

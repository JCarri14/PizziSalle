package strategy;

import controller.SessionContext;
import model.delegation.Delegation;
import model.delegation.DelegationFactory;
import model.user.Address;
import model.user.User;
import model.user.builder.AddressBuilder;
import model.user.builder.UserBuilder;
import model.user.enums.UserLevel;
import strategy.MenuStrategy;
import view.View;

public class MenuCustomerStrategy implements MenuStrategy {
    public static UserLevel userLevel = UserLevel.USER;

    private SessionContext context;
    private View view;

    public MenuCustomerStrategy(View view) {
        this.view = view;
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
            option = Integer.parseInt(
                    this.view.prompt("" +
                            "----------------------------------------------------------------" +
                            "\nClient name: " + context.getUserCredentials().getFirstName() +
                            "\nDelegation: " + context.getDelegation().getName() +
                            "\n----------------------------------------------------------------" +
                            "\nWhat do you want to do?\n" +
                            "\t[1] Make order\n" +
                            "\t[2] See cart\n" +
                            "\t[3] Update credentials\n" +
                            "\t[4] Update delegation\n" +
                            "\t[5] Exit\n" +
                            "Option: "));
            isCorrect = option >= 1 && option <= 5;
            if (!isCorrect) this.view.printMessage("Invalid option. Enter correct value.\n");
        }
        return option;
    }

    private boolean manageActions(int action) {
        switch (action) {
            case 1:

                break;
            case 2:

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

    private void requestDelegation() {
        boolean isCorrect = false;
        int option = 0;
        Delegation d = null;
        while(!isCorrect) {
            option = Integer.parseInt(
                    this.view.prompt(
                            "\nChoose your delegation:\n" +
                                    "\t[1]. Barcelona\n" +
                                    "\t[2]. Lleida\n" +
                                    "\t[3]. Tarragona\n" +
                                    "\t[4]. Girona\n" +
                                    "Delegacio: "));
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

    private void manageOrder() {

    }
}

package strategy;

import controller.SessionContext;
import model.delegation.Delegation;
import model.delegation.DelegationFactory;
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
        this.view.printMessage("Benvingut/da!");
    }

    private int requestAction() {
        boolean isCorrect = false;
        int option = 0;
        while (!isCorrect) {
            option = Integer.parseInt(
                    this.view.prompt("" +
                            "----------------------------------------------------------------" +
                            "\nNom client: " + context.getUserCredentials().getFirstName() +
                            "\nDelegacio: " + context.getDelegation().getName() +
                            "\n----------------------------------------------------------------" +
                            "\nQue vols fer?\n" +
                            "\t[1] Fer comanda\n" +
                            "\t[2] Veure carta\n" +
                            "\t[3] Introduir credencials\n" +
                            "\t[4] Actualitzar delegacio\n" +
                            "\t[5] Sortir\n" +
                            "Opcio: "));
            isCorrect = option >= 1 && option <= 5;
            if (!isCorrect) this.view.printMessage("Opcio invalida! Introdueix un valor correcte\n");
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
                            "\nSelecciona la teva delegacio\n" +
                                    "\t[1]. Barcelona\n" +
                                    "\t[2]. Lleida\n" +
                                    "\t[3]. Tarragona\n" +
                                    "\t[4]. Girona\n" +
                                    "Delegacio: "));
            isCorrect = option >= 1 && option <= 4;
            if (!isCorrect) this.view.printMessage("KO. Delegacio Incorrecta. Introdueix un valor valid.\n");
        }
        d = DelegationFactory.getDelegation(option);
        this.context.setDelegation(d == null ? DelegationFactory.getDelegation(1): d);
    }

    private void requestCredentials() {

    }

    private void manageOrder() {

    }
}

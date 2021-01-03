package controller;

import db.DBConnector;
import model.Model;
import strategy.MenuAdminStrategy;
import strategy.MenuStrategy;
import strategy.MenuWorkerStrategy;
import strategy.MenuCustomerStrategy;
import view.View;

import java.sql.SQLException;

public class Controller {
    private final Model model;
    private final View view;
    private SessionContext context;
    private DBConnector dbConnector;

    public Controller(Model model, View view) throws SQLException, ClassNotFoundException {
        this.model = model;
        this.view = view;
        this.context = SessionContext.getInstance();
        //this.dbConnector = DBConnector.getInstance();
    }

    public void execute() {
        view.showWelcomeText();
        boolean keepRunning = true;
        while (keepRunning) {
            int option = requestAccessType();
            if (option == 4) {
                break;
            }
            MenuStrategy strategy = getMenuStrategy(option);
            context.setMenuStrategy(strategy);
            strategy.executeMenu();
        }
    }

    private int requestAccessType() {
        boolean isCorrect = false;
        int option = 0;
        while (!isCorrect) {
            option = Integer.parseInt(this.view.prompt(
                    "\nEntry Point\n" +
                            "\t [1] Access as User\n" +
                            "\t [2] Access as Worker\n" +
                            "\t [3] Access as Admin\n" +
                            "\t [4] Exit\n" +
                            "\n Access Type: "));
            isCorrect = option >= 1 && option <= 4;
            if (!isCorrect) {
                this.view.printMessage("Invalid option, Range values: [1,3]");
            }
        }
        return option;
    }

    private MenuStrategy getMenuStrategy(int option) {
        switch (option) {
            case 1:
                return new MenuCustomerStrategy(view);
            case 2:
                return new MenuWorkerStrategy(view);
            case 3:
                return new MenuAdminStrategy(view);
        }
        return null;
    }


}

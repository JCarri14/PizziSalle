package controller;

import controller.strategy.customer.CustomerStrategy;
import db.DBConnector;
import db.DBConnectorFactory;
import db.model.DBType;
import db.repositories.PizzaRepository;
import model.Model;
import controller.strategy.admin.AdminStrategy;
import controller.strategy.ControllerStrategy;
import controller.strategy.worker.WorkerStrategy;
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
        this.dbConnector = DBConnectorFactory.get(DBType.MYSQL);
    }

    public void execute() {
        view.showWelcomeText();
        boolean keepRunning = true;
        while (keepRunning) {
            int option = requestAccessType();
            if (option == 4) {
                break;
            }
            ControllerStrategy strategy = getMenuStrategy(option);
            context.setMenuStrategy(strategy);
            strategy.executeMenu();
        }
    }

    public void finish() {
        this.model.stopObserving();
    }

    private int requestAccessType() {
        boolean isCorrect = false;
        int option = 0;
        while (!isCorrect) {
            option = Integer.parseInt(this.view.prompt(
                    "\nEntry Point\n" +
                            "\t[1] Access as User\n" +
                            "\t[2] Access as Worker\n" +
                            "\t[3] Access as Admin\n" +
                            "\t[4] Exit\n" +
                            "\nOption: "));
            isCorrect = option >= 1 && option <= 4;
            if (!isCorrect) {
                this.view.printMessage("Invalid option, Range values: [1,4]");
            }
        }
        return option;
    }

    /* Works as a Controller Strategy Factory */
    private ControllerStrategy getMenuStrategy(int option) {
        switch (option) {
            case 1:
                return new CustomerStrategy(view, model);
            case 2:
                return new WorkerStrategy(view);
            case 3:
                return new AdminStrategy(view);
        }
        return null;
    }


}


import controller.Controller;
import db.DBConnector;
import model.Model;
import view.View;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            //DBConnector dbConnector = DBConnectorFactory.create(DBType.MYSQL);

            Model model = new Model();

            View view = new View();

            Controller controller = new Controller(model, view);
            addShutDownHook();
            controller.execute();
            controller.finish();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("***************************************************");
            System.out.println("Tancant PizziSalle ...");
            System.out.println("***************************************************");

            try {
                //DBConnector.getInstance().disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }));
    }

}


import builders.v1.PizzaBuilder;
import controller.Controller;
import db.DBConnector;
import db.DBConnectorFactory;
import db.model.DBObject;
import db.model.DBType;
import db.mysql.MySQLManagerFactory;
import db.repositories.*;
import model.Model;
import model.delegation.Delegation;
import model.pizza.*;
import view.View;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        final String password = "pass";
        final String username = "user";
        try {
            DBConnector dbConnector = DBConnectorFactory.get(DBType.MYSQL);
            dbConnector.setCredentials(username, "pizzisalle", password);
            dbConnector.connect();

            Model model = new Model();
            PizzaRepository.getInstance().getAllPizzas(dbConnector, false, PizzaRepository.getInstance());
            IngredientRepository.getInstance().getAllIngredients(dbConnector);
            MassRepository.getInstance().getAllMasses(dbConnector);
            DrinkRepository.getInstance().getAllDrinks(dbConnector);
            DelegationRepository.getInstance().getAllDelegations(dbConnector);

            //if (!checkInitialPopulation()) {
            // Careful, only execute ONCE! Comment line after
            populateDatabase();
            //}

            View view = new View();

            Controller controller = new Controller(model, view);
            addShutDownHook();
            controller.execute();
            controller.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkInitialPopulation() {
        return false;
    }

    private static void populateDatabase() throws SQLException, ClassNotFoundException {
        DBConnector dbConnector = DBConnectorFactory.get(DBType.MYSQL);
        insertDelegation();
        insertIngredients();
        insertMasses();
        insertDrinks();
        insertPizza();
    }

    private static void insertIngredients() throws SQLException, ClassNotFoundException {
        DBConnector dbConnector = DBConnectorFactory.get(DBType.MYSQL);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Tomaquet"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Mozzarella'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Pernil'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Pinya'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Pollastre'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Bacon Crispy"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Frankfurt"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Bacon'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Ou"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Salsitxes'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Ceba'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Mini Burgers"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Tonyina'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Xoriço'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Olives'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Salsa BBQ"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Cheddar'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Roquefort'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Tomaquet Natural"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Anxoves'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Gambes'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Carn'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Salsa Carbonara"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Xampinyons'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Pernil Salat"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Brie'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Emmental'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Pepperoni'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Pebrot'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Carxofa'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Formatge Cabra"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Sobrassada'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("'Mel'"), null);
        dbConnector.insert(DBObject.INGREDIENT, new Ingredient("Tomaquet"), null);
    }

    private static void insertMasses() throws SQLException, ClassNotFoundException {
        DBConnector dbConnector = DBConnectorFactory.get(DBType.MYSQL);
        dbConnector.insert(DBObject.MASS, new Mass("Original"), null);
        dbConnector.insert(DBObject.MASS, new Mass("Prima"), null);
        dbConnector.insert(DBObject.MASS, new Mass("Gruixuda"), null);
    }

    private static void insertDrinks() throws SQLException, ClassNotFoundException {
        DBConnector dbConnector = DBConnectorFactory.get(DBType.MYSQL);
        dbConnector.insert(DBObject.DRINK, new Drink("Aigua"), null);
        dbConnector.insert(DBObject.DRINK, new Drink("Refresc"), null);
        dbConnector.insert(DBObject.DRINK, new Drink("Cervesa"), null);
    }

    private static void insertPizza() throws SQLException, ClassNotFoundException {
        DBConnector dbConnector = DBConnectorFactory.get(DBType.MYSQL);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Margarida").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Hawaiana").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Bacon Crispy").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Americana").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Traviata").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Burger").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Castellera").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Cowboy").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Vaquera").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Marinera").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Bbq").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Diablo").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Carbonara").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Serrana").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("4 formatges").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Pepperoni").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Vegetal").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Barcelona").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Girona").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Tarragona").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Lleida").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("6 formatges").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Mallorquina").createPizza(), null);
        dbConnector.insert(DBObject.PIZZA, new PizzaBuilder().withName("Carbonara Deluxe").createPizza(), null);
    }

    private static void insertDelegation() throws SQLException, ClassNotFoundException {
        DBConnector dbConnector = DBConnectorFactory.get(DBType.MYSQL);
        dbConnector.insert(DBObject.DELEGATION, new Delegation("Barcelona"), null);
        dbConnector.insert(DBObject.DELEGATION, new Delegation("Lleida"), null);
        dbConnector.insert(DBObject.DELEGATION, new Delegation("Tarragona"), null);
        dbConnector.insert(DBObject.DELEGATION, new Delegation("Girona"), null);
    }

    private static void insertPizzaItems() throws SQLException, ClassNotFoundException {
        DBConnector dbConnector = DBConnectorFactory.get(DBType.MYSQL);
        //dbConnector.insertWithParenId(DBObject.PIZZA_ITEM, new PizzaItem(), 1, null);
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

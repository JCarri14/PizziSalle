package db.mysql;

import db.model.DBObject;
import db.managers.MySQLEntityManager;
import db.mysql.managers.*;

import java.sql.Connection;

/**
 * @author Joan CA
 * @implNote Factory class from which retrieve a desired EntryManager class
 */
public class MySQLManagerFactory {

    public static MySQLEntityManager get(DBObject objectType, Connection conn) {
        switch (objectType) {
            case USER:
                return UserManager.getInstance(conn);
            case DELEGATION:
                return DelegationManager.getInstance(conn);
            case ORDER:
                return OrderManager.getInstance(conn);
            case ORDER_ITEM:
                return OrderItemManager.getInstance(conn);
            case PIZZA:
                return PizzaManager.getInstance(conn);
            case PIZZA_ITEM:
                return PizzaItemManager.getInstance(conn);
            case DRINK:
                return DrinkManager.getInstance(conn);
            case INGREDIENT:
                return IngredientManager.getInstance(conn);
            case MASS:
                return MassManager.getInstance(conn);
            default:
                return null;
        }
    }
}

package db.mysql;

import db.enums.DBObject;
import db.managers.MySQLManager;
import db.mysql.managers.*;

import java.sql.Connection;

public class MySQLManagerFactory {

    public static MySQLManager get(DBObject objectType, Connection conn) {
        switch (objectType) {
            case USER:
                return MySQLUserManager.getInstance(conn);
            case DELEGATION:
                return MySQLDelegationManager.getInstance(conn);
            case ORDER:
                return MySQLOrderManager.getInstance(conn);
            case ORDER_ITEM:
                //return MySQLOrderItemManager.getInstance(conn);
            case PIZZA:
                return MySQLPizzaManager.getInstance(conn);
            case PIZZA_ITEM:
                return MySQLPizzaItemManager.getInstance(conn);
            case DRINK:
                return MySQLDrinkManager.getInstance(conn);
            case INGREDIENT:
                return MySQLIngredientManager.getInstance(conn);
            case MASS:
                return MySQLMassManager.getInstance(conn);
            default:
                return null;
        }
    }
}

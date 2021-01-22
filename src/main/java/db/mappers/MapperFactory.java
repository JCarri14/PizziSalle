package db.mappers;

import db.model.DBObject;
import db.model.DBType;
import model.pizza.Pizza;
import model.user.Address;
import model.user.User;

public class MapperFactory {

    public static EntityMapper get(DBType dbType, DBObject dbObject) {
        switch (dbObject) {
            case USER:
                return new UserMapper(dbType);
            case DELEGATION:
                return new DelegationMapper(dbType);
            case ORDER:
                return new OrderMapper(dbType);
            case ORDER_ITEM:
                return new OrderItemMapper(dbType);
            case PIZZA:
                return new PizzaMapper(dbType);
            case PIZZA_ITEM:
                return new PizzaItemMapper(dbType);
            case DRINK:
                return new DrinkMapper(dbType);
            case INGREDIENT:
                return new IngredientMapper(dbType);
            case MASS:
                return new MassMapper(dbType);
        }
        return null;
    }
}

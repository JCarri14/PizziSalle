package db.mappers;

import db.model.DBType;
import model.delegation.Delegation;
import model.pizza.Drink;
import model.pizza.Mass;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrinkMapper extends EntityMapper<Drink>{

    public DrinkMapper(DBType dbType) {
        super(dbType);
    }

    @Override
    protected Drink mySQLResponseToEntity(ResultSet rs) {
        try {
            return new Drink(
                    Integer.parseInt(rs.getString("id_drink")),
                    rs.getString("name")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    protected Drink mongoResponseToEntity() {
        // to be implemented
        return null;
    }

    @Override
    public String mySQLInsert(Drink drink) {
        return "INSERT INTO Drink (name) VALUES ("
                + '\'' + drink.getName() + '\''
                + ");";
    }

    @Override
    public String mongoInsert(Drink drink) {
        return null;
    }

    @Override
    public String mySQLDelete(Drink drink) {
        return null;
    }

    @Override
    public String mongoDelete(Drink drink) {
        return null;
    }

    @Override
    public String mySQLGet(Drink drink) {
        return null;
    }

    @Override
    public String mongoGet(Drink drink) {
        return null;
    }

    @Override
    public String mySQLUpdate(Drink drink) {
        return null;
    }

    @Override
    public String mongoUpdate(Drink drink) {
        return null;
    }

}

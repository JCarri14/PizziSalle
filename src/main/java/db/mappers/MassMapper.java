package db.mappers;

import db.model.DBType;
import model.pizza.Ingredient;
import model.pizza.Mass;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MassMapper extends EntityMapper<Mass> {

    public MassMapper(DBType dbType) {
        super(dbType);
    }

    @Override
    protected Mass mySQLResponseToEntity(ResultSet rs) {
        try {
            return new Mass(
                    Integer.parseInt(rs.getString("id_mass")),
                    rs.getString("name")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    protected Mass mongoResponseToEntity() {
        // to be implemented
        return null;
    }

    @Override
    public String mySQLInsert(Mass mass) {
        return "INSERT INTO Mass (name) VALUES ("
                + '\'' + mass.getName() + '\''
                + ");";
    }

    @Override
    public String mongoInsert(Mass mass) {
        return null;
    }

    @Override
    public String mySQLDelete(Mass mass) {
        return null;
    }

    @Override
    public String mongoDelete(Mass mass) {
        return null;
    }

    @Override
    public String mySQLGet(Mass mass) {
        return null;
    }

    @Override
    public String mongoGet(Mass mass) {
        return null;
    }

    @Override
    public String mySQLUpdate(Mass mass) {
        return null;
    }

    @Override
    public String mongoUpdate(Mass mass) {
        return null;
    }
}

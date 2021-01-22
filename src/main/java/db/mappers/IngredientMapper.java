package db.mappers;

import db.model.DBType;
import model.pizza.Drink;
import model.pizza.Ingredient;
import model.pizza.Mass;
import model.pizza.Pizza;
import model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper extends EntityMapper<Ingredient> {

    public IngredientMapper(DBType dbType) {
        super(dbType);
    }

    @Override
    protected Ingredient mySQLResponseToEntity(ResultSet rs) {
        try {
            return new Ingredient(
                    Integer.parseInt(rs.getString("id_ingredient")),
                    rs.getString("name"),
                    rs.getString("description")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    protected Ingredient mongoResponseToEntity() {
        return null;
    }

    @Override
    public String mySQLInsert(Ingredient ingredient) {
        return "INSERT INTO Ingredient (name, description) VALUES ("
                + '\'' + ingredient.getName() + '\'' + ","
                + '\'' + ingredient.getDescription() + '\''
                + ");";
    }

    @Override
    public String mongoInsert(Ingredient ingredient) {
        return null;
    }

    @Override
    public String mySQLDelete(Ingredient ingredient) {
        return null;
    }

    @Override
    public String mongoDelete(Ingredient ingredient) {
        return null;
    }

    @Override
    public String mySQLGet(Ingredient ingredient) {
        return null;
    }

    @Override
    public String mongoGet(Ingredient ingredient) {
        return null;
    }

    @Override
    public String mySQLUpdate(Ingredient ingredient) {
        return null;
    }

    @Override
    public String mongoUpdate(Ingredient ingredient) {
        return null;
    }
}

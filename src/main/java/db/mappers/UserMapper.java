package db.mappers;

import db.model.DBType;
import model.user.User;
import builders.v2.UserBuilder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends EntityMapper<User> {

    public UserMapper(DBType dbType) {
        super(dbType);
    }

    @Override
    protected User mySQLResponseToEntity(ResultSet rs) {
        return new UserBuilder()
                .with(personBuilder -> {
                    try {
                        personBuilder.id = Integer.parseInt(rs.getString("id"));
                        personBuilder.name = rs.getString("name");
                        personBuilder.middleName = rs.getString("middle_name");
                        personBuilder.lastName = rs.getString("last_name");
                        personBuilder.age = Integer.parseInt(rs.getString("age"));
                        personBuilder.phoneNumber = rs.getString("phone_number");
                        personBuilder.email = rs.getString("email");
                        personBuilder.createdAt = Date.valueOf(rs.getString("created_at"));
                        personBuilder.isActive = Boolean.parseBoolean(rs.getString("is_active"));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }).createUser();
    }

    @Override
    protected User mongoResponseToEntity() {
        return null;
    }

    @Override
    public String mySQLInsert(User user) {
        return "INSERT INTO User (name, middle_name, last_name, age, phone_number, email, created_at, is_active) VALUES ("
                + '\'' + user.getName() + '\'' + ","
                + '\'' + user.getMiddleName() + '\'' + ","
                + '\'' + user.getLastName() + '\'' + ","
//                + '\'' + user.getAddress().getId() + '\'' + ","
                + '\'' + user.getAge() + '\'' + ","
                + '\'' + user.getPhoneNumber() + '\'' + ","
                + '\'' + user.getEmail() + '\'' + ","
                + "NOW()" + ","
                + "true"
                + ");";
    }

    @Override
    public String mongoInsert(User user) {
        return null;
    }

    @Override
    public String mySQLDelete(User user) {
        return null;
    }

    @Override
    public String mongoDelete(User user) {
        return null;
    }

    @Override
    public String mySQLGet(User user) {
        return null;
    }

    @Override
    public String mongoGet(User user) {
        return null;
    }

    @Override
    public String mySQLUpdate(User user) {
        return null;
    }

    @Override
    public String mongoUpdate(User user) {
        return null;
    }
}

package db.mappers;

import model.user.User;
import builders.v2.UserBuilder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper  {

    public static User MySQLResponseToObject(ResultSet rs) {
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

    public static String ObjectToMySQLQuery(User user) {
        return "INSERT INTO Customer (name, middle_name, last_name, address_id, age, phone_number, email, created_at, is_active) VALUES ("
                + '\'' + user.getName() + '\'' + ","
                + '\'' + user.getMiddleName() + '\'' + ","
                + '\'' + user.getLastName() + '\'' + ","
                + '\'' + user.getAddress().getId() + '\'' + ","
                + '\'' + user.getAge() + '\'' + ","
                + '\'' + user.getPhoneNumber() + '\'' + ","
                + '\'' + user.getEmail() + '\'' + ","
                + "NOW()" + ","
                + "true"
                + ");";
    }
}

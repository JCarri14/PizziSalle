package model.user.builder;

import model.user.Address;
import model.user.User;

import java.sql.Timestamp;
import java.util.function.Consumer;

public class UserBuilder {
    public int id;
    public String firstName;
    public String middleName;
    public String lastName;
    public int age;
    public String phoneNumber;
    public String email;
    public Address address;
    public Timestamp createdAt;
    public Timestamp lastAccess;
    public String password;
    public boolean isActive;

    public UserBuilder with(Consumer<UserBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public User createUser() {
        return new User(id, firstName, middleName, lastName, age,
                phoneNumber, email, address, createdAt,
                lastAccess, password, isActive);
    }
}

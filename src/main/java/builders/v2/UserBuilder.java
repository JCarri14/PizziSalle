package builders.v2;

import model.user.Address;
import model.user.User;

import java.sql.Date;
import java.util.function.Consumer;

public class UserBuilder {
    public int id;
    public String name;
    public String middleName;
    public String lastName;
    public int age;
    public String phoneNumber;
    public String email;
    public Address address;
    public Date createdAt;
    public boolean isActive;

    public UserBuilder with(Consumer<UserBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public User createUser() {
        return new User(id, name, middleName, lastName, age,
                phoneNumber, email, address, createdAt, isActive);
    }
}

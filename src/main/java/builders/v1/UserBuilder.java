package builders.v1;

import model.user.Address;
import model.user.User;

import java.sql.Date;

public class UserBuilder {
    protected int id;
    protected String name;
    protected String middleName;
    protected String lastName;
    protected int age;
    protected String phoneNumber;
    protected String email;
    protected Address address;
    protected Date createdAt;
    protected boolean isActive;

    public UserBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public UserBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserBuilder withIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public User createUser() {
        return new User(id, name, middleName, lastName, age,
                phoneNumber, email, address, createdAt, isActive);
    }

}

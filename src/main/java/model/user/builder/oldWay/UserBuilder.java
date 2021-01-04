package model.user.builder.oldWay;

import model.user.Address;
import model.user.User;

import java.sql.Timestamp;

public class UserBuilder {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;
    private Address address;
    private Timestamp createdAt;
    private Timestamp lastAccess;
    private String password;
    private boolean isActive;

    public UserBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
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

    public UserBuilder withCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserBuilder withLastAccess(Timestamp lastAccess) {
        this.lastAccess = lastAccess;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public User createUser() {
        return new User(id, firstName, middleName, lastName, age,
                phoneNumber, email, address, createdAt,
                lastAccess, password, isActive);
    }

}

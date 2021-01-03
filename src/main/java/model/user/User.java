package model.user;

import java.sql.Timestamp;

public class User {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;
    private Address address;
    private boolean isFemale;
    private boolean isHomeOwner;
    private Timestamp createdAt;
    private Timestamp lastAccess;
    private int userLevel;
    private int permissionLevel;
    private boolean isActive;

    public User(int id, String firstName, String middleName, String lastName, int age, String phoneNumber,
                String email, Address address, boolean isFemale, boolean isHomeOwner, Timestamp createdAt,
                Timestamp lastAccess, int userLevel, int permissionLevel, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.isFemale = isFemale;
        this.isHomeOwner = isHomeOwner;
        this.createdAt = createdAt;
        this.lastAccess = lastAccess;
        this.userLevel = userLevel;
        this.permissionLevel = permissionLevel;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale(boolean female) {
        isFemale = female;
    }

    public boolean isHomeOwner() {
        return isHomeOwner;
    }

    public void setHomeOwner(boolean homeOwner) {
        isHomeOwner = homeOwner;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Timestamp lastAccess) {
        this.lastAccess = lastAccess;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

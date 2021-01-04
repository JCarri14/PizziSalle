package model.user;

import java.sql.Timestamp;

public class Worker extends User{
    private String iban;
    private int permissionLevel;

    public Worker(int id, String firstName, String middleName, String lastName, int age, String phoneNumber, String email, Address address, Timestamp createdAt, Timestamp lastAccess, String password, boolean isActive) {
        super(id, firstName, middleName, lastName, age, phoneNumber, email, address, createdAt, lastAccess, password, isActive);
    }
}

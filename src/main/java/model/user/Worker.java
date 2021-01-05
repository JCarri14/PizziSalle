package model.user;

import java.sql.Date;

public class Worker extends User{
    private String password;
    private Date lastAccess;
    private int permissionLevel;

    public Worker(int id, String name, String middleName, String lastName, int age, String phoneNumber, String email, Address address, Date createdAt, boolean isActive, String password, Date lastAccess, int permissionLevel) {
        super(id, name, middleName, lastName, age, phoneNumber, email, address, createdAt, isActive);
        this.password = password;
        this.lastAccess = lastAccess;
        this.permissionLevel = permissionLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}

package builders.v1;

import model.user.Worker;

import java.sql.Date;

public class WorkerBuilder extends UserBuilder{

    private String password;
    private Date lastAccess;
    private int permissionLevel;

    public WorkerBuilder withLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
        return this;
    }

    public WorkerBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public WorkerBuilder withPermissionLevel(int level) {
        this.permissionLevel = level;
        return this;
    }

    public Worker createWorker() {
        return new Worker(id, name, middleName, lastName, age,
                phoneNumber, email, address, createdAt, isActive,
                password, lastAccess, permissionLevel);
    }

}

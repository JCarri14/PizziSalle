package builders.v2;

import model.user.Address;
import model.user.Worker;

import java.sql.Date;
import java.util.function.Consumer;

public class WorkerBuilder {
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
    /* BEGIN - WORKER PARAMS */
    public String password;
    public Date lastAccess;
    public int permissionLevel;

    public WorkerBuilder with(Consumer<WorkerBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public Worker createWorker() {
        return new Worker(id, name, middleName, lastName, age,
                phoneNumber, email, address, createdAt, isActive,
                password, lastAccess, permissionLevel);
    }
}

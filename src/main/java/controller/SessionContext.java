package controller;

import db.enums.DBType;
import model.delegation.Delegation;
import model.delegation.DelegationFactory;
import model.user.User;
import builders.v2.UserBuilder;
import controller.strategy.ControllerStrategy;

public class SessionContext {
    private static SessionContext instance;

    private ControllerStrategy controllerStrategy;
    private User userCredentials;
    private Delegation delegation;
    private DBType dbType;

    public static SessionContext getInstance() {
        if (instance == null) {
            synchronized (SessionContext.class) {
                if (instance == null) {
                    instance = new SessionContext();
                }
            }
        }
        return instance;
    }

    private SessionContext() {
        this.userCredentials = new UserBuilder().createUser();
        this.delegation = DelegationFactory.getDelegation(1);
        this.dbType = DBType.MYSQL;
    }

    public ControllerStrategy getMenuStrategy() {
        return controllerStrategy;
    }

    public void setMenuStrategy(ControllerStrategy controllerStrategy) {
        this.controllerStrategy = controllerStrategy;
    }

    public User getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(User userCredentials) {
        this.userCredentials = userCredentials;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }

    public DBType getDbType() {
        return dbType;
    }

    public void setDbType(DBType dbType) {
        this.dbType = dbType;
    }
}

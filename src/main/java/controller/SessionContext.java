package controller;

import model.delegation.Delegation;
import model.delegation.DelegationFactory;
import model.user.User;
import model.user.builder.UserBuilder;
import strategy.MenuStrategy;

public class SessionContext {
    private static SessionContext instance;

    private MenuStrategy menuStrategy;
    private User userCredentials;
    private Delegation delegation;

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
    }

    public MenuStrategy getMenuStrategy() {
        return menuStrategy;
    }

    public void setMenuStrategy(MenuStrategy menuStrategy) {
        this.menuStrategy = menuStrategy;
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
}

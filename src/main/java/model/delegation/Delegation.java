package model.delegation;

import db.enums.DBObject;

public class Delegation {
    public final static DBObject type = DBObject.DELEGATION;
    private Integer id;
    private String name;

    public Delegation(Integer id) {
        this.id = id;
    }

    public Delegation(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

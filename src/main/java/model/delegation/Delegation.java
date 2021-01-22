package model.delegation;

import db.model.DBObject;

public class Delegation {
    public static DBObject TYPE = DBObject.DELEGATION;
    private Integer id;
    private String name;

    public Delegation(Integer id) {
        this.id = id;
    }

    public Delegation(String name) {
        this.name = name;
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

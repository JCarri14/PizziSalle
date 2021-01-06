package model.pizza;

import db.enums.DBObject;

public class Mass {
    public final static DBObject type = DBObject.MASS;
    private Integer id;
    private String name;

    public Mass(Integer id) {
        this.id = id;
    }

    public Mass(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}

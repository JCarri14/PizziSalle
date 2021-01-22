package model.pizza;

import db.model.DBObject;

public class Mass {
    public static DBObject TYPE = DBObject.MASS;
    private Integer id;
    private String name;

    public Mass(Integer id) {
        this.id = id;
    }

    public Mass(String name) {
        this.name = name;
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

package model.pizza;

public class Mass {
    private Integer id;
    private String name;

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

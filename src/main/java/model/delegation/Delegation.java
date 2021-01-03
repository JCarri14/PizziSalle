package model.delegation;

public abstract class Delegation {
    private Integer id;
    private String name;

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

    public abstract String getSpecialPizza();
}

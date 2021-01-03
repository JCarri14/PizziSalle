package model.delegation;


public class Lleida extends Delegation {
    public Lleida(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String getSpecialPizza() {
        return "Lleida";
    }
}

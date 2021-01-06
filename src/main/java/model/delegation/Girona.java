package model.delegation;

import model.delegation.Delegation;

public class Girona extends Delegation {
    public Girona(Integer id, String name) {
        super(id, name);
    }

    public String getSpecialPizza() {
        return "Girona";
    }
}

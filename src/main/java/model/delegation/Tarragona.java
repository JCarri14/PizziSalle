package model.delegation;

import model.delegation.Delegation;

public class Tarragona extends Delegation {
    public Tarragona(Integer id, String name) {
        super(id, name);
    }

    public String getSpecialPizza() {
        return "Tarragona";
    }
}

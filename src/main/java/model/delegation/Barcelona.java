package model.delegation;

import model.delegation.Delegation;

public class Barcelona extends Delegation {
    public Barcelona(Integer id, String name) {
        super(id, name);
    }

    public String getSpecialPizza() {
        return "Barcelona";
    }
}

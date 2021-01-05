package model.pizza;

import model.order.OrderItem;
import model.order.OrderType;

public enum DrinkType {
    WATER("Water"),
    REFRESH ("Refresh"),
    BEER("Beer");

    private final String type;

    DrinkType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}

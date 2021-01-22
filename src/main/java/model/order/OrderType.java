package model.order;

public enum OrderType {
    PIZZA("Pizza"),
    DRINK("Drink"),
    DESSERT("Dessert"),
    UNDEFINED("Undefined");

    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}

package model.pizza;

public enum PizzaType {
    MARGARIDA("Margarida"),
    HAWAIANA("Hawaiana"),
    BACON_CRISPY("Bacon Crispy"),
    AMERICANA("Americana"),
    TRAVIATA("Traviata"),
    BURGUER("Burguer"),
    CASTELLERA("Castellera"),
    COWBOY("Cowboy"),
    VAQUERA("Vaquera"),
    MARINERA("Marinera"),
    BBQ("BBQ"),
    DIABLO("Diablo"),
    CARBONARA("Carbonara"),
    SERRANA("Serrana"),
    FOUR_CHEESE("4 Cheese"),
    PEPPERONI("Pepperoni"),
    VEGETAL("Veggetal"),
    BARCELONA("Barcelona"),
    GIRONA("Girona"),
    TARRAGONA("Tarragona"),
    LLEIDA("Lleida"),
    SIX_CHEESE("6 Cheese"),
    MALLORQUINA("Mallorquina"),
    CARBONARA_DELUXE("Carbonara Deluxe");

    private final String type;

    PizzaType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}

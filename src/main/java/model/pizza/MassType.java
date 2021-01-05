package model.pizza;

public enum MassType {
    THICK("Thick"),
    ORIGINAL("Original"),
    THIN("Thin");

    private final String type;

    MassType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}

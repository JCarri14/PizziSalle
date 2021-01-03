package model.delegation;

public enum DelegationCentral {
    BARCELONA("Barcelona"),
    TARRAGONA("Tarragona"),
    LLEIDA("Lleida"),
    GIRONA("Girona");

    private String text;

    DelegationCentral(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}

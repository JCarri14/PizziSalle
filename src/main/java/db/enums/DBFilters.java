package db.enums;

public enum DBFilters {
    ID("id"),
    NAME("name"),
    MIDDLE_NAME("middle_name"),
    LAST_NAME("last_name"),
    AGE("age"),
    EMAIL("email"),
    PHONE_NUMBER("phone_number"),
    QUANTITY("quantity");

    private String filter;

    DBFilters(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return this.filter;
    }
}

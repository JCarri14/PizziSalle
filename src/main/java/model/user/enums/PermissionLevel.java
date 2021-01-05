package model.user.enums;

public enum PermissionLevel {
    FULL(3),
    READ_WRITE(2),
    READ_ONLY(1),
    NO_ACCESS(0);

    private final int level;

    PermissionLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}

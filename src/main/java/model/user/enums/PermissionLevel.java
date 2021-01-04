package model.user.enums;

public enum PermissionLevel {
    FULL(4),
    READ_WRITE(3),
    READ_ONLY(2),
    NO_ACCESS(1);

    private final int level;

    PermissionLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}

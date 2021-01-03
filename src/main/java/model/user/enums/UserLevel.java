package model.user.enums;

public enum UserLevel {
    ADMIN(3),
    WORKER(2),
    USER(1);

    private final int level;

    UserLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}

package com.figo.enums;

public enum CarStatus {
    IN_RENT(-1) , NOT_ON_RENT(0) , BROKEN(-10);
    private final int level;

    CarStatus(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

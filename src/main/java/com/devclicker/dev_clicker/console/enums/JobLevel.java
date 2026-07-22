package com.devclicker.dev_clicker.console.enums;

public enum JobLevel {
    INTERN(50, 5),
    JUNIOR(75, 10),
    MID(125, 15),
    SENIOR(200, 25);

    private final int moneyCost;
    private final int codePerRound;

    JobLevel(int moneyCost, int codePerRound) {
        this.moneyCost = moneyCost;
        this.codePerRound = codePerRound;
    }

    public int getMoneyCost() {
        return moneyCost;
    }

    public int getCodePerRound() {
        return codePerRound;
    }
}

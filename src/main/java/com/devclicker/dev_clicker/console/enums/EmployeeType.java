package com.devclicker.dev_clicker.console.enums;

public enum EmployeeType {
    INTERN(50, 5),
    JUNIOR(75, 10),
    MID(125, 15),
    SENIOR(200, 25);

    private final Integer moneyCost;
    private final Integer codePerRound;

    EmployeeType(Integer moneyCost, Integer codePerRound) {
        this.moneyCost = moneyCost;
        this.codePerRound = codePerRound;
    }

    public Integer getMoneyCost() {
        return moneyCost;
    }

    public Integer getCodePerRound() {
        return codePerRound;
    }
}

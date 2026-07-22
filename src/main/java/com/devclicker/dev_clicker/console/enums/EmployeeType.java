package com.devclicker.dev_clicker.console.enums;

public enum EmployeeType {
    JUNIOR(100, 1),
    MID(500, 5),
    SENIOR(2000, 20);

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

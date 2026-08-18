package com.devclicker.dev_clicker.console.enums;

import java.math.BigDecimal;

public enum EmployeeType {
    JUNIOR(new BigDecimal(100), 1),
    MID(new BigDecimal(500), 5),
    SENIOR(new BigDecimal(2000), 20);

    private final BigDecimal moneyCost;
    private final Integer codePerRound;

    EmployeeType(BigDecimal moneyCost, Integer codePerRound) {
        this.moneyCost = moneyCost;
        this.codePerRound = codePerRound;
    }

    public BigDecimal getMoneyCost() {
        return moneyCost;
    }

    public Integer getCodePerRound() {
        return codePerRound;
    }
}

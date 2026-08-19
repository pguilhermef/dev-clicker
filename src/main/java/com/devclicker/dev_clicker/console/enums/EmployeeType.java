package com.devclicker.dev_clicker.console.enums;

import java.math.BigDecimal;

public enum EmployeeType {
    JUNIOR(new BigDecimal(100), 1L),
    MID(new BigDecimal(500), 5L),
    SENIOR(new BigDecimal(2000), 20L);

    private final BigDecimal moneyCost;
    private final Long codePerRound;

    EmployeeType(BigDecimal moneyCost, Long codePerRound) {
        this.moneyCost = moneyCost;
        this.codePerRound = codePerRound;
    }

    public BigDecimal getMoneyCost() {
        return moneyCost;
    }

    public Long getCodePerRound() {
        return codePerRound;
    }
}

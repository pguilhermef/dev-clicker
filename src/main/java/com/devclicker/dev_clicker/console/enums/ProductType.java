package com.devclicker.dev_clicker.console.enums;

import java.math.BigDecimal;

public enum ProductType {
    SITE(BigDecimal.valueOf(100), 1),
    AUTOMATION(BigDecimal.valueOf(500), 5),
    APP(BigDecimal.valueOf(2000), 20);

    private final BigDecimal moneyPerRound;
    private final Integer codeCost;

    ProductType(BigDecimal moneyPerRound, Integer codeCost) {
        this.moneyPerRound = moneyPerRound;
        this.codeCost = codeCost;
    }

    public BigDecimal getMoneyPerRound() {
        return moneyPerRound;
    }

    public Integer getCodeCost() {
        return codeCost;
    }
}

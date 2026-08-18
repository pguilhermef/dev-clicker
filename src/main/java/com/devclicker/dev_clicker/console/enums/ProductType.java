package com.devclicker.dev_clicker.console.enums;

import java.math.BigDecimal;

public enum ProductType {
    SITE(BigDecimal.valueOf(100), 10),
    AUTOMATION(BigDecimal.valueOf(500), 50),
    APP(BigDecimal.valueOf(2000), 200);

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

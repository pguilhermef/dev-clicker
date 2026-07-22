package com.devclicker.dev_clicker.console.enums;

public enum ProductType {
    SITE(100, 1),
    AUTOMATION(500, 5),
    APP(2000, 20);

    private final Integer moneyPerRound;
    private final Integer codeCost;

    ProductType(Integer moneyPerRound, Integer codeCost) {
        this.moneyPerRound = moneyPerRound;
        this.codeCost = codeCost;
    }

    public Integer getMoneyPerRound() {
        return moneyPerRound;
    }

    public Integer getCodeCost() {
        return codeCost;
    }
}

package com.devclicker.dev_clicker.console.model;

import com.devclicker.dev_clicker.console.enums.ProductType;

import java.math.BigDecimal;

public class Product {
    private ProductType productType;
    private BigDecimal incomePerMinute;

    Product(ProductType productType) {
        this.productType = productType;
        this.incomePerMinute = productType.getMoneyPerRound();
    }

    public ProductType getProductType() {
        return productType;
    }

    public BigDecimal getIncomePerMinute() {
        return incomePerMinute;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setIncomePerMinute(BigDecimal incomePerMinute) {
        this.incomePerMinute = incomePerMinute;
    }
}

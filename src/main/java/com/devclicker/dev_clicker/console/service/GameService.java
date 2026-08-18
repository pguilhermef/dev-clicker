package com.devclicker.dev_clicker.console.service;

import com.devclicker.dev_clicker.console.enums.ProductType;
import com.devclicker.dev_clicker.console.model.Company;

public class GameService {
    private Company company;

    public GameService(Company company) {
        this.company = company;
    }

    public void buyProduct(ProductType productType) {
        Long totalOfCode = company.getCode();

        if (totalOfCode < productType.getCodeCost()) {
            System.out.println("Insufficient code");
        } else {
            company.setCode(totalOfCode - productType.getCodeCost());
            company.addProduct(productType);
        }
    }
}

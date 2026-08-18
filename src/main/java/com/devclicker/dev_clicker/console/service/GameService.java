package com.devclicker.dev_clicker.console.service;

import com.devclicker.dev_clicker.console.enums.EmployeeType;
import com.devclicker.dev_clicker.console.enums.ProductType;
import com.devclicker.dev_clicker.console.model.Company;

import java.math.BigDecimal;

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

    public void hireEmployee(EmployeeType employeeType) {
        BigDecimal totalOfMoney = company.getMoney();

        if (totalOfMoney.compareTo(employeeType.getMoneyCost()) < 0){
            System.out.println("Insufficient money");
        } else {
            company.setMoney(totalOfMoney.subtract(employeeType.getMoneyCost()));
        }
    }
}

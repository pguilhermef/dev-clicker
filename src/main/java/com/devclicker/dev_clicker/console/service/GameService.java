package com.devclicker.dev_clicker.console.service;

import com.devclicker.dev_clicker.console.enums.EmployeeType;
import com.devclicker.dev_clicker.console.enums.ProductType;
import com.devclicker.dev_clicker.console.exception.InsufficientCodeException;
import com.devclicker.dev_clicker.console.exception.InsufficientMoneyException;
import com.devclicker.dev_clicker.console.model.Company;

import java.math.BigDecimal;

public class GameService {
    private Company company;

    public GameService(Company company) {
        this.company = company;
    }

    public void buyProduct(ProductType productType) {
        Long totalOfCode = company.getCode();
        String productTypeName = productType.name().toLowerCase();
        String confirmationMessageOfCreationApp = "You built one" + productTypeName;

        if (totalOfCode < productType.getCodeCost()) {
            System.out.println("Insufficient Code");
        } else {
            company.addProduct(productType);
            company.setCode(totalOfCode - productType.getCodeCost());

            System.out.println(confirmationMessageOfCreationApp);
        }

    }

    public void hireEmployee(EmployeeType employeeType) {
        BigDecimal totalOfMoney = company.getMoney();
        String professionalTypeName = employeeType.name().toLowerCase();
        String confirmationMessageOfHire = "You hired one " + professionalTypeName + " profissional";

        if (totalOfMoney.compareTo(employeeType.getMoneyCost()) < 0){
            System.out.println("Insufficient Money");
        } else {
            company.setMoney(totalOfMoney.subtract(employeeType.getMoneyCost()));
            company.addEmployee(employeeType);

            System.out.println(confirmationMessageOfHire);
        }
    }
}

package com.devclicker.dev_clicker.console.service;

import com.devclicker.dev_clicker.console.model.Company;
import com.devclicker.dev_clicker.console.model.Employee;
import com.devclicker.dev_clicker.console.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductionService {
    private Company company;

    public ProductionService(Company company) {
        this.company = company;
    };

    private void calculateEmployees() {
        Long totalOfCode = company.getCode();

        List<Employee> employees = company.getEmployees();

        for(Employee e : employees) {
            totalOfCode += e.getCodePerMinute();
        }

        company.setCode(totalOfCode);
    }

    private void calculateMoney() {
        BigDecimal totalOfMoney = company.getMoney();

        List<Product> products = company.getProducts();

        for(Product p : products){
            totalOfMoney = totalOfMoney.add(p.getIncomePerMinute());
        }

        company.setMoney(totalOfMoney);
    }

    public void calculateResources() {
        calculateEmployees();
        calculateMoney();
    }
}


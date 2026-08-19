package com.devclicker.dev_clicker.console.service;

import com.devclicker.dev_clicker.console.model.Company;
import com.devclicker.dev_clicker.console.model.Employee;

import java.util.List;

public class ProductionService {
    private Company company;

    public ProductionService(Company company){
        this.company = company;
    };

    public void calculateEmployees(){
        Long totalOfCode = company.getCode();

        List<Employee> employees = company.getEmployees();

        for(Employee e : employees){
            totalOfCode += e.getCodePerMinute();
        }

        company.setCode(totalOfCode);
        System.out.println(totalOfCode);
    }
}


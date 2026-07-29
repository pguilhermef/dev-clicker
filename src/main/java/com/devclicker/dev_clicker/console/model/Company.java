package com.devclicker.dev_clicker.console.model;

import com.devclicker.dev_clicker.console.enums.EmployeeType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private LocalDateTime startDate;
    private Long code;
    private BigDecimal money;
    private List <Employee> employees;
    private List <Product> products;

    public Company(
     String name
    ) {
        this.name = name;
        this.startDate = LocalDateTime.now();
        this.code = 0L;
        this.money = BigDecimal.ZERO;
        this.employees = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Long getCode() {
        return code;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setCode(Long code){
        this.code = code;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

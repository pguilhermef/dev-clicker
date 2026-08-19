package com.devclicker.dev_clicker.console.model;

import com.devclicker.dev_clicker.console.enums.EmployeeType;
import com.devclicker.dev_clicker.console.enums.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private Player ceo;
    private String name;
    private LocalDateTime startDate;
    private Long code;
    private BigDecimal money;
    private List <Employee> employees;
    private List <Product> products;

    public Company(Player ceo, String name) {
        this.ceo = ceo;
        this.name = name;
        this.startDate = LocalDateTime.now();
        this.code = 0L;
        this.money = BigDecimal.ZERO;
        this.employees = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public Player getCeo() {
        return ceo;
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

    public void addEmployee(EmployeeType employeeType) {
        Employee employee = new Employee(employeeType);
        this.employees.add(employee);
    }

    public void addProduct(ProductType productType) {
        Product product = new Product(productType);
        this.products.add(product);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

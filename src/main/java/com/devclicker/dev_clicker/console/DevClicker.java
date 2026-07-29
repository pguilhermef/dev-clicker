package com.devclicker.dev_clicker.console;

import com.devclicker.dev_clicker.console.enums.EmployeeType;
import com.devclicker.dev_clicker.console.enums.ProductType;
import com.devclicker.dev_clicker.console.model.Company;
import com.devclicker.dev_clicker.console.model.Employee;
import com.devclicker.dev_clicker.console.model.Player;

import java.util.ArrayList;
import java.util.List;

public class DevClicker {
    public static void main(String[] args) {
        Company oxyanCompany = new Company("Oxyan");
        Player gui = new Player("Paulo", oxyanCompany);
        Employee empregado = new Employee(EmployeeType.JUNIOR);

        List<Employee> profissionais = new ArrayList<>();

        oxyanCompany.setCode(5L);
        oxyanCompany.addEmployee(empregado);

        System.out.println(gui.getName());
        System.out.println(gui.getCompany().getName());
        System.out.println(gui.getCompany().getCode());
        System.out.println(ProductType.valueOf("SITE").getCodeCost());
        System.out.println(gui.getCompany().getEmployees());

    }
}

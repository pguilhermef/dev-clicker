package com.devclicker.dev_clicker.console;

import com.devclicker.dev_clicker.console.enums.EmployeeType;
import com.devclicker.dev_clicker.console.enums.ProductType;
import com.devclicker.dev_clicker.console.model.Company;
import com.devclicker.dev_clicker.console.model.Employee;
import com.devclicker.dev_clicker.console.model.Player;
import com.devclicker.dev_clicker.console.model.Product;
import com.devclicker.dev_clicker.console.service.GameService;

import java.util.ArrayList;
import java.util.List;

public class DevClicker {
    public static void main(String[] args) {
        Company oxyanCompany = new Company("Oxyan");
        Player gui = new Player("Paulo", oxyanCompany);
        Employee empregado = new Employee(EmployeeType.JUNIOR);
        GameService gameService = new GameService(oxyanCompany);

        oxyanCompany.setCode(500L);

        System.out.println(gui.getName());
        System.out.println(gui.getCompany().getName());
        System.out.println(gui.getCompany().getCode());

        System.out.println(ProductType.valueOf("SITE").getCodeCost());

        gameService.buyProduct(ProductType.SITE);

        for (Product p : gui.getCompany().getProducts()) {
            System.out.println(p.getProductType());
        }

        for (Employee e : gui.getCompany().getEmployees()) {
            System.out.println(e.getEmployeeType());
            System.out.println(e.getCodePerMinute());
            System.out.println("---      ");
        }

        System.out.println(gui.getCompany().getCode());

    }
}

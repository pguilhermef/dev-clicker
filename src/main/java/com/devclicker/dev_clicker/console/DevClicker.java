package com.devclicker.dev_clicker.console;

import com.devclicker.dev_clicker.console.enums.EmployeeType;
import com.devclicker.dev_clicker.console.enums.ProductType;
import com.devclicker.dev_clicker.console.model.Company;
import com.devclicker.dev_clicker.console.model.Employee;
import com.devclicker.dev_clicker.console.model.Player;
import com.devclicker.dev_clicker.console.model.Product;
import com.devclicker.dev_clicker.console.service.GameService;
import com.devclicker.dev_clicker.console.service.ProductionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DevClicker {
    public static void main(String[] args) {
        Company oxyanCompany = new Company("Oxyan");
        Player gui = new Player("Paulo", oxyanCompany);
        Employee empregado = new Employee(EmployeeType.JUNIOR);
        GameService gameService = new GameService(oxyanCompany);
        ProductionService productionService = new ProductionService(oxyanCompany);

        oxyanCompany.setCode(5000L);
        oxyanCompany.setMoney(new BigDecimal("5000"));

        System.out.println("Jogador: " + gui.getName());
        System.out.println("Empresa: " +gui.getCompany().getName());
        System.out.println("Total de Código: " +gui.getCompany().getCode());
        System.out.println("Total de Dinheiro: " +gui.getCompany().getMoney());

        gameService.buyProduct(ProductType.SITE);
        gameService.hireEmployee(EmployeeType.JUNIOR);

        System.out.println("Calculando código...");

        productionService.calculateResources();
        System.out.println("Total de Código: " +gui.getCompany().getCode());
        System.out.println("Total de Dinheiro: " +gui.getCompany().getMoney());

        System.out.println(gui.getCompany().getCode());

    }
}

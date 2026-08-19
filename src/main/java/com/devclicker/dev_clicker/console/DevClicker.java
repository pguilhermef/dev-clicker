package com.devclicker.dev_clicker.console;

import com.devclicker.dev_clicker.console.enums.EmployeeType;
import com.devclicker.dev_clicker.console.enums.ProductType;
import com.devclicker.dev_clicker.console.model.Company;
import com.devclicker.dev_clicker.console.model.Employee;
import com.devclicker.dev_clicker.console.model.Player;
import com.devclicker.dev_clicker.console.model.Product;
import com.devclicker.dev_clicker.console.service.GameService;
import com.devclicker.dev_clicker.console.service.ProductionService;
import com.devclicker.dev_clicker.console.ui.MenuDisplay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DevClicker {
    public static void main(String[] args) {
        boolean continueGame = true;

        Player gui = new Player("Paulo");
        Company oxyanCompany = new Company(gui, "Oxyan");
        MenuDisplay menuDisplay = new MenuDisplay(oxyanCompany);

        GameService gameService = new GameService(oxyanCompany);
        ProductionService productionService = new ProductionService(oxyanCompany);

        menuDisplay.showMainMenu();
        menuDisplay.showCompanyStatus();
    }
}

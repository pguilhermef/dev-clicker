package com.devclicker.dev_clicker.console.utils;

import com.devclicker.dev_clicker.console.model.Company;
import com.devclicker.dev_clicker.console.service.ProductionService;
import com.devclicker.dev_clicker.console.ui.MenuDisplay;

import java.util.Scanner;

public class ConsoleUtils {
    public static void waitForMainOption(MenuDisplay menuDisplay) {
        Scanner scanner = new Scanner(System.in);
        int selectedOption = scanner.nextInt();
        ProductionService productionService = new ProductionService(menuDisplay.getCompany());

        switch (selectedOption) {
            case 0:
                exitGame(menuDisplay.getCompany());
                break;
            case 4:
                menuDisplay.showCompanyStatus();
                break;
            default:
                productionService.calculateResources();

        }
    }

    public static void waitForEnter(Scanner scanner) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Pressione ENTER para continuar...  ║");
        System.out.println("╚══════════════════════════════════════╝");

        scanner.nextLine();
    }

    public static void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static void exitGame(Company company) {
        company.getCeo().setWantsContinueGame(false);
    }
}

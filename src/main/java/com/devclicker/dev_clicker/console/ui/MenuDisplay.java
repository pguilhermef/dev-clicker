package com.devclicker.dev_clicker.console.ui;

import com.devclicker.dev_clicker.console.model.Company;

public class MenuDisplay {
    private Company company;

    public MenuDisplay(Company company) {
        this.company = company;
    }

    public void display() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         DEV CLICKER v1.0             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  CEO : " + company.getCeo().getName());
        System.out.println("  Empresa : " + company.getName());
        System.out.println("  Código (</>): " + company.getCode());
        System.out.println("  Dinheiro ($): " + company.getMoney());
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ 1. Trabalhar                         ║");
        System.out.println("║ 2. Criar produto </>                 ║");
        System.out.println("║ 3. Contratar profissional $          ║");
        System.out.println("║ 4. Status da empresa                 ║");
        System.out.println("║ 0. Sair                              ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}

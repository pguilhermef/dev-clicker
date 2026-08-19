package com.devclicker.dev_clicker.console.ui;

import com.devclicker.dev_clicker.console.model.Company;

public class MenuDisplay {
    private Company company;

    public MenuDisplay(Company company) {
        this.company = company;
    }

    public void showMainMenu() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         DEV CLICKER v1.0             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  CEO: " + company.getCeo().getName());
        System.out.println("  Empresa: " + company.getName());
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

    public void showCompanyStatus() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           COMPANY DETAILS            ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println(" ");
        System.out.println("  Nome do CEO: " + company.getCeo().getName());
        System.out.println("  Nome da Empresa: " + company.getName());
        System.out.println("  Total de Código: " + company.getCode());
        System.out.println("  Total de Dinheiro: " + company.getMoney());
        System.out.println("  Geração de Código por rodada: " + company.getCodePerRound());
        System.out.println("  Poder de Receita por rodada: " + company.getMoneyPerRound());
        System.out.println("  Profissionais Contratados: " + company.getEmployees().size());
        System.out.println("  Produtos Desenvolvidos: " + company.getProducts().size());
        System.out.println(" ");
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       Pressione qualquer tecla       ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}

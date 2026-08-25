package com.devclicker.dev_clicker.console.ui;

import com.devclicker.dev_clicker.console.enums.EmployeeType;
import com.devclicker.dev_clicker.console.enums.ProductType;
import com.devclicker.dev_clicker.console.model.Company;

import java.util.Scanner;

import static com.devclicker.dev_clicker.console.utils.ConsoleUtils.clearConsole;
import static com.devclicker.dev_clicker.console.utils.ConsoleUtils.waitForEnter;

public class MenuDisplay {
    private Company company;

    public MenuDisplay(Company company) {
        this.company = company;
    }

    public void showMainMenu() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           DEV CLICKER MENU           ║");
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
        waitForEnter(new Scanner(System.in));
        clearConsole();
    }

    public void showHireOptions() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║              DEVINKEDIN              ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  Junior - Custa $:" + EmployeeType.JUNIOR.getMoneyCost() + " Gera </>: " + EmployeeType.JUNIOR.getCodePerRound());
        System.out.println("  Mid - Custa $:" + EmployeeType.MID.getMoneyCost() + " Gera </>: " + EmployeeType.MID.getCodePerRound());
        System.out.println("  Senior - Custa $:" + EmployeeType.SENIOR.getMoneyCost() + " Gera </>: " + EmployeeType.SENIOR.getCodePerRound());
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ 1. Junior                            ║");
        System.out.println("║ 2. Mid/Pleno                         ║");
        System.out.println("║ 3. Senior                            ║");
        System.out.println("║ 0. Voltar ao Menu Principal          ║");
        System.out.println("╚══════════════════════════════════════╝");
        waitForEnter(new Scanner(System.in));
        clearConsole();
    }

    public void showAppsOptions() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║              DEVINKEDIN              ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  Site - Custa </>:" + ProductType.SITE.getCodeCost() + " Gera $: " + ProductType.SITE.getMoneyPerRound());
        System.out.println("  Automação - Custa </>:" + ProductType.AUTOMATION.getCodeCost() + " Gera $: " + ProductType.AUTOMATION.getMoneyPerRound());
        System.out.println("  Aplicativo - Custa </>:" + ProductType.APP.getCodeCost() + " Gera $: " + ProductType.APP.getMoneyPerRound());
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ 1. Site                              ║");
        System.out.println("║ 2. Automação                         ║");
        System.out.println("║ 3. Aplicativo                        ║");
        System.out.println("║ 0. Voltar ao Menu Principal          ║");
        System.out.println("╚══════════════════════════════════════╝");
        waitForEnter(new Scanner(System.in));
        clearConsole();
    }

    // Precisamos criar a função de seleção de criação de contratação e criação de produtos

    public Company getCompany() {
        return company;
    }
}

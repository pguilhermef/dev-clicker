package com.devclicker.dev_clicker.console.ui;

import java.util.Scanner;

import static com.devclicker.dev_clicker.console.utils.ConsoleUtils.clearConsole;
import static com.devclicker.dev_clicker.console.utils.ConsoleUtils.waitForEnter;

public class ProfileForm {
    private Scanner scanner = new Scanner(System.in);
    private String companyName;
    private String ceoName;

    public void welcome() {
        System.out.println("══════════════════════════════════════");
        System.out.println("       BEM VINDO AO DEV CLICKER!      ");
        System.out.println("══════════════════════════════════════");
        System.out.println(" ");
        System.out.println("Aqui você será o dono de uma empresa de tecnologia.");
        System.out.println(" ");
        System.out.println("""
                Seu objetivo é gerar código por meio do trabalho duro,\s
                usar código para desenvolver produtos que irão gerar renda e então \s
                contratar profissionais para gerar mais código.""");
        System.out.println(" ");

        waitForEnter(scanner);
        clearConsole();

        System.out.println("Primeiro de tudo, qual será o nome da sua empresa? (Escolha bem, pois depois que registrarmos firma no cartório, não será possível alterar.)");
        companyName = scanner.nextLine();
        clearConsole();


        System.out.println("Muito bem! Esse é um ótimo nome. Agora, qual será o nome do grande CEO da " + companyName + "?");
        ceoName = scanner.nextLine();
        clearConsole();

        System.out.println("Uau, " + ceoName + ", CEO da " + companyName + ". Parece perfeito.");

        waitForEnter(scanner);
        clearConsole();
    }

    public String getCeoName() {
        return ceoName;
    }

    public String getCompanyName() {
        return companyName;
    }
}

package com.devclicker.dev_clicker.console.ui;

import java.util.Scanner;

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
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      Pressione qualquer tecla...     ║");
        System.out.println("╚══════════════════════════════════════╝");

        scanner.next();

        System.out.println("Primeiro de tudo, qual será o nome da sua empresa?");
        System.out.println(" ");
        System.out.println("(Escolha bem, pois depois que registrarmos firma no cartório, não será possível alterar.)");
        scanner.nextLine();
        companyName = scanner.nextLine();


        System.out.println("Muito bem! Esse é um ótimo nome. Agora, qual será o nome do grande CEO da " + companyName + "?");
        ceoName = scanner.nextLine();

        System.out.println("Uau, " + ceoName + " CEO da " + companyName + ". Parece perfeito.");

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      Pressione qualquer tecla...     ║");
        System.out.println("╚══════════════════════════════════════╝");

        scanner.next();
    }

    public String getCeoName() {
        return ceoName;
    }

    public String getCompanyName() {
        return companyName;
    }
}

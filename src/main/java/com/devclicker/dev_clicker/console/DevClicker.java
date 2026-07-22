package com.devclicker.dev_clicker.console;

import com.devclicker.dev_clicker.console.model.Company;
import com.devclicker.dev_clicker.console.model.Player;

public class DevClicker {
    public static void main(String[] args) {
        Company Oxyan = new Company("Oxyan");
        Player gui = new Player("Paulo", Oxyan);

        System.out.println(gui.getName());
        System.out.println(gui.getCompany().getName());
    }
}

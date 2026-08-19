package com.devclicker.dev_clicker.console;

import com.devclicker.dev_clicker.console.model.Company;
import com.devclicker.dev_clicker.console.model.Player;
import com.devclicker.dev_clicker.console.service.GameService;
import com.devclicker.dev_clicker.console.service.ProductionService;
import com.devclicker.dev_clicker.console.ui.MenuDisplay;
import com.devclicker.dev_clicker.console.ui.ProfileForm;

import java.util.Scanner;

public class DevClicker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueGame = true;
        ProfileForm profileForm = new ProfileForm();

        profileForm.welcome();

        Player player = new Player(profileForm.getCeoName());
        Company company = new Company(player, profileForm.getCompanyName());

        MenuDisplay menuDisplay = new MenuDisplay(company);

        menuDisplay.showMainMenu();
        menuDisplay.showCompanyStatus();
    }
}

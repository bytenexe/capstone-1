package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class DadloonDock {
    private static Scanner scanner = new Scanner(System.in);
    private static CaptainsLog captainsLog = new CaptainsLog();

    public static void main(String[] args) {
        showBanner();
        homeScreen();
        System.out.println("Fair winds! Your Dadloons are safe in the vault.");
    }

    private static void showBanner() {
        System.out.println("  CAPTAIN DAVE'S DADLOON DOCK");
        System.out.println("================================");
    }

    private static void homeScreen() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== THE DOCK =====");
            System.out.println("D) Haul In a Dadloon");
            System.out.println("P) Pay the Harbor Master");
            System.out.println("L) Captain's Log");
            System.out.println("X) Sail Away");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("D")) {
                addDeposit();
            } else if (choice.equals("P")) {
                makePayment();
            } else if (choice.equals("L")) {
                ledgerScreen();
            } else if (choice.equals("X")) {
                running = false;
            } else {
                System.out.println("That is not a heading, sailor. Try D, P, L, or X.");
            }
        }
    }

    private static void ledgerScreen() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== CAPTAIN'S LOG =====");
            System.out.println("A) Full Log");
            System.out.println("D) Jokes Told");
            System.out.println("P) Dadloons Spent");
            System.out.println("H) Home Port");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("A")) {
                displayTransactions("FULL LOG", captainsLog.getAll());
            } else if (choice.equals("D")) {
                displayTransactions("JOKES TOLD", captainsLog.getDeposits());
            } else if (choice.equals("P")) {
                displayTransactions("DADLOONS SPENT", captainsLog.getPayments());
            } else if (choice.equals("H")) {
                running = false;
            } else {
                System.out.println("That is not a heading, sailor. Try A, D, P, or H.");
            }
        }
    }

    private static void displayTransactions(String title, List<LogEntry> entries) {
        System.out.println("\n--- " + title + " ---");
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
        } else {
            for (LogEntry entry : entries) {
                System.out.printf("%s %s | %-20s | %-30s | $%.2f%n", entry.getDate(), entry.getTime(), entry.getVendor(), entry.getDescription(), entry.getAmount());
            }
        }
    }

    private static void addDeposit() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
        System.out.print("Enter time (HH:MM:SS): ");
        String time = scanner.nextLine().trim();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine().trim();
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter amount: ");
        String amountStr = scanner.nextLine().trim();

        try {
            double amount = Double.parseDouble(amountStr);
            LogEntry newEntry = new LogEntry(date, time, description, vendor, amount);
            captainsLog.addEntry(newEntry); // 
            System.out.println("Dadloon hauled in!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please use numbers only.");
        }
    }

    private static void makePayment() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
        System.out.print("Enter time (HH:MM:SS): ");
        String time = scanner.nextLine().trim();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine().trim();
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter amount: ");
        String amountStr = scanner.nextLine().trim();

        try {
            double amount = Double.parseDouble(amountStr);
            LogEntry newEntry = new LogEntry(date, time, description, vendor, -amount);
            captainsLog.addEntry(newEntry);
            System.out.println("Payment made to the Harbor Master.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please use numbers only.");
        }
    }
}

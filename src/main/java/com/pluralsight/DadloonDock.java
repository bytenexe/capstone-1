package com.pluralsight;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class (DadloonDock).
 * Handles the User Interface, menu screens, and user interaction for Captain Dave's financial ledger.
 */
public class DadloonDock {
    private static Scanner scanner = new Scanner(System.in);
    private static CaptainsLog captainsLog = new CaptainsLog();

    /**
     * Launches the application, displays the banner, starts the home screen menu,
     * and prints a farewell message when exiting.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        showBanner(); // Used only once
        homeScreen(); //Used only once
        System.out.println("Fair winds! Your Dadloons are safe in the vault.");
    }

    /**
     * Displays the app banner title screen.
     */
    private static void showBanner() {
        System.out.println("  CAPTAIN DAVE'S DADLOON DOCK");
        System.out.println("================================");
    }

    /**
     * Runs the main dock menu loop allowing the user to navigate between deposits, 
     * payments, the ledger, or exiting the application.
     */
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

            // Prompts the user for an input, converting lowercase inputs to uppercase
            String choice = scanner.nextLine().trim().toUpperCase();

            // Switch case set up to toggle through the Home Screen options
            switch (choice) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> ledgerScreen();
                case "X" -> running = false;
                default -> System.out.println("That is not a heading, sailor. Try D, P, L, or X.");
            }
        }
    }

    /**
     * Opens the Ledger menu (L) from the Home Screen.
     * Lets the user view different filtered views of their records (Full Log, Jokes Told / Deposits, Dadloons Spent / Payments).
     */
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

            // Switch case to toggle through the Captain's Log mini-menu views
            switch (choice) {
                case "A" -> displayTransactions("FULL LOG", captainsLog.getAll());
                case "D" -> displayTransactions("JOKES TOLD", captainsLog.getDeposits());
                case "P" -> displayTransactions("DADLOONS SPENT", captainsLog.getPayments());
                case "H" -> running = false;
                default -> System.out.println("That is not a heading, sailor. Try A, D, P, or H.");
            }
        }
    }

    /**
     * Formats and prints a table of transactions with spacing, printing floats, and titles.
     *
     * @param title    The title banner for the transaction list.
     * @param entries  The list of LogEntry items to display.
     */
    private static void displayTransactions(String title, List<LogEntry> entries) {
        System.out.println("\n--- " + title + " ---");
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
        } else {
            for (LogEntry entry : entries) {
                System.out.printf("%s %s | %-20s | %-30s | $%.2f%n", // %-int is how many characters to the left field
                        entry.getDate(), entry.getTime(), entry.getVendor(), entry.getDescription(), entry.getAmount());
            }
        }
    }

    /**
     * Prompts the user for deposit details and records a positive amount (Dadloons hauled in).
     */
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

        /**
         * Try/catch block to handle accidental typos like entering letters instead of numbers.
         * Designed with accessibility in mind (dyslexia-friendly error recovery).
         */
        try {
            double amount = Double.parseDouble(amountStr);
            LogEntry newEntry = new LogEntry(date, time, description, vendor, amount);
            captainsLog.addEntry(newEntry);
            System.out.println("Dadloon hauled in!");
        } catch (NumberFormatException e) {
            System.out.println("Yarr, invalid Dadloons. Please use numbers only.");
        }
    }

    /**
     * Prompts the user for payment details and converts the entered amount into a negative value (-amount) for money spent.
     */
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

        /**
         * Try/catch block to handle accidental typos like entering letters instead of numbers.
         * Designed with accessibility in mind (dyslexia-friendly error recovery).
         */
        try {
            double amount = Double.parseDouble(amountStr);
            LogEntry newEntry = new LogEntry(date, time, description, vendor, -amount);
            captainsLog.addEntry(newEntry);
            System.out.println("Payment made to the Harbor Master.");
        } catch (NumberFormatException e) {
            System.out.println("Yarr, invalid Dadloons. Please use numbers only.");
        }
    }
}
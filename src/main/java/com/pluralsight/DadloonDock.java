package com.pluralsight;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Main application class (DadloonDock).
 * Handles the User Interface, menu screens, and user interaction for Captain Dave's financial ledger.
 **/
public class DadloonDock {
    private static Scanner scanner = new Scanner(System.in);
    private static CaptainsLog captainsLog = new CaptainsLog();

    /**
     * Launches the application, displays the banner, starts the home screen menu and prints a farewell message when exiting.
     *
     * @param args Command line arguments (not used).
     **/
    public static void main(String[] args) {
        showBanner(); // Used only once
        homeScreen(); //Used only once
        System.out.println("Fair winds! Your Dadloons are safe in the vault.");
    }

    /**
     * Displays the app banner title screen.
     **/
    private static void showBanner() {
        System.out.println("  CAPTAIN DAVE'S DADLOON DOCK");
        System.out.println("================================");
    }

    /**
     * Runs the Main Menu loop allowing the user to navigate between deposits payments, the ledger, or exiting the application.
     **/
    private static void homeScreen() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== THE DOCK =====");
            System.out.println("D) Haul In a Dadloon");
            System.out.println("P) Pay the Harbor Master");
            System.out.println("L) Captain's Log");
            System.out.println("X) Sail Away");
            System.out.print("Set yer course: ");

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
     **/
    private static void ledgerScreen() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== CAPTAIN'S LOG =====");
            System.out.println("A) Full Log");
            System.out.println("D) Jokes Told");
            System.out.println("P) Dadloons Spent");
            System.out.println("R) Arrr-chived Dad-abase");
            System.out.println("H) Home Port");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            // Switch case to toggle through the Captain's Log mini-menu views
            switch (choice) {
                case "A" -> displayTransactions("FULL LOG", captainsLog.getAll());
                case "D" -> displayTransactions("JOKES TOLD", captainsLog.getDeposits());
                case "P" -> displayTransactions("DADLOONS SPENT", captainsLog.getPayments());
                case "R" -> reportsScreen();
                case "H" -> running = false;
                default -> System.out.println("That is not a heading, sailor. Try A, D, P, R, or H.");
            }
        }
    }

    /**
     * Runs reports (R) menu from the ledger (L) screen
     * Lets the user flip through pre-defined searches (Month to Date, Previous Month, Year to Date, etc.).
     **/
    private static void reportsScreen() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== ARR-CHIVE DAD-ABASE =====");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Sail Away");
            System.out.print("Choose yer chart, mate: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    monthToDate();
                    promptToContinue();
                }
                case "2" -> {
                    previousMonth();
                    promptToContinue();
                }
                case "3" -> {
                    yearToDate();
                    promptToContinue();
                }
                case "4" -> {
                    previousYear();
                    promptToContinue();
                }
                case "5" -> {
                    searchByVendor();
                    promptToContinue();
                }
                case "0" -> running = false;
                default -> System.out.println("No such entry in the captain's log!");
            }
        }
    }

    /**
     * Pauses the report view so the user can inspect entries before returning to the Reports menu.
     **/
    private static void promptToContinue() {
        System.out.println("\nPress Enter to return to the arr-chive dad-abase.");
        scanner.nextLine();
    }

    /**
     * Formats and prints a table of transactions with spacing, printing floats, and titles.
     *
     * @param title   The title banner for the transaction list.
     * @param entries The list of LogEntry items to display.
     **/
    private static void displayTransactions(String title, List<LogEntry> entries) {
        System.out.println("\n--- " + title + " ---");
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
        } else {
            for (LogEntry entry : entries) {
                System.out.printf("%s %s | %-20s | %-25s | $%.2f%n",
                        entry.getDate(), entry.getTime(),
                        entry.getVendor(),
                        entry.getDescription(),
                        entry.getAmount());
            }
        }
    }

    /**
     * Prompts the user for deposit details and records a positive amount (Dadloons hauled in).
     **/
    private static void addDeposit() {
        String date = "";
        while (date.isEmpty()) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            date = scanner.nextLine().trim();
            if (date.isEmpty() || !isValidDate(date)) {
                System.out.println("Arrr! Invalid date. Use YYYY-MM-DD.");
                date = "";
            }
        }

        String time = "";
        while (time.isEmpty()) {
            System.out.print("Enter time (HH:MM:SS): ");
            time = scanner.nextLine().trim();
            if (time.isEmpty() || !isValidTime(time)) {
                System.out.println("Shiver me timbers, invalid time! Use HH:MM:SS. Try again:");
                time = "";
            }
        }

        String vendor = "";
        while (vendor.isEmpty()) {
            System.out.print("Enter vendor: ");
            vendor = scanner.nextLine().trim();
            if (vendor.isEmpty()) {
                System.out.println("Avast! Who be payin' or gettin' paid? Name your vendor!");
            }
        }

        String description = "";
        while (description.isEmpty()) {
            System.out.print("Enter description: ");
            description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("Write a tale for the log! Description can't be blank.");
            }
        }

        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Enter amount: ");
            String amountStr = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(amountStr);
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Yarr, invalid Dadloons. Please use numbers only.");
            }
        }

        LogEntry newEntry = new LogEntry(date, time, description, vendor, amount);
        captainsLog.addEntry(newEntry);
        System.out.println("Dadloon hauled in!");
    }

    /**
     * Prompts the user for payment details and converts the entered amount into a negative value (-amount) for money spent.
     **/
    private static void makePayment() {
        String date = "";
        while (date.isEmpty()) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            date = scanner.nextLine().trim();
            if (date.isEmpty() || !isValidDate(date)) {
                System.out.println("Arrr! Invalid date. Use YYYY-MM-DD. Try again.");
                date = "";
            }
        }

        String time = "";
        while (time.isEmpty()) {
            System.out.print("Enter time (HH:MM:SS): ");
            time = scanner.nextLine().trim();
            if (time.isEmpty() || !isValidTime(time)) {
                System.out.println("Shiver me timbers, invalid time! Use HH:MM:SS. Try again:");
                time = "";
            }
        }

        String vendor = "";
        while (vendor.isEmpty()) {
            System.out.print("Enter vendor: ");
            vendor = scanner.nextLine().trim();
            if (vendor.isEmpty()) {
                System.out.println("Avast! Who be payin' or gettin' paid? Name your vendor!");
            }
        }

        String description = "";
        while (description.isEmpty()) {
            System.out.print("Enter description: ");
            description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("Write a tale for the log! Description can't be blank.");
            }
        }

        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Enter amount: ");
            String amountStr = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(amountStr);
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Yarr, invalid Dadloons. Use numbers only, or walk the plank!.");
            }
        }

        LogEntry newEntry = new LogEntry(date, time, description, vendor, -amount);
        captainsLog.addEntry(newEntry);
        System.out.println("Loot surrendered to the Harbor Master.");
    }

    /**
     * Prompts user to enter a vendor and displays a specific vendor by calling the captainsLog object.
     **/
    private static void searchByVendor() {
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine().trim();
        List<LogEntry> results = captainsLog.getEntriesByVendor(vendor);
        displayTransactions("VENDOR: " + vendor.toUpperCase(), results);
    }

    /**
     * Validates the date string format using LocalDate.
     **/
    private static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates the time string format using LocalTime.
     **/
    private static boolean isValidTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Filters and displays transactions from the first day of the current month up to today.
     **/
    private static void monthToDate() {
        LocalDate today = LocalDate.now();
        List<LogEntry> allEntries = captainsLog.getAll();
        List<LogEntry> filtered = new java.util.ArrayList<>();

        for (LogEntry entry : allEntries) {
            LocalDate entryDate = LocalDate.parse(entry.getDate());
            if (entryDate.getYear() == today.getYear() &&
                    entryDate.getMonthValue() == today.getMonthValue() &&
                    !entryDate.isAfter(today)) {
                filtered.add(entry);
            }
        }
        displayTransactions("MONTH TO DATE", filtered);
    }

    /**
     * Filters and displays transactions from the previous calendar month.
     **/
    private static void previousMonth() {
        LocalDate today = LocalDate.now();
        LocalDate targetMonth = today.minusMonths(1);
        List<LogEntry> allEntries = captainsLog.getAll();
        List<LogEntry> filtered = new java.util.ArrayList<>();

        for (LogEntry entry : allEntries) {
            LocalDate entryDate = LocalDate.parse(entry.getDate());
            if (entryDate.getYear() == targetMonth.getYear() &&
                    entryDate.getMonthValue() == targetMonth.getMonthValue()) {
                filtered.add(entry);
            }
        }
        displayTransactions("PREVIOUS MONTH", filtered);
    }

    /**
     * Filters and displays transactions from January 1st of the current year up to today.
     **/
    private static void yearToDate() {
        LocalDate today = LocalDate.now();
        List<LogEntry> allEntries = captainsLog.getAll();
        List<LogEntry> filtered = new java.util.ArrayList<>();

        for (LogEntry entry : allEntries) {
            LocalDate entryDate = LocalDate.parse(entry.getDate());
            if (entryDate.getYear() == today.getYear() && !entryDate.isAfter(today)) {
                filtered.add(entry);
            }
        }
        displayTransactions("YEAR TO DATE", filtered);
    }

    /**
     * Filters and displays transactions across the entire previous calendar year.
     **/
    private static void previousYear() {
        int targetYear = LocalDate.now().getYear() - 1;
        List<LogEntry> allEntries = captainsLog.getAll();
        List<LogEntry> filtered = new java.util.ArrayList<>();

        for (LogEntry entry : allEntries) {
            LocalDate entryDate = LocalDate.parse(entry.getDate());
            if (entryDate.getYear() == targetYear) {
                filtered.add(entry);
            }
        }
        displayTransactions("PREVIOUS YEAR", filtered);
    }
}
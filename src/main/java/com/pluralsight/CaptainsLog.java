package com.pluralsight;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CaptainsLog
 * Acts as the data engine and file manager for the application.
 * Handles loading transactions from CSV, storing them in memory, and filtering records.
 */
public class CaptainsLog {
    Scanner scanner = new Scanner(System.in);
    private List<LogEntry> entries;

    /**
     * Constructor runs when a CaptainsLog object is created.
     * Initializes an empty ArrayList and immediately calls loadTransactions() to read the CSV.
     */
    public CaptainsLog() {
        this.entries = new ArrayList<>();
        loadTransactions();
    }

    public List<LogEntry> getAll() {
        List<LogEntry> sortedEntries = new ArrayList<>(entries);
        Comparator<LogEntry> byDateThenTime = Comparator // Comparator simplifies lambdas using the :: method reference operator
                .comparing(LogEntry::getDate) // Comparison rule for LogEntry objects
                .thenComparing(LogEntry::getTime); // if LogEntryDate1 == LogEntryDate2, :: getTime sorts by time
        Collections.sort(sortedEntries, byDateThenTime.reversed()); // Flips result order
        return sortedEntries;
    }

    /**
     * Filters through the entries list to find all positive amounts (deposits / jokes told).
     *
     * @return A new List containing only positive LogEntry items, sorted newest first.
     */
    public List<LogEntry> getDeposits() {
        List<LogEntry> deposits = new ArrayList<>();
        for (LogEntry entry : entries) {
            if (entry.getAmount() > 0) {
                deposits.add(entry);
            }
        }

        // Comparator simplifies lambdas using the :: method reference operator
        Comparator<LogEntry> byDateThenTime = Comparator // Comparison rule for LogEntry objects
                .comparing(LogEntry::getDate) // Takes date and compares each entry; sorts ascending by default
                .thenComparing(LogEntry::getTime); // if LogEntryDate1 == LogEntryDate2, :: getTime sorts by time
        Collections.sort(deposits, byDateThenTime.reversed()); // Flips result order
        return deposits;
    }


    /**
     * Filters through the entries list to find all negative amounts (Payments / Dadloons Spent).
     *
     * @return A new List containing only negative LogEntry items, sorted newest first.
     */
    public List<LogEntry> getPayments() {
        List<LogEntry> payments = new ArrayList<>();
        for (LogEntry entry : entries) {
            if (entry.getAmount() < 0) {
                payments.add(entry);
            }
        }

        // Comparator simplifies lambdas using the :: method reference operator
        Comparator<LogEntry> byDateThenTime = Comparator // Comparison rule for LogEntry objects
                .comparing(LogEntry::getDate) // Takes date and compares each entry; sorts ascending by default
                .thenComparing(LogEntry::getTime); // if LogEntryDate1 == LogEntryDate2, ::getTime sorts by time
        payments.sort(byDateThenTime.reversed()); // Flips result order

        return payments;
    }
    /**
     * This method takes a new LogEntry, adds it to the entries list memory and immediately saves it to the CSV file.
     */
    public void addEntry(LogEntry entry) {
        entries.add(entry);      // Add to memory first
        saveTransactions();      // Then write the updated list to the CSV file second
    }

    public List<LogEntry> getEntriesByVendor(String vendor) {
        List<LogEntry> results = new ArrayList<>();
        for (LogEntry entry : entries) {
            if (entry.getVendor().equalsIgnoreCase(vendor)) {
                results.add(entry);
            }
        }
        return results;
    }

    /**
     * Will check if line (reader.readLine()) is NOT EQUAL TO null; once it is, stops reading because it'll be EQUAL TO null.
     * Skips the first header using an isFirstLine boolean.
     * entries.add(entry) saves into memory.
     */
    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) { // Reads lines until there are no more lines left (null)
                if (isFirstLine) {
                    isFirstLine = false; // Skips header
                    continue;
                }

                /**
                 * Splits the pipe into array parts (date, time, vendor, description, amount),
                 * converts the string amount into a double, builds a LogEntry, and saves it to memory.
                 */
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    LogEntry entry = new LogEntry(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            Double.parseDouble(parts[4])
                    );

                    entries.add(entry);
                }
            }
        } catch (IOException e) {
            System.out.println("Blimey! The ledger's pages be ruined!: " + e.getMessage());
        }
    }

    /**
     * Saves the current list of transactions back to the CSV file.
     * Writes the pipe header row first ("date|time|description|vendor|amount").
     * Loops through every entry in memory and writes it out row-by-row.
     * Uses BufferedWriter to output data to transactions.csv.
     */
    public void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("src/main/resources/transactions.csv"))) {

            writer.write("date|time|description|vendor|amount"); // Match the exact header from the CSV
            writer.newLine();

            for (LogEntry entry : entries) {
                String line = entry.getDate() + "|" + // date | time | description | vendor | amount
                        entry.getTime() + "|" +
                        entry.getDescription() + "|" +
                        entry.getVendor() + "|" +
                        entry.getAmount();
                writer.write(line);
                writer.newLine();
            }

            System.out.println("X marks the spot for yer Dadloons!");
        } catch (IOException e) {
            System.out.println("Arr, mate! Failed to log yer Dadloons. Give 'er another haul!: " + e.getMessage());
        }
    }
}
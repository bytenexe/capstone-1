package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

/**
 * CaptainsLog
 * Acts as the data engine and file manager for the application.
 * Handles loading historical transactions from CSV, storing them in memory, and filtering records.
 */
public class CaptainsLog {
    private List<LogEntry> entries;

    /**
     * Constructor runs when a CaptainsLog object is created.
     * Initializes an empty ArrayList and immediately calls loadTransactions() to read the CSV.
     */
    public CaptainsLog() {
        this.entries = new ArrayList<>();
        loadTransactions();
    }

    /**
     * Getter method that returns the entire list of transaction entries.
     *
     * @return The full List of LogEntry objects.
     */
    public List<LogEntry> getAll() {
        return entries;
    }

    /**
     * Filters through the entries list to find all positive amounts (deposits / jokes told).
     *
     * @return A new List containing only positive LogEntry items.
     */
    public List<LogEntry> getDeposits() {
        List<LogEntry> deposits = new ArrayList<>();
        for (LogEntry entry : entries) {
            if (entry.getAmount() > 0) {
                deposits.add(entry);
            }
        }
        return deposits;
    }

    /**
     * Filters through the entries list to find all negative amounts (Payments / Dadloons Spent).
     *
     * @return A new List containing only negative LogEntry items.
     */
    public List<LogEntry> getPayments() {
        List<LogEntry> payments = new ArrayList<>();
        for (LogEntry entry : entries) {
            if (entry.getAmount() < 0) {
                payments.add(entry);
            }
        }
        return payments;
    }

    /**
     * Adds a new transaction entry directly to the in-memory list.
     *
     * @param entry The LogEntry object to add.
     */
    public void addEntry(LogEntry entry) {
        entries.add(entry);
    }

    /**
     * Safely reads the transactions.csv file line-by-line and populates the entries list.
     */
    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {
            String line;
            boolean isFirstLine = true;

            // Reads lines until there are no more lines left (null)
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skips header row
                    continue;
                }

                /*
                 * Splits the pipe-delimited line into array parts (date, time, vendor, description, amount),
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
            System.out.println("Error reading the log: " + e.getMessage());
        }
    }
}
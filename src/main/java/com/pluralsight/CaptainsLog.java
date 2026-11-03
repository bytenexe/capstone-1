package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CaptainsLog {
    private List<LogEntry> entries;

    public CaptainsLog() {
    // Creating the list
        this.entries = new ArrayList<>();

    // Load data from transactions.csv
        loadTransactions();
    }

    // Getters for UI
    public List<LogEntry> getAll() {
        return entries;
    }

    public List<LogEntry> getDeposits() {
        List<LogEntry> deposits = new ArrayList<>();
        for (LogEntry entry : entries) {
            if (entry.getAmount() > 0) {
                deposits.add(entry);
            }
        }
        return deposits;
    }

    public List<LogEntry> getPayments() {
        List<LogEntry> payments = new ArrayList<>();
        for (LogEntry entry : entries) {
            if (entry.getAmount() < 0) {
                payments.add(entry);
            }
        }
        return payments;
    }

    public void addEntry(LogEntry entry) {
        entries.add(entry);
    }

    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {
            String line;
            boolean isFirstLine = true;

            // Read until there is no more data 
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] parts = line.split("\\|");

                // Safety check to ensure the line has enough data
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



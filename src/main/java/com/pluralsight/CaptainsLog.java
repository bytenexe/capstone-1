//Library book; it loads the file, filters for deposits and payments

package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class CaptainsLog {
    private List<LogEntry> entries;

    public CaptainsLog() {

        // Creating the list
        this.entries = new ArrayList<>();

        // Loading the CSV to be read
        entries.add(new LogEntry("2026-10-01", "09:12:04", "Dave", "Test Joke", 5.00));
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
}
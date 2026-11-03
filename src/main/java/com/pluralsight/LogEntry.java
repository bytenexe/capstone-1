// 'Transactions.java'

package com.pluralsight;

// Creating private classes to be unmodified
public class LogEntry {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

// Creating the constructors (are null be default)
    public LogEntry(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
    }

    // Getters
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getDescription() { return description; }
    public String getVendor() { return vendor; }
    public double getAmount() { return amount; }

    // Setters
    public void setDate(String date) { this.date = date; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setVendor(String vendor) { this.vendor = vendor; }
    public void setDescription(String description) { this.description = description; }
    public void setTime(String time) { this.time = time; }
}
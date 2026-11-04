package com.pluralsight;

/**
 * LogEntry (Transactions.java)
 * Represents an individual transaction item.
 * Stores the following fields: date, time, description, vendor, amount.
 */
public class LogEntry {

    // Creation of fields set to private.
    // private is an access modifier that encapsulates code from being changed by other code.
    // private variables are protected from direct reading and writing from outside classes.
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    /**
     * Constructors (builder method) initialize all fields when a new transaction is created.
     * Object fields are null by default before the constructor gives them values.
     *
     * @param date        The date of the transaction (YYYY-MM-DD)
     * @param time        The time of the transaction (HH:MM:SS)
     * @param description The description or joke told
     * @param vendor      The vendor or person involved
     * @param amount      The monetary amount (positive for deposits, negative for payments)
     */
    public LogEntry(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Getter methods to retrieve encapsulated data safely.
     * public is an access modifier allowing other classes to call these methods.
     */
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getDescription() { return description; }
    public String getVendor() { return vendor; }
    public double getAmount() { return amount; }

    /**
     * Setter methods to change and update the private fields from outside the class.
     * Encapsulates data to protect fields while allowing controlled modifications.
     * Return nothing (void); they perform an action to update data rather than fetching it.
     */
    public void setDate(String date) { this.date = date; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setVendor(String vendor) { this.vendor = vendor; }
    public void setDescription(String description) { this.description = description; }
    public void setTime(String time) { this.time = time; }
}
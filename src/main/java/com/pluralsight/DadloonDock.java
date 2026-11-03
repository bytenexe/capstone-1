// Main
package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DadloonDock {
    public static void main(String[] args) {
        // Captain Dave's Dadloon Dock starts
        System.out.println("Welcome to Captain Dave's Dadloon Dock!");

        // First test: Creating an entry directly
        LogEntry entry = new LogEntry("2026-10-01", "09:12:04", "Dave", "Why do Java developers wear glasses?", 5.00);
        System.out.println("First Entry: " + entry.getDescription());

        // Second test: Parsing a CSV line
        String line = "2026-10-01|09:12:04|Why do Java developers wear glasses?|Dave|5.00";
        String[] parts = line.split("\\|");

        LogEntry parsedEntry = new LogEntry(
                parts[0], // date
                parts[1], // time
                parts[2], // description
                parts[3], // vendor
                Double.parseDouble(parts[4]) // amount
        );

        System.out.println("Created entry: " + parsedEntry.getVendor() + " - " + parsedEntry.getDescription());
    }
}


package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Transaction {

    private static final String FILE_NAME = "transactions.csv";

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // Constructor
    public Transaction(LocalDate date, LocalTime time,
                       String description, String vendor, double amount) {

        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Getters
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    // Create CSV file
    public static void createFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(file))) {

                writer.write("date|time|description|vendor|amount");
                writer.newLine();

            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    // Load all transactions
    public static ArrayList<Transaction> loadAll() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|", -1);

                if (parts.length == 5) {

                    try {
                        LocalDate date = LocalDate.parse(parts[0]);
                        LocalTime time = LocalTime.parse(parts[1], formatter);

                        String description = parts[2];
                        String vendor = parts[3];
                        double amount = Double.parseDouble(parts[4]);

                        transactions.add(new Transaction(
                                date, time, description, vendor, amount
                        ));

                    } catch (Exception e) {
                        System.out.println("Skipping invalid transaction: " + line);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return transactions;
    }

    // Save a transaction
    public static void saveTransaction(
            String description,
            String vendor,
            double amount) {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss");

        description = description.replace("|", "/");
        vendor = vendor.replace("|", "/");

        boolean saved = false;

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            writer.write(
                    date + "|"
                            + time.format(formatter) + "|"
                            + description + "|"
                            + vendor + "|"
                            + String.format(Locale.US, "%.2f", amount)
            );

            writer.newLine();
            saved = true;

        } catch (IOException e) {

            System.out.println(Console.RED
                    + "Error saving transaction: " + e.getMessage()
                    + Console.RESET);
        }

        if (saved) {
            System.out.println(Console.GREEN
                    + "\nTransaction saved successfully!" + Console.RESET);

            System.out.println(Console.BLUE
                    + "\n========== UPDATED BALANCE ==========" + Console.RESET);

            System.out.printf(
                    Console.GREEN + "Current Balance: $%.2f\n" + Console.RESET,
                    calculateBalance()
            );
        }
    }

    // Calculate account balance
    public static double calculateBalance() {

        double balance = 10000.00;

        ArrayList<Transaction> transactions = loadAll();

        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }

        return balance;
    }
}


package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Program {

    public static Scanner scanner = new Scanner(System.in);

    public static final String FILE_NAME = "transactions.csv";

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            System.out.println("\n========== Will's Games ==========");
            System.out.println("1) Add Deposit");
            System.out.println("2) Make Payment (Debit)");
            System.out.println("3) Ledger");
            System.out.println("4) Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {

                case "1":
                    addDeposit();
                    break;

                case "2":
                    makePayment();
                    break;

                case "3":
                    displayLedger();
                    break;

                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    // Read transactions from CSV
    public static ArrayList<String[]> getTransactions() {

        ArrayList<String[]> transactions = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|", -1);

                if (parts.length == 5) {
                    transactions.add(parts);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return transactions;
    }

    // Save transaction to CSV
    public static void saveTransaction(
            String description, String vendor, double amount) {

        LocalDate date = LocalDate.now();

        LocalTime time = LocalTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss");

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            writer.write(
                    date + "|" +
                            time.format(formatter) + "|" +
                            description + "|" +
                            vendor + "|" +
                            String.format(Locale.US, "%.2f", amount)
            );

            writer.newLine();

            System.out.println("Transaction saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Add deposit
    public static void addDeposit() {

        System.out.println("\n========== ADD DEPOSIT ==========");

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount <= 0) {
            System.out.println("Deposit must be greater than zero.");
            return;
        }

        saveTransaction(description, vendor, amount);
    }

    // Make payment
    public static void makePayment() {

        System.out.println("\n========== MAKE PAYMENT ==========");

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount <= 0) {
            System.out.println("Payment must be greater than zero.");
            return;
        }

        saveTransaction(description, vendor, -amount);
    }

    // Display ledger menu
    public static void displayLedger() {

        boolean running = true;

        while (running) {

            System.out.println("\n========== LEDGER ==========");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {

                case "A":
                    displayTransactions(getTransactions(), "ALL");
                    break;

                case "D":
                    displayTransactions(getTransactions(), "DEPOSITS");
                    break;

                case "P":
                    displayTransactions(getTransactions(), "PAYMENTS");
                    break;

                case "R":
                    Reports.displayReports(scanner);
                    break;

                case "H":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Display transactions
    public static void displayTransactions(
            ArrayList<String[]> transactions, String filter) {

        transactions.sort((a, b) -> {

            int dateCompare = b[0].compareTo(a[0]);

            if (dateCompare != 0) {
                return dateCompare;
            }

            return b[1].compareTo(a[1]);
        });

        System.out.println("\nDATE | TIME | DESCRIPTION | VENDOR | AMOUNT");
        System.out.println("---------------------------------------------");

        for (String[] transaction : transactions) {

            double amount = Double.parseDouble(transaction[4]);

            if (filter.equals("ALL")
                    || (filter.equals("DEPOSITS") && amount > 0)
                    || (filter.equals("PAYMENTS") && amount < 0)) {

                System.out.printf(
                        "%s | %s | %s | %s | $%.2f%n",
                        transaction[0],
                        transaction[1],
                        transaction[2],
                        transaction[3],
                        amount
                );
            }
        }
    }
}


package com.pluralsight;

import java.io.*;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Program {

    public static Scanner scanner = new Scanner(System.in);

    public static final String FILE_NAME = "transactions.csv";

    // Terminal colors
    public static final String CYAN = "\033[0;96m";
    public static final String BLUE = "\033[0;36m";
    public static final String WHITE = "\033[0;97m";
    public static final String GRAY = "\033[0;90m";
    public static final String GREEN = "\033[0;92m";
    public static final String RED = "\033[0;91m";
    public static final String RESET = "\033[0m";
    public static final String BRIGHT_PURPLE = "\033[0;95m";

    public static void main(String[] args) {

        createFile();
        startScreen();

        boolean running = true;

        while (running) {

            displayHomeScreen();

            System.out.print(CYAN + "\nSelect an option: " + RESET);

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {

                case "D":
                    addDeposit();
                    pause();
                    break;

                case "P":
                    makePayment();
                    pause();
                    break;

                case "L":
                    displayLedger();
                    break;

                case "X":
                    System.out.println(CYAN
                            + "Thank you for using Personal Ledger!" + RESET);
                    running = false;
                    break;

                default:
                    System.out.println(RED
                            + "Invalid option. Try again." + RESET);
                    pause();
            }
        }

        scanner.close();
    }

    // ==========================================
    // START SCREEN
    // ==========================================

    public static void startScreen() {

        clearScreen();

        // Main ASCII logo
        System.out.println(BRIGHT_PURPLE + """
             ▄▄                        ▄▄
             ██                        ██
             ██         ▄████▄    ▄███▄██   ▄███▄██   ▄████▄    ██▄████
             ██        ██▄▄▄▄██  ██▀  ▀██  ██▀  ▀██  ██▄▄▄▄██   ██▀
             ██        ██▀▀▀▀▀▀  ██    ██  ██    ██  ██▀▀▀▀▀▀   ██
             ██▄▄▄▄▄▄  ▀██▄▄▄▄█  ▀██▄▄███  ▀██▄▄███  ▀██▄▄▄▄█   ██
             ▀▀▀▀▀▀▀▀    ▀▀▀▀▀     ▀▀▀ ▀▀   ▄▀▀▀ ██    ▀▀▀▀▀    ▀▀
                                            ▀████▀▀
            """ + RESET);

        System.out.println(GRAY +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);

        // Personal Finance ASCII art
        System.out.println(BLUE + """
             ██████   █████  ███    ██ ██   ██ ██ ███    ██  ██████         ██        ███    ███  ██████  ██████  ███████
             ██   ██ ██   ██ ████   ██ ██  ██  ██ ████   ██ ██              ██        ████  ████ ██    ██ ██   ██ ██
             ██████  ███████ ██ ██  ██ █████   ██ ██ ██  ██ ██   ███     ████████     ██ ████ ██ ██    ██ ██████  █████
             ██   ██ ██   ██ ██  ██ ██ ██  ██  ██ ██  ██ ██ ██    ██     ██  ██       ██  ██  ██ ██    ██ ██   ██ ██
             ██████  ██   ██ ██   ████ ██   ██ ██ ██   ████  ██████      ██████       ██      ██  ██████  ██   ██ ███████

                            PERSONAL FINANCE

                          TRANSACTION SYSTEM
            """ + RESET);

        System.out.println(GRAY +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);

        System.out.println();
        System.out.println();

        System.out.print(CYAN +
                "              [ PRESS ENTER TO START ]" + RESET);

        scanner.nextLine();

        loadingScreen();

        clearScreen();
    }

    // ==========================================
    // LOADING SCREEN
    // ==========================================

    public static void loadingScreen() {

        System.out.println("\n\n");
        System.out.println(WHITE + "          INITIALIZING PERSONAL LEDGER..." + RESET);

        System.out.print(CYAN + "\n          [");

        for (int i = 0; i < 25; i++) {

            System.out.print("█");

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("] 100%" + RESET);

        System.out.println(GREEN + "\n          SYSTEM READY." + RESET);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ==========================================
    // HOME MENU
    // ==========================================


    public static void displayHomeScreen() {

        clearScreen();

        System.out.println(CYAN + """
            
            """ + RESET);

        System.out.println(GRAY + "              PERSONAL FINANCE DASHBOARD" + RESET);

        System.out.println(BLUE +
                "\n        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);

        System.out.println(WHITE + "\n                  MAIN MENU\n" + RESET);

        System.out.println(CYAN + "             [D] " + WHITE + "Add Deposit");
        System.out.println(CYAN + "             [P] " + WHITE + "Make Payment (Debit)");
        System.out.println(CYAN + "             [L] " + WHITE + "View Ledger");
        System.out.println(CYAN + "             [X] " + WHITE + "Exit");

        System.out.println(BLUE +
                "\n        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);

        System.out.printf(GREEN + "\n        Current Balance: $%.2f\n" + RESET,
                calculateBalance());
    }


    // ==========================================
    // FILE CREATION
    // ==========================================

    public static void createFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file))) {

                writer.write("date|time|description|vendor|amount");
                writer.newLine();

            } catch (IOException e) {

                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    // ==========================================
    // READ TRANSACTIONS
    // ==========================================

    public static ArrayList<String[]> getTransactions() {

        ArrayList<String[]> transactions = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            reader.readLine();

            String line;

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

    // ==========================================
    // SAVE TRANSACTION
    // ==========================================

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

            System.out.println(GREEN
                    + "\nTransaction saved successfully!" + RESET);

        } catch (IOException e) {

            System.out.println(RED
                    + "Error saving transaction: " + e.getMessage() + RESET);
        }

        // Display the updated balance after the file has been saved.
        if (saved) {
            System.out.println(BLUE
                    + "\n========== UPDATED BALANCE ==========" + RESET);

            System.out.printf(
                    GREEN + "Current Balance: $%.2f\n" + RESET,
                    calculateBalance()
            );
        }
    }

    // ==========================================
    // ADD DEPOSIT
    // ==========================================

    public static void addDeposit() {

        clearScreen();

        System.out.println(CYAN +
                "\n========== ADD DEPOSIT ==========" + RESET);

        System.out.print("\nDescription: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        double amount = getAmount();

        if (amount <= 0) {

            System.out.println(RED +
                    "Deposit must be greater than zero." + RESET);
            return;
        }

        saveTransaction(description, vendor, amount);
    }

    // ==========================================
    // MAKE PAYMENT
    // ==========================================

    public static void makePayment() {

        clearScreen();

        System.out.println(CYAN +
                "\n========== MAKE PAYMENT ==========" + RESET);

        System.out.print("\nDescription: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        double amount = getAmount();

        if (amount <= 0) {

            System.out.println(RED +
                    "Payment must be greater than zero." + RESET);
            return;
        }

        saveTransaction(description, vendor, -amount);
    }

    // ==========================================
    // GET VALID AMOUNT
    // ==========================================

    public static double getAmount() {

        while (true) {

            System.out.print("Amount: ");

            try {

                double amount = Double.parseDouble(scanner.nextLine());

                if (Double.isFinite(amount)) {
                    return amount;
                }

                System.out.println(RED + "Enter a valid amount." + RESET);

            } catch (NumberFormatException e) {

                System.out.println(RED +
                        "Invalid amount. Please enter a number." + RESET);
            }
        }
    }

    // ==========================================
    // CALCULATE BALANCE
    // ==========================================

    public static double calculateBalance() {

        double balance = 10000.00;

        ArrayList<String[]> transactions = getTransactions();

        for (String[] transaction : transactions) {

            try {
                double amount = Double.parseDouble(transaction[4]);

                balance += amount;

            } catch (NumberFormatException e) {
                System.out.println("Invalid transaction amount.");
            }
        }

        return balance;
    }

    // ==========================================
    // LEDGER MENU
    // ==========================================

    public static void displayLedger() {

        boolean running = true;

        while (running) {

            clearScreen();

            System.out.println(CYAN + "\n============== LEDGER ==============" + RESET);

            System.out.println(WHITE + "\n[A] All Transactions");
            System.out.println("[D] Deposits");
            System.out.println("[P] Payments");
            System.out.println("[R] Reports");
            System.out.println("[H] Home" + RESET);

            System.out.print(CYAN + "\nSelect an option: " + RESET);

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {

                case "A":
                    clearScreen();
                    displayTransactions(getTransactions(), "ALL");
                    pause();
                    break;

                case "D":
                    clearScreen();
                    displayTransactions(getTransactions(), "DEPOSITS");
                    pause();
                    break;

                case "P":
                    clearScreen();
                    displayTransactions(getTransactions(), "PAYMENTS");
                    pause();
                    break;

                case "R":
                    displayReports();
                    break;

                case "H":
                    running = false;
                    break;

                default:
                    System.out.println(RED + "Invalid option. Try again." + RESET);
                    pause();
            }
        }
    }
    // ==========================================
    // DISPLAY TRANSACTIONS
    // ==========================================

    public static void displayTransactions(
            ArrayList<String[]> transactions,
            String filter) {

        clearScreen();

        // Sort newest transactions first
        transactions.sort((a, b) -> {

            int dateCompare = b[0].compareTo(a[0]);

            if (dateCompare != 0) {
                return dateCompare;
            }

            return b[1].compareTo(a[1]);
        });

        System.out.println(CYAN +
                "\n================ TRANSACTIONS ================" + RESET);

        System.out.println(WHITE +
                "\nDATE | TIME | DESCRIPTION | VENDOR | AMOUNT" + RESET);

        System.out.println(BLUE +
                "------------------------------------------------------------" + RESET);

        boolean found = false;

        for (String[] transaction : transactions) {

            double amount = Double.parseDouble(transaction[4]);

            if (filter.equals("ALL")
                    || (filter.equals("DEPOSITS") && amount > 0)
                    || (filter.equals("PAYMENTS") && amount < 0)) {

                String color = amount >= 0 ? GREEN : RED;

                System.out.printf(
                        "%s | %s | %s | %s | %s$%.2f%s%n",
                        transaction[0],
                        transaction[1],
                        transaction[2],
                        transaction[3],
                        color,
                        amount,
                        RESET
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println(RED + "No transactions found." + RESET);
        }

        System.out.println(BLUE +
                "\n------------------------------------------------------------" + RESET);
    }

    // ==========================================
    // REPORTS
    // ==========================================

    public static void displayReports() {

        clearScreen();

        ArrayList<String[]> transactions = getTransactions();

        double deposits = 0;
        double payments = 0;

        int depositCount = 0;
        int paymentCount = 0;

        for (String[] transaction : transactions) {

            double amount = Double.parseDouble(transaction[4]);

            if (amount > 0) {

                deposits += amount;
                depositCount++;

            } else if (amount < 0) {

                payments += Math.abs(amount);
                paymentCount++;
            }
        }

        double balance = calculateBalance();

        System.out.println(CYAN +
                "\n=============== FINANCIAL REPORT ===============" + RESET);

        System.out.println();

        System.out.printf(GREEN +
                "Total Deposits:       $%.2f%n" + RESET, deposits);

        System.out.printf(RED +
                "Total Payments:       $%.2f%n" + RESET, payments);

        System.out.println(BLUE +
                "------------------------------------------------" + RESET);

        System.out.printf(WHITE +
                "Current Balance:      $%.2f%n" + RESET, balance);

        System.out.println();

        System.out.println(CYAN + "TRANSACTION STATISTICS" + RESET);

        System.out.println("Total Transactions:   " + transactions.size());
        System.out.println("Number of Deposits:   " + depositCount);
        System.out.println("Number of Payments:   " + paymentCount);

        System.out.println(BLUE +
                "\n================================================" + RESET);
    }

    // ==========================================
    // CLEAR TERMINAL
    // ==========================================

    public static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // ==========================================
    // PAUSE SCREEN
    // ==========================================

    public static void pause() {

        System.out.println();

        System.out.print(GRAY +
                "Press ENTER to continue..." + RESET);

        scanner.nextLine();
    }
}

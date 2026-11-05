package com.pluralsight;

import java.util.*;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static com.pluralsight.Console.*;

public class Program {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Enable UTF-8 encoding for console output and errors
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        Transaction.createFile();
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
        System.out.println(BLUE + """
[0;37m [0;34m▐█[0;37m     [0;34m▐█████▐█████▄[0;37m    [0;34m▄█▓▓▓▓[0;37m  [0;34m▐█████▐█████▄[0;37m  [0m
[0;37m [0;34m▐█▌[0;37m    [0;34m▐█▌[0;37m    [0;34m▐██▀█[0;36;44m [0;34m▌[0;90m▓[0;37m [0;34m██▀[0;37m [0;34m▐█▌[0;37m  [0;34m▐█▌[0;37m    [0;34m▐██▀█[0;36;44m [0;34m▌[0;90m▓[0m
[0;37m [0;36;44m░ [0;37m  [0;90m░░[0;37m [0;36;44m░ [0;34m██[0;37m   [0;34m██[0;37m  [0;34m▐██[0;37m [0;34m▐█▌[0;36m░[0;37m [0;34m██[0;37m [0;90m▐[0;37m [0;36;44m░ [0;34m██[0;37m   [0;34m██[0;37m  [0;34m▐██[0;37m [0m
[0;37m [0;34m██[0;37m [0;90m░░[0;36m░[0;37m [0;34m██[0;37m   [0;36m░[0;37m [0;34m██[0;37m [0;36m░[0;37m [0;34m██▌█[0;94;44m░[0;37m [0;90m▌[0;37m    [0;36m░[0;37m [0;34m██[0;37m   [0;36m░[0;37m [0;34m██[0;37m [0;36m░[0;37m [0;34m█▌[0;37m [0m
[0;34m▐[0;94;44m░[0;34m▌[0;37m [0;36m░░░[0;34m▐[0;94;44m░[0;34m▌[0;37m [0;36m░░░[0;34m▐[0;94;44m░[0;34m▌[0;90m▐[0;36m░[0;37m [0;94;44m░[0;34m█▌█[0;94;44m▒[0;37m  [0;34m██[0;94;44m░[0;34m█[0;37m [0;34m▐[0;94;44m░[0;34m▌[0;37m [0;36m░░░[0;34m▐[0;94;44m░[0;34m▌▄▄[0;94;44m░[0;34m▀[0;90m░▌[0m
[0;94m▐[0;94;44m▒[0;34m▌[0;37m [0;90m▀[0;37m  [0;94m▐[0;94;44m▒[0;34m▌[0;37m [0;90m▀[0;37m  [0;34m▐[0;94;44m▒[0;94m▌[0;90m▐[0;37m [0;94m▐[0;94;44m▒[0;34m█[0;37m [0;34m▐[0;94;44m▓[0;94m▌[0;37m [0;90m▄[0;37m [0;94;44m▒░[0;34m░[0;94m▐[0;94;44m▒[0;34m▌[0;37m [0;90m▀[0;37m  [0;34m▐[0;94;44m▒[0;94m▌[0;37m [0;34m▀[0;94;44m░[0;34m▌[0;90m▀[0;37m [0m
[0;94m█[0;94;44m▓[0;37m [0;94m▄▄[0;94;44m▓[0;94m██[0;94;44m▓[0;37m [0;94m▄▄[0;94;44m▓[0;94m█▐[0;94;44m▓[0;37m  [0;94m▄[0;94;44m▓[0;94m█[0;37m   [0;94m██▄[0;37m  [0;94;44m▓▒[0;37m [0;94m█[0;94;44m▓[0;37m [0;94m▄▄[0;94;44m▓[0;94m█▐[0;94;44m▓[0;37m [0;90m░[0;37m [0;94;44m▒▓[0;94m▌[0;37m [0m
[0;94m▓██▀▀[0;37m  [0;94m▓██▀▀[0;37m  [0;94m▐█▄[0;94;47m██[0;94m▀[0;37m     [0;94m▀▓█▄█[0;94;44m▓[0;37m [0;94m▓██▀▀[0;37m  [0;94m▐█[0;37m [0;90m░[0;37m [0;94m▐[0;94;44m▓[0;94m█[0;37m [0m
[0;37m              [0;94m██▀[0;37m           [0;94m▀█▌[0;37m        [0;94m██▀[0;37m  [0;94m█▀[0;37m  [0m
[0;37m              [0;94m▀[0;37m               [0;94m▌[0;37m        [0;94m▀[0;37m        [0m

                Bank safer.
            """ + RESET);

        System.out.println();
        System.out.println();

        System.out.print(BLUE +
                "         [ PRESS ENTER TO START ]" + RESET);

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

        System.out.print(BRIGHT_YELLOW + "\n          [");

        for (int i = 0; i < 25; i++) {

            System.out.print("★");

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("] 100%" + RESET);

        System.out.println(WHITE + "\n          SYSTEM READY." + RESET);

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
                Transaction.calculateBalance());
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

        Transaction.saveTransaction(description, vendor, amount);
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

        Transaction.saveTransaction(description, vendor, -amount);
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
                    displayTransactions(Transaction.loadAll(), "ALL");
                    pause();
                    break;

                case "D":
                    clearScreen();
                    displayTransactions(Transaction.loadAll(), "DEPOSITS");
                    pause();
                    break;

                case "P":
                    clearScreen();
                    displayTransactions(Transaction.loadAll(), "PAYMENTS");
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
        ArrayList<Transaction> transactions,
        String filter) {

        clearScreen();

        // Sort newest transactions first
        transactions.sort((a, b) -> {

            int dateCompare = b.getDate().compareTo(a.getDate());

            if (dateCompare != 0) {
                return dateCompare;
            }

            return b.getTime().compareTo(a.getTime());
        });

        System.out.println(Console.CYAN +
                "\n================ TRANSACTIONS ================" + Console.RESET);

        System.out.println(Console.WHITE +
                "\nDATE | TIME | DESCRIPTION | VENDOR | AMOUNT" + Console.RESET);

        System.out.println(Console.BLUE +
                "------------------------------------------------------------" + Console.RESET);

        boolean found = false;

        for (Transaction transaction : transactions) {

            double amount = transaction.getAmount();

            if (filter.equals("ALL")
                    || (filter.equals("DEPOSITS") && amount > 0)
                    || (filter.equals("PAYMENTS") && amount < 0)) {

                String color = amount >= 0 ? Console.GREEN : Console.RED;

                System.out.printf(
                        "%s | %s | %s | %s | %s$%.2f%s%n",
                        transaction.getDate(),
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        color,
                        amount,
                        Console.RESET
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println(Console.RED + "No transactions found." + Console.RESET);
        }

        System.out.println(Console.BLUE +
                "\n------------------------------------------------------------" + Console.RESET);
    }

    // ==========================================
    // REPORTS
    // ==========================================

    public static void displayReports() {

        clearScreen();

        ArrayList<Transaction> transactions = Transaction.loadAll();

        double deposits = 0;
        double payments = 0;

        int depositCount = 0;
        int paymentCount = 0;

        for (Transaction transaction : transactions) {

            double amount = transaction.getAmount();

            if (amount > 0) {

                deposits += amount;
                depositCount++;

            } else if (amount < 0) {

                payments += Math.abs(amount);
                paymentCount++;
            }
        }

        double balance = Transaction.calculateBalance();

        System.out.println(CYAN +
                "\n=============== FINANCIAL REPORT ===============" + RESET);

        System.out.println();

        System.out.printf(GREEN +
                "Total Deposits:       $%.2f%n" + RESET, deposits);

        System.out.printf(RED +
                "Total Payments:       $%.2f%n" + RESET, payments);

        System.out.println(BLUE +
                "------------------------------------------------" + RESET);

        System.out.printf(BLUE +
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

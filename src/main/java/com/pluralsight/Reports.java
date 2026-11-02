package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {

    public static void displayReports(Scanner scanner) {

        boolean running = true;

        while (running) {

            System.out.println("\n========== REPORTS ==========");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            LocalDate today = LocalDate.now();

            ArrayList<String[]> transactions =
                    Program.getTransactions();

            switch (choice) {

                case "1":
                    showDateReport(transactions,
                            today.withDayOfMonth(1), today);
                    break;

                case "2":
                    LocalDate previousMonth =
                            today.minusMonths(1).withDayOfMonth(1);

                    showDateReport(transactions,
                            previousMonth,
                            today.withDayOfMonth(1).minusDays(1));
                    break;

                case "3":
                    showDateReport(transactions,
                            today.withDayOfYear(1), today);
                    break;

                case "4":
                    LocalDate previousYear =
                            LocalDate.of(today.getYear() - 1, 1, 1);

                    showDateReport(transactions,
                            previousYear,
                            LocalDate.of(today.getYear() - 1, 12, 31));
                    break;

                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();

                    searchVendor(transactions, vendor);
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void showDateReport(
            ArrayList<String[]> transactions,
            LocalDate startDate,
            LocalDate endDate) {

        ArrayList<String[]> results = new ArrayList<>();

        for (String[] transaction : transactions) {

            LocalDate transactionDate =
                    LocalDate.parse(transaction[0]);

            if (!transactionDate.isBefore(startDate)
                    && !transactionDate.isAfter(endDate)) {

                results.add(transaction);
            }
        }

        Program.displayTransactions(results, "ALL");
    }

    public static void searchVendor(
            ArrayList<String[]> transactions, String vendor) {

        ArrayList<String[]> results = new ArrayList<>();

        for (String[] transaction : transactions) {

            if (transaction[3].equalsIgnoreCase(vendor)) {
                results.add(transaction);
            }
        }

        Program.displayTransactions(results, "ALL");
    }
}
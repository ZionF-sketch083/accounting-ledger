package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class FinancialTracker {


    private static final ArrayList<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "transactions.csv";

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);


    public static void main(String[] args) {
        loadTransactions(FILE_NAME);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D" -> addDeposit(scanner);
                case "P" -> addPayment(scanner);
                case "L" -> ledgerMenu(scanner);
                case "X" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
        scanner.close();
    }

    public static void loadTransactions(String fileName) {

        File otherFile = new File(FILE_NAME);

        try {
            if (!otherFile.exists()){
                otherFile.createNewFile();
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(otherFile));

            String line;

            while ((line = br.readLine())!= null){
                String[] parts = line.split("\\|");

                if (parts.length == 5){
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);

                    transactions.add(transaction);

                }
            }
            br.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }




    private static void addDeposit(Scanner scanner) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));

            System.out.print("What is the date and time("+ DATE_PATTERN +" "+ TIME_PATTERN +") of the transaction? ");
            String dateTime = scanner.nextLine();
            System.out.print("What is the description of the transaction? ");
            String description = scanner.nextLine();
            System.out.print("Who is the vendor of this transaction? ");
            String vendor = scanner.nextLine();
            System.out.print("What was the amount of the transaction? ");
            double amount = Double.parseDouble(scanner.nextLine());
            // scanner.nextLine();

            if (amount < 0){
                System.out.println("Deposit must be greater than zero.");
                return;
            }

            LocalDate date = LocalDate.parse(dateTime.split(" ")[0]);
            LocalTime time = LocalTime.parse(dateTime.split(" ")[1]);


            transactions.add(new Transaction(date, time, description, vendor, amount));

            bw.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            System.out.println("Oh on, something went wrong.");
        }

        System.out.println("Thank you for adding deposit.");

    }


    private static void addPayment(Scanner scanner) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));

            System.out.print("What is the date and time("+ DATE_PATTERN +" "+ TIME_PATTERN +") of this transaction? ");
            String dateTime = scanner.nextLine();
            System.out.print("What is the description of the transaction? ");
            String description = scanner.nextLine();
            System.out.print("Who is the vendor of this transaction? ");
            String vendor = scanner.nextLine();
            System.out.print("What was the amount of the transaction? ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount < 0) {
                System.out.println("Your payment must be a positive number.");
                return;
            }
            amount = -amount;

            LocalDate date = LocalDate.parse(dateTime.split(" ")[0]);
            LocalTime time = LocalTime.parse(dateTime.split(" ")[1]);

            transactions.add(new Transaction(date, time, description, vendor, amount));

            bw.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
            bw.newLine();
            bw.close();


        } catch (Exception e) {
            System.out.println("Oh on, something went wrong.");
        }

        System.out.println("Thank you for payment.");

    }


    private static void ledgerMenu(Scanner scanner) {
        Collections.sort(transactions, Comparator.comparing(Transaction::getDate).
                thenComparing(Transaction::getTime).reversed());
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A" -> displayLedger();
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> reportsMenu(scanner);
                case "H" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }


    private static void displayLedger() {


        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void displayDeposits() {


        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }

    private static void displayPayments() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }


    private static void reportsMenu(Scanner scanner) {

        boolean running = true;
        while (running) {
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> {
                    LocalDate today = LocalDate.now();

                    LocalDate startOfMonth =
                            today.withDayOfMonth(1);

                    filterTransactionsByDate(startOfMonth, today);
                }
                case "2" -> {
                    LocalDate previousMonth =
                            LocalDate.now().minusMonths(1);

                    LocalDate start =
                            previousMonth.withDayOfMonth(1);

                    LocalDate end =
                            previousMonth.withDayOfMonth(
                                    previousMonth.lengthOfMonth());


                    filterTransactionsByDate(start, end);
                }
                case "3" -> {
                    LocalDate today = LocalDate.now();

                    LocalDate startOfYear =
                            today.withDayOfYear(1);

                    filterTransactionsByDate(startOfYear, today);
                }
                case "4" -> {
                    LocalDate previousYear =
                            LocalDate.now().minusYears(1);

                    LocalDate start =
                            previousYear.withDayOfYear(1);

                    LocalDate end =
                            previousYear.withDayOfYear(
                                    previousYear.lengthOfYear());


                    filterTransactionsByDate(start, end);
                }
                case "5" -> {
                    System.out.println("Vendor name: ");
                    String vendor = scanner.nextLine();

                    filterTransactionsByVendor(vendor);
                }
                case "0" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }


    private static void filterTransactionsByDate(LocalDate start, LocalDate end) {
        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getDate();

            if ((date.isEqual(start) || date.isAfter(start))
                    &&
                    (date.isEqual(end) || date.isBefore(end))) {
                System.out.println(transaction);
            }
        }
    }

    private static void filterTransactionsByVendor(String vendor) {
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)){
                System.out.println(transaction);
            }
        }
    }
}

# FinanceTracker – Java Ledger Capstone

A Java-based command-line financial tracking application designed to help users manage, record, and monitor financial transactions. Built as a capstone project to demonstrate object-oriented programming, file handling, data management, and application development skills.

## Overview

FinanceTracker is a financial ledger application that allows users to track income and expenses, manage transactions, and monitor their account balance through an interactive command-line interface.

The application is designed for personal or small-business financial tracking, providing a straightforward way to record transactions and review financial activity.

## Features

- **Transaction Management** – Add, view, and manage financial transactions.
- **Income Tracking** – Record incoming funds and monitor deposits.
- **Expense Tracking** – Record outgoing payments and purchases.
- **Live Balance Updates** – Automatically update the account balance after each transaction.
- **Starting Balance** – Begins with an initial balance of $10,000.
- **Transaction History** – Review previously recorded financial activity.
- **CSV File Storage** – Save and load transactions using `transactions.csv`.
- **Date and Time Tracking** – Associate transactions with their respective dates and times.
- **Command-Line Interface** – Navigate the application through an interactive menu.
- **Exception Handling** – Handle unexpected input and application errors.

## Technologies Used

- Java 17
- IntelliJ IDEA
- Object-Oriented Programming (OOP)
- Java Collections Framework
- File I/O
- CSV Data Storage
- Git and GitHub
- JPackage

## Project Structure

```text
FinanceTracker/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── pluralsight/
│                   ├── Program.java
│                   ├── Transaction.java
│                   ├── Reports.java
│                   └── Console.java
│
├── transactions.csv
├── pom.xml
└── README.md
```

## How It Works

1. Launch FinanceTracker.
2. Navigate through the command-line menu.
3. Add income or expense transactions.
4. Enter the transaction information, including description, vendor, amount, date, and time.
5. The application updates the account balance.
6. Transactions are saved to `transactions.csv` for future access.
7. Review transaction history and financial reports.

## Transaction Data Format

Transactions are stored in a pipe-delimited CSV file using the following format:

```text
date|time|description|vendor|amount
```

Example:

```text
2023-04-15|10:13:25|Groceries|Walmart|-85.50
2023-04-16|14:30:00|Paycheck|Employer|2500.00
```

## Running the Application

### Run Through IntelliJ IDEA

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA.
3. Make sure Java 17 is configured.
4. Locate `Program.java`.
5. Run the `main()` method.

### Run the Packaged Windows Application

FinanceTracker can also be packaged as a standalone Windows application using Java JPackage.

The packaged application includes a Java runtime, allowing users to run it without separately installing Java.

## What I Learned

Through this capstone project, I developed practical experience with:

- Building a Java application using object-oriented programming.
- Organizing code into multiple classes.
- Managing financial records with Java collections.
- Reading and writing data using file handling.
- Implementing input validation and exception handling.
- Working with dates, times, and monetary values.
- Using Git and GitHub for version control.
- Packaging a Java application for Windows distribution.

## Future Improvements

- Monthly and yearly financial summaries.
- Search and filter transactions.
- Export financial reports.
- Improved graphical user interface.
- Multiple account support

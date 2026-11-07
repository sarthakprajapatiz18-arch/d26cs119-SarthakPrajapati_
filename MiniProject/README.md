# MiniBank – Java Banking Management System

## 1. Project Overview

MiniBank is a console-based Banking Management System developed using Java. It simulates basic banking operations such as creating bank accounts, depositing money, withdrawing money, transferring funds, viewing account details, and generating transaction reports.

The project demonstrates important Object-Oriented Programming (OOP) concepts, Java annotations, exception handling, file handling, serialization, and collection management.

## 2. Objectives

- Develop a simple banking application using Java.
- Implement different types of bank accounts.
- Perform basic banking transactions.
- Apply Object-Oriented Programming concepts.
- Use custom annotations and reflection.
- Implement exception handling and input validation.
- Store account and transaction information using files.
- Generate transaction reports.

## 3. Technologies Used

- **Programming Language:** Java
- **JDK:** Java Development Kit
- **IDE:** Visual Studio Code / IntelliJ IDEA / Eclipse
- **Version Control:** Git and GitHub
- **Storage:** File Handling and Java Serialization

## 4. Features

### Account Management
- Create Savings Accounts.
- Create Current Accounts.
- Create Fixed Deposit Accounts.
- Generate unique account IDs.
- View account details.
- List available accounts.

### Banking Operations
- Deposit money.
- Withdraw money.
- Transfer money between accounts.
- View transaction statements.
- Validate transaction amounts.
- Check account balances.

### Reporting
- Generate transaction reports.
- Calculate deposit and withdrawal totals.
- Store transaction records.

### Validation and Security
- Validate account information.
- Handle invalid inputs.
- Prevent withdrawals exceeding the available balance, subject to account rules.
- Handle inactive accounts.
- Use custom exceptions for error handling.

## 5. OOP Concepts Implemented

The project demonstrates the following Java concepts:

1. **Classes and Objects:** Used to represent customers, accounts, and transactions.
2. **Inheritance:** Different account types inherit common properties and methods from the Account class.
3. **Polymorphism:** Account-specific behaviour is implemented through method overriding.
4. **Encapsulation:** Data members are protected using access modifiers.
5. **Abstraction:** Common banking operations are represented through abstract behaviour and methods.
6. **Interfaces:** Used to define common functionality.
7. **Exception Handling:** Handles invalid operations and runtime errors.
8. **Serialization:** Used to save account-related data.
9. **Collections:** Used to manage account and transaction records.
10. **Annotations and Reflection:** Used for validation and identifying annotated fields.

## 6. Project Structure

```text
MiniProject/
│
├── src/
│   ├── model/
│   │   ├── Account.java
│   │   ├── SavingsAccount.java
│   │   ├── CurrentAccount.java
│   │   ├── FixedDepositAccount.java
│   │   ├── Customer.java
│   │   └── Transaction.java
│   │
│   ├── service/
│   │   ├── BankService.java
│   │   └── Repository.java
│   │
│   ├── util/
│   │   ├── CommandParser.java
│   │   ├── AnnotationValidator.java
│   │   └── ReportGenerator.java
│   │
│   └── MiniBank.java
│
├── tests/
│   └── MiniBankRegressionTest.java
│
├── out/
├── README.md
└── TEST_REPORT.md
package app;

import domain.Account;
import domain.Transaction;
import exception.BankingException;
import exception.InvalidAmountException;
import service.BankingService;

// Coordinates the console flow of the application after startup.
public class BankingApp {
    private final BankingService bankingService;
    private final Console console;
    private final LoginService loginService;

    public BankingApp(Console console, LoginService loginService, BankingService bankingService) {
        this.console = console;
        this.loginService = loginService;
        this.bankingService = bankingService;
    }
    // Starts the user interaction flow: login first, then one menu action.
    public void run() {
        LoginResult loginResult = loginService.login();

        if (!loginResult.isSuccess()) {
            console.printError(loginResult.getMessage());
            return;
        }

        Account userAccount = loginResult.getAccount();
        console.printMessage("Hello " + userAccount.getAccountHolder() + " you are logged in.");

        showMenu();
        String choice = console.readLine();
        handleChoice(choice, userAccount);
    }

    private void showMenu() {
        console.printMessage("1. Show Balance");
        console.printMessage("2. Show History");
        console.printMessage("3. Deposit");
        console.printMessage("4. Withdraw");
        console.printMessage("5. Transfer");
        console.printMessage("6. Exit");
    }

    // Routes the selected menu option to the matching use case.
    private void handleChoice(String choice, Account userAccount) {
        try {
            switch (choice) {
                case "1":
                    console.printMessage("Your balance is: " + userAccount.getBalance());
                    break;
                case "2":
                    showHistory(userAccount);
                    break;
                case "3":
                    handleDeposit(userAccount);
                    break;
                case "4":
                    handleWithdraw(userAccount);
                    break;
                case "5":
                    handleTransfer(userAccount);
                    break;
                case "6":
                    console.printMessage("Exiting...");
                    break;
                default:
                    console.printError("Invalid input.");
            }
        } catch (BankingException exception) {
            console.printError(exception.getMessage());
        }
    }

    private void showHistory(Account account) {
        java.util.List<Transaction> transactions = bankingService.getHistoryForAccount(account.getAccountId());

        if (transactions.isEmpty()) {
            console.printMessage("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {
            console.printMessage(transaction.toString());
        }
    }

    private void handleDeposit(Account account) {
        console.printMessage("Enter amount to deposit:");
        double amount = readAmount();
        bankingService.deposit(account, amount);
        console.printMessage("The new balance is: " + account.getBalance());
    }

    private void handleWithdraw(Account account) {
        console.printMessage("Enter amount to withdraw:");
        double amount = readAmount();
        console.printMessage("Enter your PIN:");
        String inputPin = console.readLine();

        bankingService.withdraw(account, inputPin, amount);
        console.printMessage("The new balance is: " + account.getBalance());
    }

    private void handleTransfer(Account account) {
        console.printMessage("Enter receiver ID:");
        String receiverId = console.readLine();
        console.printMessage("Enter amount to send:");
        double amount = readAmount();
        console.printMessage("Enter your PIN:");
        String inputPin = console.readLine();

        bankingService.transfer(account.getAccountId(), receiverId, inputPin, amount);
        console.printMessage("The new balance is: " + account.getBalance());
    }

    // Reads numeric input and converts parsing problems into the same
    // business exception flow used by the rest of the application.
    private double readAmount() {
        try {
            return Double.parseDouble(console.readLine());
        } catch (NumberFormatException exception) {
            throw new InvalidAmountException("Please enter a valid amount.");
        }
    }
}

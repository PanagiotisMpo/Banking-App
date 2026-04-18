package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

        private final String accountHolder;
        private final String iban;
        private final String accountId;
        private double balance;
        private final String pin;
        private final ArrayList<Transaction> transactions;

        public Account(String accountHolder, String iban, String accountId, double balance, String pin)
        {
            this.accountHolder = accountHolder;
            this.iban = iban;
            this.accountId = accountId;
            this.balance = balance;
            this.pin = pin;
            this.transactions = new ArrayList<>();

        }

        public String getAccountHolder()
        {
            return accountHolder;
        }

        public String getIban()
        {
            return iban;
        }

        public String getAccountId()
        {
            return accountId;
        }

        public double getBalance()
        {
            return balance;
        }

        public List<Transaction> getTransactions(){ return Collections.unmodifiableList(transactions); }

        public boolean validatePin(String inputPin) {return pin.equals(inputPin);}

        public void deposit(double amount) {
            balance += amount;
            addTransactionToHistory(TransactionType.DEPOSIT, amount);
        }

        public void withdraw(double amount) {
            balance -= amount;
            addTransactionToHistory(TransactionType.WITHDRAW, amount);
        }

        public void transferOut(double amount) {
            balance -= amount;
            addTransactionToHistory(TransactionType.TRANSFER_OUT, amount);
        }

        public void transferIn(double amount) {
            balance += amount;
            addTransactionToHistory(TransactionType.TRANSFER_IN, amount);
        }

        private void addTransactionToHistory(TransactionType transactionType, double amount)
        {
            transactions.add(new Transaction(transactionType, amount));
        }
}

package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Transaction;

public class TransactionRepository {
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getAll() {
        return Collections.unmodifiableList(transactions);
    }

    public List<Transaction> findByAccountId(String accountId) {
        ArrayList<Transaction> accountTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (accountId.equals(transaction.getSourceAccountId()) || accountId.equals(transaction.getTargetAccountId())) {
                accountTransactions.add(transaction);
            }
        }

        return Collections.unmodifiableList(accountTransactions);
    }
}

package service;

import domain.Account;
import domain.Transaction;
import domain.TransactionType;
import repository.AccountRepository;
import repository.TransactionRepository;

public class LedgerService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public LedgerService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void apply(Transaction transaction) {
        if (transaction.getType() == TransactionType.DEPOSIT) {
            Account target = accountRepository.findById(transaction.getTargetAccountId());
            target.increaseBalance(transaction.getAmount());
            transactionRepository.add(transaction);
            return;
        }

        if (transaction.getType() == TransactionType.WITHDRAW) {
            Account source = accountRepository.findById(transaction.getSourceAccountId());
            source.decreaseBalance(transaction.getAmount());
            transactionRepository.add(transaction);
            return;
        }

        if (transaction.getType() == TransactionType.TRANSFER) {
            Account source = accountRepository.findById(transaction.getSourceAccountId());
            Account target = accountRepository.findById(transaction.getTargetAccountId());

            source.decreaseBalance(transaction.getAmount());
            target.increaseBalance(transaction.getAmount());
            transactionRepository.add(transaction);
        }
    }
}

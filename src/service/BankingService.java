package service;

import domain.Account;
import domain.Transaction;
import domain.TransactionType;
import repository.AccountRepository;
import repository.TransactionRepository;

// Executes banking operations after validation has passed.
public class BankingService {
    private final AccountRepository repository;
    private final TransactionRepository transactionRepository;
    private final LedgerService ledgerService;
    private final TransactionValidator validator;

    public BankingService(AccountRepository repository, TransactionRepository transactionRepository, LedgerService ledgerService, TransactionValidator validator) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
        this.ledgerService = ledgerService;
        this.validator = validator;
    }

    public void deposit(Account account, double amount) {
        validator.validateDeposit(account, amount);
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, null, account.getAccountId());
        ledgerService.apply(transaction);
    }

    public void withdraw(Account account, String inputPin, double amount) {
        validator.validateWithdraw(account, inputPin, amount);
        Transaction transaction = new Transaction(TransactionType.WITHDRAW, amount, account.getAccountId(), null);
        ledgerService.apply(transaction);
    }

    public void transfer(String senderAccountId, String receiverAccountId, String inputPin, double amount) {
        Account sender = repository.findById(senderAccountId);
        Account receiver = repository.findById(receiverAccountId);

        validator.validateTransfer(sender, receiver, inputPin, amount);

        Transaction transaction = new Transaction(TransactionType.TRANSFER, amount, senderAccountId, receiverAccountId);
        ledgerService.apply(transaction);
    }

    public java.util.List<Transaction> getHistoryForAccount(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}

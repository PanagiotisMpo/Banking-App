package service;

import domain.Account;
import repository.AccountRepository;

public class BankingService {
    private final AccountRepository repository;
    private final TransactionValidator validator;

    public BankingService(AccountRepository repository, TransactionValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public void deposit(Account account, double amount) {
        validator.validateDeposit(account, amount);
        account.deposit(amount);
    }

    public void withdraw(Account account, String inputPin, double amount) {
        validator.validateWithdraw(account, inputPin, amount);
        account.withdraw(amount);
    }

    public void transfer(String senderAccountId, String receiverAccountId, String inputPin, double amount) {
        Account sender = repository.findById(senderAccountId);
        Account receiver = repository.findById(receiverAccountId);

        validator.validateTransfer(sender, receiver, inputPin, amount);

        sender.transferOut(amount);
        receiver.transferIn(amount);
    }
}

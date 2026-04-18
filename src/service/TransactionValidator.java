package service;

import domain.Account;
import exception.AccountNotFoundException;
import exception.InvalidAccountIdException;
import exception.InvalidAmountException;
import exception.InvalidPinException;
import exception.InsufficientBalanceException;
import exception.SameAccountTransferException;

public class TransactionValidator {
    public void validateDeposit(Account account, double amount) {
        if (account == null) {
            throw new AccountNotFoundException();
        }
        if (amount <= 0) {
            throw new InvalidAmountException();
        }
    }

    public void validateWithdraw(Account account, String inputPin, double amount) {
        if (account == null) {
            throw new AccountNotFoundException();
        }
        if (!account.validatePin(inputPin)) {
            throw new InvalidPinException();
        }
        validateDeposit(account, amount);
        if (amount > account.getBalance()) {
            throw new InsufficientBalanceException();
        }
    }

    public void validateTransfer(Account sender, Account receiver, String inputPin, double amount) {
        if (sender == null || receiver == null) {
            throw new InvalidAccountIdException();
        }
        if (sender.getAccountId().equals(receiver.getAccountId())) {
            throw new SameAccountTransferException();
        }
        validateWithdraw(sender, inputPin, amount);
    }
}

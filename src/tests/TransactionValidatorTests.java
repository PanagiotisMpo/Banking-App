package tests;

import domain.Account;
import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import exception.InvalidPinException;
import exception.SameAccountTransferException;
import service.TransactionValidator;

public class TransactionValidatorTests {
    private final TransactionValidator validator = new TransactionValidator();

    public void runAll() {
        shouldRejectDepositForNullAccount();
        shouldRejectDepositForNonPositiveAmount();
        shouldRejectWithdrawForWrongPin();
        shouldRejectWithdrawForInsufficientBalance();
        shouldRejectTransferToSameAccount();
    }

    private void shouldRejectDepositForNullAccount() {
        TestAssertions.assertThrows(
                AccountNotFoundException.class,
                () -> validator.validateDeposit(null, 100),
                "validateDeposit should reject null account."
        );
    }

    private void shouldRejectDepositForNonPositiveAmount() {
        Account account = new Account("panos", "GR1", "ACC001", 1000, "1234");

        TestAssertions.assertThrows(
                InvalidAmountException.class,
                () -> validator.validateDeposit(account, 0),
                "validateDeposit should reject zero amount."
        );
    }

    private void shouldRejectWithdrawForWrongPin() {
        Account account = new Account("panos", "GR1", "ACC001", 1000, "1234");

        TestAssertions.assertThrows(
                InvalidPinException.class,
                () -> validator.validateWithdraw(account, "9999", 10),
                "validateWithdraw should reject wrong PIN."
        );
    }

    private void shouldRejectWithdrawForInsufficientBalance() {
        Account account = new Account("panos", "GR1", "ACC001", 50, "1234");

        TestAssertions.assertThrows(
                InsufficientBalanceException.class,
                () -> validator.validateWithdraw(account, "1234", 100),
                "validateWithdraw should reject amount above balance."
        );
    }

    private void shouldRejectTransferToSameAccount() {
        Account account = new Account("panos", "GR1", "ACC001", 1000, "1234");

        TestAssertions.assertThrows(
                SameAccountTransferException.class,
                () -> validator.validateTransfer(account, account, "1234", 100),
                "validateTransfer should reject same-account transfers."
        );
    }
}

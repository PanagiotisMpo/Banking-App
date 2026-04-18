package exception;

public class AccountNotFoundException extends BankingException {
    public AccountNotFoundException() {
        super("Account not found.");
    }
}

package exception;

public class InsufficientBalanceException extends BankingException {
    public InsufficientBalanceException() {
        super("Insufficient balance.");
    }
}

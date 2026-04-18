package exception;

public class InvalidAccountIdException extends BankingException {
    public InvalidAccountIdException() {
        super("Invalid account ID.");
    }
}

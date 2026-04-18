package exception;

public class InvalidPinException extends BankingException {
    public InvalidPinException() {
        super("Invalid PIN.");
    }
}

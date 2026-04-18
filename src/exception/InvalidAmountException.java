package exception;

public class InvalidAmountException extends BankingException {
    public InvalidAmountException() {
        super("Amount must be greater than zero!");
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}

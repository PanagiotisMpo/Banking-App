package exception;

public class SameAccountTransferException extends BankingException {
    public SameAccountTransferException() {
        super("You cannot transfer to the same account.");
    }
}

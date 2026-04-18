package app;

import domain.Account;

public class LoginResult {
    private final Account account;
    private final String message;

    private LoginResult(Account account, String message) {
        this.account = account;
        this.message = message;
    }

    public static LoginResult success(Account account) {
        return new LoginResult(account, "Login successful.");
    }

    public static LoginResult failure(String message) {
        return new LoginResult(null, message);
    }

    public boolean isSuccess() {
        return account != null;
    }

    public Account getAccount() {
        return account;
    }

    public String getMessage() {
        return message;
    }
}

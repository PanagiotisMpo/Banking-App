package app;

import domain.Account;
import repository.AccountRepository;

public class LoginService {
    private final AccountRepository repository;
    private final Console console;

    public LoginService(AccountRepository repository, Console console) {
        this.repository = repository;
        this.console = console;
    }

    public LoginResult login() {
        console.printMessage("Enter account id");
        String userId = console.readLine();

        Account userAccount = repository.findById(userId);
        if (userAccount == null) {
            return LoginResult.failure("Wrong Account ID");
        }

        console.printMessage("Enter your PIN");
        String userPin = console.readLine();

        if (!userAccount.validatePin(userPin)) {
            return LoginResult.failure("Wrong PIN");
        }

        return LoginResult.success(userAccount);
    }
}

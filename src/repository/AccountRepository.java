package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Account;

public class AccountRepository {
    private static AccountRepository instance;
    private final ArrayList<Account> accounts;

    private AccountRepository() {
        accounts = new ArrayList<>();

        accounts.add(new Account("panos", "GR16 0110 1250 0000 0001 2300 695", "ACC001", 1000, "4019"));
        accounts.add(new Account("giorgos", "GR16 0110 1250 0000 0001 2300 696", "ACC002", 500, "4009"));
    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public Account findById(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }
}

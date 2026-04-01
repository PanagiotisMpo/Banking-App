import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;

    public Bank(){
        this.accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account)
    {
        accounts.add(account);
    }
}

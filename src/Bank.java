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

    public Account findAccountById(String accountId)
    {
        for (Account account : accounts)
        {
            if(account.getAccountId().equals(accountId))
            {
                return account;
            }
        }
        return null;
    }

    public void transfer(String senderAccountId, String receiverAccountId, String inputPin, double amount)
    {
        Account sender = findAccountById(senderAccountId);
        Account receiver = findAccountById(receiverAccountId);
        if(sender==null || receiver==null){
            throw new IllegalArgumentException("Invalid Account ID");
        }
        sender.withdraw(inputPin, amount);
        receiver.deposit(amount);
    }
}

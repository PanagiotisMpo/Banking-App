import java.util.ArrayList;

public class  Bank {

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
        if(sender.validateWithdraw(inputPin,amount)){

            /// Subtract amount from balance and add transaction in history
            sender.subtractBalance(amount);
            sender.addTransactionToHistory(TransactionType.TRANSFER_OUT, amount);
            /// Add amount to balance and add transaction in history
            receiver.addBalance(amount);
            receiver.addTransactionToHistory(TransactionType.TRANSFER_IN, amount);

        }
    }
}

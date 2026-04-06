import java.util.ArrayList;

public class Account {

        private String accountHolder;
        private String iban;
        private String accountId;
        private double balance;
        private String pin;
        private ArrayList<Transaction> transactions;

        public Account(String accountHolder, String iban, String accountId, double balance, String pin)
        {
            this.accountHolder = accountHolder;
            this.iban = iban;
            this.accountId = accountId;
            this.balance = balance;
            this.pin = pin;
            this.transactions = new ArrayList<>();

        }

        public String getAccountHolder()
        {
            return accountHolder;
        }

        public String getIban()
        {
            return iban;
        }

        public String getAccountId()
        {
            return accountId;
        }

        public double getBalance()
        {
            return balance;
        }

        public ArrayList<Transaction> getTransactions(){ return transactions; }

        public boolean validatePin(String inputPin) {return pin.equals(inputPin);}

        public void addBalance(double amount) { balance += amount;}

        public void subtractBalance(double amount) {balance -= amount;}

        public void deposit(double amount)
        {
            balance += amount;
            addTransactionToHistory(TransactionType.DEPOSIT, amount);
        }

        public void withdraw(String inputPin, double amount)
        {
            if(validateWithdraw(inputPin, amount)){
                balance -= amount;
                addTransactionToHistory(TransactionType.WITHDRAW, amount);
            }
        }

        public void addTransactionToHistory(TransactionType transactionType, double amount)
        {
            transactions.add(new Transaction(transactionType, amount));
        }

        public boolean validateWithdraw(String inputPin, double amount)
        {
            if(!validatePin(inputPin)){
                throw new IllegalArgumentException("Invalid Pin");
            }
            if(amount > getBalance())
            {
                throw new IllegalArgumentException("Insufficient Balance");
            }
            if(amount <= 0)
            {
                throw new IllegalArgumentException("Amount must be greater than zero!");
            }
            return true;
        }

}

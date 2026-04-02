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

        public void deposit(double amount)
        {
            if(amount <= 0)
            {
               throw new IllegalArgumentException("Deposit amount must be greater than zero.");
            }

            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
        }

        public void withdraw(String inputPin, double amount)
        {
            if(!validatePin(inputPin))
            {
                throw new IllegalArgumentException("Invalid Pin.");
            }
            if(amount <=0)
            {
                throw new IllegalArgumentException("Withdraw amount must be greater than zero.");
            }
            if(amount>balance)
            {
                throw new IllegalArgumentException("Insufficient balance.");
            }

            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
        }

        public boolean validatePin(String inputPin)
        {
            return pin.equals(inputPin);
        }
}

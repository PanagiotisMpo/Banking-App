package domain;

// Domain entity that stores account state.
public class Account {

        private final String accountHolder;
        private final String iban;
        private final String accountId;
        private double balance;
        private final String pin;

        public Account(String accountHolder, String iban, String accountId, double balance, String pin)
        {
            this.accountHolder = accountHolder;
            this.iban = iban;
            this.accountId = accountId;
            this.balance = balance;
            this.pin = pin;
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

        public boolean validatePin(String inputPin) {return pin.equals(inputPin);}

        public void increaseBalance(double amount) {
            this.balance += amount;
        }

        public void decreaseBalance(double amount) {
            this.balance -= amount;
        }
}

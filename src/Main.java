public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Account account1 = new Account("panos", "GR16 0110 1250 0000 0001 2300 695", "ACC001", 1000, "4019");
        bank.addAccount(account1);
        Account account2 = new Account("giorgos", "GR16 0110 1250 0000 0001 2300 696", "ACC002", 500, "4009");
        bank.addAccount(account2);

        System.out.println(account1.getAccountId() + " " + account1.getBalance());
        System.out.println(account2.getAccountId() + " " + account2.getBalance());
        try {
            bank.transfer(account1.getAccountId(), account2.getAccountId(), "4019", 250);

        } catch (IllegalArgumentException  e){
            System.out.println(e.getMessage());
        }
        for(Account account : bank.getAccounts())
        {
            for(Transaction transaction : account.getTransactions()){
                if (transaction.getType().equals(TransactionType.TRANSFER_IN)){
                    System.out.println(transaction.getType().getLabel() + transaction.getAmount());
                }
                else if(transaction.getType().equals(TransactionType.TRANSFER_OUT)){
                    System.out.println(transaction.getType().getLabel() + transaction.getAmount());
                }
            }
        }


    }


}

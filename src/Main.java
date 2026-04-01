public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Account account1 = new Account("panos", "GR16 0110 1250 0000 0001 2300 695", "ACC001", 1000, "4019");
        bank.addAccount(account1);
        Account account2 = new Account("giorgos", "GR16 0110 1250 0000 0001 2300 696", "ACC002", 500, "4009");
        bank.addAccount(account2);
    }

}

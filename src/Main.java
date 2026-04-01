public class Main {
    public static void main(String[] args) {
        Account account =  new Account("panos", "GR16 0110 1250 0000 0001 2300 695", "ACC001", 1000, "4019");
        try{
            account.withdraw("0001", 250);
        } catch(IllegalArgumentException  e) {
            System.out.println(e.getMessage());
        }
        System.out.println(account.getBalance());
        try{
            account.withdraw("4019", 250);
        } catch(IllegalArgumentException  e) {
            System.out.println(e.getMessage());
        }
        System.out.println(account.getBalance());

    }

}

package app;

import repository.AccountRepository;
import service.BankingService;
import service.TransactionValidator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountRepository repository = AccountRepository.getInstance();
        Scanner scanner = new Scanner(System.in);
        Console console = new StandardConsole(scanner);
        TransactionValidator validator = new TransactionValidator();
        BankingService bankingService = new BankingService(repository, validator);
        LoginService loginService = new LoginService(repository, console);
        BankingApp bankingApp = new BankingApp(console, loginService, bankingService);

        bankingApp.run();
    }
}

package app;

import repository.AccountRepository;
import repository.TransactionRepository;
import service.BankingService;
import service.LedgerService;
import service.TransactionValidator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountRepository repository = AccountRepository.getInstance();
        TransactionRepository transactionRepository = new TransactionRepository();
        Scanner scanner = new Scanner(System.in);
        Console console = new StandardConsole(scanner);
        TransactionValidator validator = new TransactionValidator();
        LedgerService ledgerService = new LedgerService(repository, transactionRepository);
        BankingService bankingService = new BankingService(repository, transactionRepository, ledgerService, validator);
        LoginService loginService = new LoginService(repository, console);
        BankingApp bankingApp = new BankingApp(console, loginService, bankingService);

        bankingApp.run();
    }
}

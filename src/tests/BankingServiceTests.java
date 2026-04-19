package tests;

import domain.Account;
import domain.TransactionType;
import repository.AccountRepository;
import repository.TransactionRepository;
import service.BankingService;
import service.LedgerService;
import service.TransactionValidator;

public class BankingServiceTests {
    public void runAll() {
        shouldDepositAndRecordTransaction();
        shouldWithdrawAndRecordTransaction();
        shouldTransferBetweenAccountsAndRecordBothSides();
    }

    private void shouldDepositAndRecordTransaction() {
        AccountRepository repository = AccountRepository.getInstance();
        repository.resetToDefaults();
        TransactionRepository transactionRepository = new TransactionRepository();
        BankingService service = new BankingService(repository, transactionRepository, new LedgerService(repository, transactionRepository), new TransactionValidator());
        Account account = repository.findById("ACC001");

        service.deposit(account, 250);

        TestAssertions.assertEquals(1250.0, account.getBalance(), "Deposit should increase balance.");
        TestAssertions.assertEquals(1, service.getHistoryForAccount(account.getAccountId()).size(), "Deposit should create one transaction.");
        TestAssertions.assertEquals(TransactionType.DEPOSIT, service.getHistoryForAccount(account.getAccountId()).get(0).getType(), "Deposit should record DEPOSIT transaction.");
    }

    private void shouldWithdrawAndRecordTransaction() {
        AccountRepository repository = AccountRepository.getInstance();
        repository.resetToDefaults();
        TransactionRepository transactionRepository = new TransactionRepository();
        BankingService service = new BankingService(repository, transactionRepository, new LedgerService(repository, transactionRepository), new TransactionValidator());
        Account account = repository.findById("ACC001");

        service.withdraw(account, "1234", 200);

        TestAssertions.assertEquals(800.0, account.getBalance(), "Withdraw should decrease balance.");
        TestAssertions.assertEquals(1, service.getHistoryForAccount(account.getAccountId()).size(), "Withdraw should create one transaction.");
        TestAssertions.assertEquals(TransactionType.WITHDRAW, service.getHistoryForAccount(account.getAccountId()).get(0).getType(), "Withdraw should record WITHDRAW transaction.");
    }

    private void shouldTransferBetweenAccountsAndRecordBothSides() {
        AccountRepository repository = AccountRepository.getInstance();
        repository.resetToDefaults();
        TransactionRepository transactionRepository = new TransactionRepository();
        BankingService service = new BankingService(repository, transactionRepository, new LedgerService(repository, transactionRepository), new TransactionValidator());

        Account sender = repository.findById("ACC001");
        Account receiver = repository.findById("ACC002");

        double senderBalanceBefore = sender.getBalance();
        double receiverBalanceBefore = receiver.getBalance();
        int senderTransactionsBefore = service.getHistoryForAccount(sender.getAccountId()).size();
        int receiverTransactionsBefore = service.getHistoryForAccount(receiver.getAccountId()).size();

        service.transfer("ACC001", "ACC002", "4019", 100);

        TestAssertions.assertEquals(senderBalanceBefore - 100, sender.getBalance(), "Transfer should decrease sender balance.");
        TestAssertions.assertEquals(receiverBalanceBefore + 100, receiver.getBalance(), "Transfer should increase receiver balance.");
        TestAssertions.assertEquals(senderTransactionsBefore + 1, service.getHistoryForAccount(sender.getAccountId()).size(), "Transfer should add sender transaction.");
        TestAssertions.assertEquals(receiverTransactionsBefore + 1, service.getHistoryForAccount(receiver.getAccountId()).size(), "Transfer should add receiver transaction.");
        TestAssertions.assertEquals(TransactionType.TRANSFER, service.getHistoryForAccount(sender.getAccountId()).get(service.getHistoryForAccount(sender.getAccountId()).size() - 1).getType(), "Sender should record TRANSFER.");
        TestAssertions.assertEquals(TransactionType.TRANSFER, service.getHistoryForAccount(receiver.getAccountId()).get(service.getHistoryForAccount(receiver.getAccountId()).size() - 1).getType(), "Receiver should record TRANSFER.");
    }
}

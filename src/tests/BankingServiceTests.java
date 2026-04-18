package tests;

import domain.Account;
import domain.TransactionType;
import repository.AccountRepository;
import service.BankingService;
import service.TransactionValidator;

public class BankingServiceTests {
    public void runAll() {
        shouldDepositAndRecordTransaction();
        shouldWithdrawAndRecordTransaction();
        shouldTransferBetweenAccountsAndRecordBothSides();
    }

    private void shouldDepositAndRecordTransaction() {
        Account account = new Account("panos", "GR1", "ACC001", 1000, "1234");
        BankingService service = new BankingService(AccountRepository.getInstance(), new TransactionValidator());

        service.deposit(account, 250);

        TestAssertions.assertEquals(1250.0, account.getBalance(), "Deposit should increase balance.");
        TestAssertions.assertEquals(1, account.getTransactions().size(), "Deposit should create one transaction.");
        TestAssertions.assertEquals(TransactionType.DEPOSIT, account.getTransactions().get(0).getType(), "Deposit should record DEPOSIT transaction.");
    }

    private void shouldWithdrawAndRecordTransaction() {
        Account account = new Account("panos", "GR1", "ACC001", 1000, "1234");
        BankingService service = new BankingService(AccountRepository.getInstance(), new TransactionValidator());

        service.withdraw(account, "1234", 200);

        TestAssertions.assertEquals(800.0, account.getBalance(), "Withdraw should decrease balance.");
        TestAssertions.assertEquals(1, account.getTransactions().size(), "Withdraw should create one transaction.");
        TestAssertions.assertEquals(TransactionType.WITHDRAW, account.getTransactions().get(0).getType(), "Withdraw should record WITHDRAW transaction.");
    }

    private void shouldTransferBetweenAccountsAndRecordBothSides() {
        AccountRepository repository = AccountRepository.getInstance();
        BankingService service = new BankingService(repository, new TransactionValidator());

        Account sender = repository.findById("ACC001");
        Account receiver = repository.findById("ACC002");

        double senderBalanceBefore = sender.getBalance();
        double receiverBalanceBefore = receiver.getBalance();
        int senderTransactionsBefore = sender.getTransactions().size();
        int receiverTransactionsBefore = receiver.getTransactions().size();

        service.transfer("ACC001", "ACC002", "4019", 100);

        TestAssertions.assertEquals(senderBalanceBefore - 100, sender.getBalance(), "Transfer should decrease sender balance.");
        TestAssertions.assertEquals(receiverBalanceBefore + 100, receiver.getBalance(), "Transfer should increase receiver balance.");
        TestAssertions.assertEquals(senderTransactionsBefore + 1, sender.getTransactions().size(), "Transfer should add sender transaction.");
        TestAssertions.assertEquals(receiverTransactionsBefore + 1, receiver.getTransactions().size(), "Transfer should add receiver transaction.");
        TestAssertions.assertEquals(TransactionType.TRANSFER_OUT, sender.getTransactions().get(sender.getTransactions().size() - 1).getType(), "Sender should record TRANSFER_OUT.");
        TestAssertions.assertEquals(TransactionType.TRANSFER_IN, receiver.getTransactions().get(receiver.getTransactions().size() - 1).getType(), "Receiver should record TRANSFER_IN.");
    }
}

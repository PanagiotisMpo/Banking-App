package tests;

public class TestRunner {
    public static void main(String[] args) {
        run("TransactionValidatorTests", () -> new TransactionValidatorTests().runAll());
        run("BankingServiceTests", () -> new BankingServiceTests().runAll());
    }

    private static void run(String testSuiteName, Runnable testSuite) {
        try {
            testSuite.run();
            System.out.println(testSuiteName + ": PASSED");
        } catch (AssertionError assertionError) {
            System.out.println(testSuiteName + ": FAILED");
            System.out.println(assertionError.getMessage());
        } catch (Exception exception) {
            System.out.println(testSuiteName + ": ERROR");
            System.out.println(exception.getMessage());
        }
    }
}

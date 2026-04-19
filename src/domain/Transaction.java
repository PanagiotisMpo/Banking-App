
package domain;

public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final String sourceAccountId;
    private final String targetAccountId;

    public Transaction(TransactionType type, double amount, String sourceAccountId, String targetAccountId)
    {
        this.type = type;
        this.amount = amount;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
    }

    public TransactionType getType(){ return type; }

    public double getAmount(){ return amount; }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    @Override
    public String toString() {
        switch (type) {
            case DEPOSIT:
                return type.getLabel() + " " + amount + " to " + targetAccountId;
            case WITHDRAW:
                return type.getLabel() + " " + amount + " from " + sourceAccountId;
            case TRANSFER:
                return type.getLabel() + " " + amount + " from " + sourceAccountId + " to " + targetAccountId;
            default:
                return type.getLabel() + " " + amount;
        }
    }
}


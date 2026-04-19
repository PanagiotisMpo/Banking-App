package domain;

public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw"),
    TRANSFER("Transfer");

    private final String label;

    TransactionType(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}

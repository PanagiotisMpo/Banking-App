public enum TransactionType {
    DEPOSIT("Deposit "),
    WITHDRAW("Withdraw "),
    TRANSFER_IN("Transfer In "),
    TRANSFER_OUT("Transfer Out ");

    private final String label;

    TransactionType(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}

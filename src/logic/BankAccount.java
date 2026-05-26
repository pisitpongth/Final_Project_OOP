package logic;

public class BankAccount {
    private String accountID;
    private String password;
    private double accountBalance;
    private final String filePath = "src/database/account-data.txt";

    public BankAccount(String accountID, String password, double accountBalance) {
        this.accountID = accountID;
        this.password = password;
        this.accountBalance = accountBalance;
    }

    public BankAccount(String accountID, String password) {
        this.accountID = accountID;
        this.password = password;
        this.accountBalance = 0;
    }

    public BankAccount(String accountID) {
        this.accountID = accountID;
        this.password = "";
        this.accountBalance = 0;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return accountID + "," + password;
    }
}

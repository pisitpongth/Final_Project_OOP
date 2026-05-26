package logic;

import java.io.*;
import java.util.Scanner;

public class AccountTransaction extends BankAccount implements Transactionable {

    private boolean isInsufficient;

    public AccountTransaction(String accountID, String password) throws FileNotFoundException {
        super(accountID, password);

        File file = new File(super.getFilePath());
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");

            if (data[0].equals(accountID) && data[1].equals(password)) {
                double accountBalance = Double.parseDouble(data[2]);
                setAccountBalance(accountBalance);
                break;
            }
        }
        sc.close();
    }

    public AccountTransaction(String accountID) throws FileNotFoundException {
        super(accountID);

        File file = new File(super.getFilePath());
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");

            if (data[0].equals(accountID)) {
                double accountBalance = Double.parseDouble(data[2]);
                setAccountBalance(accountBalance);
                break;
            }
        }
        sc.close();
    }

    public boolean hasAccountID() throws FileNotFoundException {
        File file = new File(super.getFilePath());
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");

            if (super.getAccountID().equals(data[0])) {
                sc.close();
                return true;
            }
        }
        sc.close();
        return false;
    }

    public boolean hasValidCredentials() throws FileNotFoundException {
        File file = new File(super.getFilePath());
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");

            if (data[0].equals(super.getAccountID()) && data[1].equals(super.getPassword())) {
                sc.close();
                return true;
            }
        }
        sc.close();
        return false;
    }

    @Override
    public void deposit(double amount) throws IOException {
        super.setAccountBalance(super.getAccountBalance() + amount);
        updateFile(super.getAccountID(), amount, true);
    }

    @Override
    public void withdraw(double amount) throws IOException {

        if (super.getAccountBalance() < amount) {

            isInsufficient = true;

        } else {

            super.setAccountBalance(super.getAccountBalance() - amount);
            updateFile(super.getAccountID(), amount, false);
            isInsufficient = false;

        }
    }

    @Override
    public double checkBalance() throws FileNotFoundException {
        File file = new File(super.getFilePath());
        Scanner sc = new Scanner(file);

        double accountBalance = 0;

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");
            String accountID = data[0];
            accountBalance = Double.parseDouble(data[2]);

            if (super.getAccountID().equals(accountID)) {
                break;
            }
        }

        sc.close();
        return accountBalance;
    }

    public void updateFile(String accountID, double accountBalance, boolean isDeposit) throws IOException {
        File file = new File(super.getFilePath());

        Scanner sc = new Scanner(file);
        String newData = "";

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");

            String rewriteAccountID = data[0];
            String rewritePassword = data[1];
            double rewriteAccountBalance = Double.parseDouble(data[2]);

            if (rewriteAccountID.equals(accountID)) {
                if (isDeposit) {
                    rewriteAccountBalance += accountBalance;
                } else {
                    rewriteAccountBalance -= accountBalance;
                }
            }

            newData += rewriteAccountID + "," + rewritePassword + "," + rewriteAccountBalance + "\n";
        }

        sc.close();

        PrintWriter writer = new PrintWriter(new FileWriter(file));

        writer.print(newData);
        writer.close();
    }

    public boolean isInsufficient() {

        return isInsufficient;

    }

    public void transferMoney(String accountID, double accountBalance) throws IOException {
        File file = new File(super.getFilePath());

        Scanner sc = new Scanner(file);
        String newData = "";

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");

            String rewriteAccountID = data[0];
            String rewritePassword = data[1];
            double rewriteAccountBalance = Double.parseDouble(data[2]);

            if (rewriteAccountID.equals(accountID)) {
                rewriteAccountBalance += accountBalance;
            }

            newData += rewriteAccountID + "," + rewritePassword + "," + rewriteAccountBalance + "\n";
        }

        sc.close();

        PrintWriter writer = new PrintWriter(new FileWriter(file));

        writer.print(newData);
        writer.close();
    }
}

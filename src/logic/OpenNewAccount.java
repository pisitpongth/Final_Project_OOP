package logic;

import java.io.*;
import java.util.Scanner;

public class OpenNewAccount extends BankAccount {
    public OpenNewAccount(String accountID, String password, double accountBalance) {
        super(accountID, password, accountBalance);
    }

    public String recordAccount() throws IOException {
        FileWriter file = new FileWriter(super.getFilePath(), true);
        PrintWriter writer = new PrintWriter(file);

        if (isExistingAccount(super.getAccountID())) {
            writer.close();
            return "This account has been created!!";
        } else {
            writer.println(getAccountID() + "," + getPassword() + "," + getAccountBalance());
            writer.close();
            return "Created account success!!";
        }
    }

    public boolean isExistingAccount(String accountID) throws FileNotFoundException {
        File file = new File(super.getFilePath());
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");

            if (data[0].equals(accountID)) {
                sc.close();
                return true;
            }
        }
        sc.close();
        return false;
    }
}

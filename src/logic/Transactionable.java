package logic;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Transactionable {
    public void deposit(double amount) throws IOException;

    public void withdraw(double amount) throws IOException;

    public double checkBalance() throws FileNotFoundException;
}

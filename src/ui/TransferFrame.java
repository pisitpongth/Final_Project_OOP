package ui;

import logic.AccountTransaction;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TransferFrame extends JFrame {
    private JPanel transferFrame;
    private JLabel lbl_main_label;
    private JTextField txt_account_id;
    private JTextField txt_amount;
    private JButton btn_confirm;
    private JButton btn_back;
    private JLabel lbl_account_id;
    private JLabel lbl_amount;
    private JLabel lbl_show_account_balance;
    private JLabel lbl_account_balance;

    private AccountTransaction transaction;

    public TransferFrame(AccountTransaction transaction) {

        setContentPane(transferFrame);
        setTitle("Banking Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        this.transaction = transaction;

        String accountBalance = String.format("%.2f", transaction.getAccountBalance());

        lbl_account_balance.setText(accountBalance);

        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                ServiceFrame serviceFrame = new ServiceFrame(transaction);

            }
        });

        btn_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String accountID = txt_account_id.getText();

                try {

                    AccountTransaction tempTransaction = new AccountTransaction(accountID);

                    if (tempTransaction.hasAccountID()) {

                        double amount = Double.parseDouble(txt_amount.getText());

                        tempTransaction.transferMoney(accountID, amount);

                        transaction.withdraw(amount);

                        lbl_account_balance.setText(String.format("%.2f", transaction.getAccountBalance()));

                        txt_account_id.setText("");
                        txt_amount.setText("");

                        JOptionPane.showMessageDialog(null,
                                "Transferred successfully!!");

                    } else {

                        txt_account_id.setText("");
                        txt_amount.setText("");

                        JOptionPane.showMessageDialog(null,
                                "Bank account not found...",
                                "Warning Message",
                                JOptionPane.WARNING_MESSAGE);

                    }

                } catch (IOException ioException) {

                    JOptionPane.showMessageDialog(null,
                            "Something went wrong...",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);

                } catch (NumberFormatException numberFormatException) {

                    txt_account_id.setText("");
                    txt_amount.setText("");

                    JOptionPane.showMessageDialog(null,
                            "Invalid data type!! Try again...",
                            "Warning Message",
                            JOptionPane.WARNING_MESSAGE);

                }
            }
        });
    }
}

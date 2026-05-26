package ui;

import logic.AccountTransaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ServiceFrame extends JFrame {
    private JPanel serviceFrame;
    private JLabel lbl_main_label;
    private JButton btn_deposit;
    private JButton btn_withdraw;
    private JButton btn_check_balance;
    private JButton btn_sign_out;
    private JLabel lbl_show_account_id;
    private JLabel lbl_show_account_balance;
    private JLabel lbl_account_balance;
    private JLabel lbl_account_id;
    private JButton btn_transfer;

    private AccountTransaction transaction;

    public ServiceFrame(AccountTransaction transaction) {

        this.setContentPane(serviceFrame);
        this.setTitle("Banking Application");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        this.transaction = transaction;

        lbl_account_id.setText(transaction.getAccountID());

        btn_sign_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                MainFrame mainFrame = new MainFrame();

            }
        });

        btn_check_balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double accountBalance = transaction.getAccountBalance();

                String showAccountBalance = String.format("%.2f", accountBalance);

                JOptionPane.showMessageDialog(null,
                        "Account Balance updated successfully!!");

                lbl_account_balance.setText(showAccountBalance);

            }
        });

        btn_deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    double amount = Double.parseDouble(JOptionPane.showInputDialog("How much do you want to deposit?"));

                    transaction.deposit(amount);

                    JOptionPane.showMessageDialog(null,
                            "Transaction successfully!!");

                } catch (NumberFormatException numberFormatException) {

                    JOptionPane.showMessageDialog(null,
                            "Invalid data type!! Try again...",
                            "Warning Message",
                            JOptionPane.WARNING_MESSAGE);

                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(null,
                            "Something went wrong...",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);

                } catch (NullPointerException nullPointerException) {

                    JOptionPane.showMessageDialog(null,
                            "Transaction was cancelled!!");

                }
            }
        });

        btn_withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    double amount = Double.parseDouble(JOptionPane.showInputDialog("How much do you want to withdraw?"));

                    transaction.withdraw(amount);

                    if (transaction.isInsufficient()) {

                        JOptionPane.showMessageDialog(null,
                                "Insufficient funds!!",
                                "Warning Message",
                                JOptionPane.WARNING_MESSAGE);

                    } else {

                        JOptionPane.showMessageDialog(null,
                                "Transaction successfully!!");

                    }

                } catch (NumberFormatException numberFormatException) {

                    JOptionPane.showMessageDialog(null,
                            "Invalid data type!! Try again...",
                            "Warning Message",
                            JOptionPane.WARNING_MESSAGE);

                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(null,
                            "Something went wrong...",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);

                } catch (NullPointerException nullPointerException) {

                    JOptionPane.showMessageDialog(null,
                            "Transaction was cancelled!!");

                }
            }
        });

        btn_transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                TransferFrame transferFrame = new TransferFrame(transaction);

            }
        });
    }
}

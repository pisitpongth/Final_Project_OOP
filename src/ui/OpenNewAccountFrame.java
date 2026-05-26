package ui;

import logic.OpenNewAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class OpenNewAccountFrame {
    private JPanel openNewAccountFrame;
    private JTextField txt_account_id;
    private JPasswordField password_field;
    private JLabel lbl_create_account_id;
    private JLabel lbl_create_password;
    private JButton btn_back;
    private JLabel lbl_main_label;
    private JButton btn_confirm;
    private JLabel lbl_initial_deposit;
    private JTextField txt_initial_deposit;
    private JFrame frame;

    public OpenNewAccountFrame() {
        frame = new JFrame();
        frame.setContentPane(openNewAccountFrame);
        frame.setTitle("Banking Application");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

                MainFrame mainFrame = new MainFrame();

            }
        });

        btn_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String accountID = txt_account_id.getText();

                char[] rawPassword = password_field.getPassword();

                int passwordLength = rawPassword.length;

                String password = "";

                for (int i = 0; i < rawPassword.length; i++) {

                    password += rawPassword[i];

                }

                String rawInitialDeposit = txt_initial_deposit.getText();

                double initialDeposit = 0;

                if (accountID.length() != 10) {

                    txt_account_id.setText("");

                    JOptionPane.showMessageDialog(null,
                            "Account ID must be 10 character!!",
                            "Warning Message",
                            JOptionPane.WARNING_MESSAGE);

                } else if (passwordLength < 8) {

                    password_field.setText("");

                    JOptionPane.showMessageDialog(null,
                            "Password must have at least 8 character!!",
                            "Warning Message",
                            JOptionPane.WARNING_MESSAGE);

                } else {

                    try {

                        if (rawInitialDeposit.isEmpty()) {

                        } else {

                            initialDeposit = Double.parseDouble(rawInitialDeposit);

                        }

                        OpenNewAccount account = new OpenNewAccount(accountID, password, initialDeposit);

                        JOptionPane.showMessageDialog(null,
                                account.recordAccount());

                        txt_account_id.setText("");
                        password_field.setText("");
                        Arrays.fill(rawPassword, '0');
                        txt_initial_deposit.setText("");

                    } catch (NumberFormatException numberFormatException) {

                        txt_initial_deposit.setText("");

                        JOptionPane.showMessageDialog(null,
                                "Invalid data type!! Try again...",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);

                    } catch (IOException ex) {

                        JOptionPane.showMessageDialog(null,
                                "Something went wrong...",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });
    }
}

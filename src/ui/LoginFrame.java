package ui;

import logic.AccountTransaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class LoginFrame {
    private JPanel signInFrame;
    private JLabel lbl_main_label;
    private JTextField txt_account_id;
    private JPasswordField password_field;
    private JLabel lbl_enter_account_id;
    private JLabel lbl_enter_account_password;
    private JButton btn_sign_in;
    private JButton btn_back;
    private JFrame frame;

    public LoginFrame() {
        frame = new JFrame();
        frame.setContentPane(signInFrame);
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

        btn_sign_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String accountID = txt_account_id.getText();

                char[] rawPassword = password_field.getPassword();

                int passwordLength = rawPassword.length;

                String password = "";

                for (int i = 0; i < passwordLength; i++) {

                    password += rawPassword[i];

                }

                try {

                    AccountTransaction transaction = new AccountTransaction(accountID, password);

                    if (transaction.hasValidCredentials()) {

                        frame.dispose();

                        ServiceFrame serviceFrame = new ServiceFrame(transaction);

                    } else {

                        txt_account_id.setText("");

                        password_field.setText("");

                        JOptionPane.showMessageDialog(null,
                                "Failed to sign in!! Please check your ID/Password",
                                "Warning Message",
                                JOptionPane.WARNING_MESSAGE);

                    }

                } catch (FileNotFoundException ex) {

                    JOptionPane.showMessageDialog(null,
                            "Something went wrong...",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);

                }

            }
        });
    }
}

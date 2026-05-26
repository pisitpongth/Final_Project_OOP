package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private JPanel mainFrame;
    private JLabel lbl_main_label;
    private JButton btn_open_new_account;
    private JButton btn_sign_in;
    private JLabel lbl_instruction;
    private JFrame frame;

    public MainFrame() {
        frame = new JFrame();
        frame.setContentPane(mainFrame);
        frame.setTitle("Banking Application");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        btn_open_new_account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

                OpenNewAccountFrame openNewAccountFrame = new OpenNewAccountFrame();

            }
        });

        btn_sign_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

                LoginFrame signInFrame = new LoginFrame();

            }
        });
    }
}

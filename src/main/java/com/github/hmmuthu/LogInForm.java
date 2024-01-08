package com.github.hmmuthu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogInForm extends JDialog {
    private MarketPlace mp;

    private JPanel loginPanel;
    private JButton loginButton;
    private JButton signupButton;
    private JTextField email;
    private JPasswordField password;
    private User loginUser;
    public LogInForm(MarketPlace mp) {
        super((Frame) null);
        setupLayout();
        this.mp = mp;

        this.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });

        this.signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signupAction();
            }
        });
    }

    public void setMp(MarketPlace mp) {
        this.mp = mp;
    }

    public MarketPlace getMp() {
        return mp;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public static void initGUI(JDialog dialog, String title, JPanel panel, int width, int height) {
        dialog.setTitle(title);
        dialog.setModal(true); // setting the Modal to true makes the code wait and not run to the next line before action is performed
        dialog.setSize(new Dimension(width, height));
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setContentPane(panel);
    }

    /**
     * sets the loginUser based on username and password
     */
    private void loginAction() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(mp.getCustomers());
        allUsers.addAll(mp.getSellers());
        this.loginUser = User.login(this.email.getText(), String.valueOf(this.password.getPassword()), allUsers);

        if (this.loginUser != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this.loginPanel, "Email or Password is incorrect",
                    "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * sets the signupUser based on username and password
     */
    private void signupAction() {
        SignUpForm signUp = new SignUpForm(mp);
        signUp.setVisible(true);

        this.loginUser = signUp.getLoginUser();
        if (loginUser != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this.loginPanel, "SignUp Failed",
                    "SignUp Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupLayout(){}
}

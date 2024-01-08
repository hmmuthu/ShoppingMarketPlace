package com.github.hmmuthu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SignUpForm extends JDialog {

    private JPanel signUpPanel;
    private JButton signUp;
    private JTextField email;
    private JPasswordField password;
    private JTextField name;
    private JComboBox userRole;
    private User loginUser;
    private MarketPlace marketPlace;

    public SignUpForm(MarketPlace marketPlace) {
        super((JFrame) null);
        setUpLayout();
        userRole.addItem("Customer");
        userRole.addItem("Seller");
        this.marketPlace = marketPlace;

        this.signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpAction();
            }
        });
    }

    public User getLoginUser() {
        return loginUser;
    }

    private void setUpLayout() {

    }

    private void signUpAction() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(marketPlace.getCustomers());
        allUsers.addAll(marketPlace.getSellers());
        boolean isCustomer = false;
        if (userRole.getSelectedIndex() == 0) {
            isCustomer = true;
        }
        this.loginUser = User.signUp(this.email.getText(), String.valueOf(this.password.getPassword()), allUsers,
                isCustomer, this.name.getText());

        if (this.loginUser != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this.signUpPanel, "User already exists",
                    "SignUp Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

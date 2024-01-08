package com.github.hmmuthu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerForm extends JDialog {
    private MarketPlace marketPlace;
    private Customer customer;
    private JPanel customerPanel;
    private JTextField searchText;
    private JComboBox searchOptions;
    private JButton searchButton;
    private JButton clearSearchButton;
    private JTable productsTable;
    private JButton shoppingCartButton;
    private JButton sortNameButton;
    private JButton sortPriceButton;
    private JButton ordersButton;
    private JButton passwordChangeButton;
    private JButton signOutButton;
    public CustomerForm(MarketPlace marketPlace, Customer customer) {
        super((JFrame) null);
        this.marketPlace = marketPlace;
        this.customer = customer;
        setUpLayout();

        this.signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.passwordChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePasswordAction();
            }
        });

        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        });

        this.clearSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSearchAction();
            }
        });

        this.sortNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortNameAcion();
            }
        });

        this.sortPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortPriceAction();
            }
        });

        this.ordersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordersAction();
            }
        });
    }

    private void changePasswordAction() {
        String newPassword = JOptionPane.showInputDialog(null, "Enter new Password",
                "Change Password Card", JOptionPane.QUESTION_MESSAGE);

        if (newPassword != null) {
            customer.setPassword(newPassword);
        }
    }

    private void searchAction() {
        ArrayList<ShoppingItem> searchedItems;
        if (searchOptions.getSelectedIndex() == 0) {
            searchedItems = marketPlace.searchName(searchText.getText());
        } else if (searchOptions.getSelectedIndex() == 1) {
            searchedItems = marketPlace.searchDescription(searchText.getText());
        } else if (searchOptions.getSelectedIndex() == 2) {
            searchedItems = marketPlace.searchStore(searchText.getText());
        } else if (searchOptions.getSelectedIndex() == 3) {
            searchedItems = marketPlace.searchSeller(searchText.getText());
        } else {
            searchedItems = null;
        }

        if (searchedItems != null) {
            productsTable.setModel(new ProductTableModel(searchedItems));
        } else {
            clearSearchAction();
        }
    }

    private void clearSearchAction() {
        productsTable.setModel(new ProductTableModel(marketPlace.getMarketPlaceItems()));
    }

    private void sortNameAcion() {
        productsTable.setModel(new ProductTableModel(marketPlace.sortByName(marketPlace.getMarketPlaceItems())));
    }

    private void sortPriceAction() {
        productsTable.setModel(new ProductTableModel(marketPlace.sortByPrice(marketPlace.getMarketPlaceItems())));

    }

    private void ordersAction() {}

    private void setUpLayout() {

        searchOptions = new JComboBox<>();
        searchOptions.addItem("Name");
        searchOptions.addItem("Description");
        searchOptions.addItem("Store");
        searchOptions.addItem("Seller");



        productsTable = new JTable();
        productsTable.setModel(new ProductTableModel(marketPlace.getMarketPlaceItems()));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(productsTable);

        JPanel tablePanel = new JPanel();
        tablePanel.add(scrollPane);

        LogInForm.initGUI(this, "Customer View", customerPanel, 800, 600);

    }


}

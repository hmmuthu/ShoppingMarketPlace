package com.github.hmmuthu;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductTableModel extends AbstractTableModel {
    private static final String[] columnNames = {"Name", "Price", "Store"};
    private ArrayList<ShoppingItem> items = new ArrayList<>();

    public ProductTableModel(ArrayList<ShoppingItem> items) {
        this.items = items;
    }

    public ProductTableModel() {
        this.items = new ArrayList<>();
    }

    public ArrayList<ShoppingItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ShoppingItem> items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnName) {
        return columnNames[columnName];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return items.get(rowIndex).getProduct().getName();
        } else if (columnIndex == 1) {
            return "$" + items.get(rowIndex).getProduct().getPrice();
        } else if (columnIndex == 2) {
            return items.get(rowIndex).getStoreName();
        }

        return null;
    }
}

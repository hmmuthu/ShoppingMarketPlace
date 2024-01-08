package com.github.hmmuthu;
import java.io.FileWriter;
import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<ShoppingItem> cart = new ArrayList<>();
    private ArrayList<ShoppingItem> orders = new ArrayList<>();


    public Customer(String email, String password, String name) {
        super(email, password, name);
        setCustomer(true);
    }

    public Customer(String email) {
        super(email);
        setCustomer(true);
    }

    public ArrayList<ShoppingItem> getCart() {
        return cart;
    }

    public ArrayList<ShoppingItem> getOrders() {
        return orders;
    }

    public void setCart(ArrayList<ShoppingItem> cart) {
        this.cart = cart;
    }

    public void setOrders(ArrayList<ShoppingItem> orders) {
        this.orders = orders;
    }

    public double getTotal() {
        double totalPrice = 0;
        for (ShoppingItem i : cart) {
            totalPrice += i.getProduct().getPrice() * i.getProduct().getQuantity();
        }
        return totalPrice;
    }

    public double checkOut() {
        double totalPrice = getTotal();
        this.orders.addAll(this.cart);
        this.cart = new ArrayList<ShoppingItem>();
        return totalPrice;
    }

    /**
     * remove a shopping item from the cart. if successful return true. Else return false.
     * @param item
     * @return
     */
    public boolean removeFromCart(ShoppingItem item) {
        if (cart.contains(item)) {
            cart.remove(item);
            return true;
        }
        return false;
    }

    /**
     * export order history and return true if successful. Else return false.
     * @param filename
     * @return
     */
    public boolean exportOrders(String filename) {
        try{
            FileWriter f = new FileWriter(filename);
            for (ShoppingItem item : orders) {
                f.write(item.getProduct().getName() + " ; " +
                        item.getProduct().getPrice() + " ; " +
                        item.getProduct().getQuantity() + " ; " +
                        item.getStoreName() + "\n");
            }
            f.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * calls customer form
     * @param mp
     */
    public void actions(MarketPlace mp) {
        CustomerForm customerForm = new CustomerForm(mp, this);
        customerForm.setVisible(true);
    }
}

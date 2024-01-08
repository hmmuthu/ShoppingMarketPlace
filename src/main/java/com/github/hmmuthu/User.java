package com.github.hmmuthu;
import java.util.ArrayList;

public class User {
    private String email;
    private String password;
    private String name;
    private boolean customer;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            return ((User) o).getEmail().equalsIgnoreCase(getEmail());
        }
        return false;
    }

    /**
     * returns the user if username and password matches
     * return null if they do not match
     * @param email
     * @param password
     * @param allUsers
     * @return
     */
    public static User login(String email, String password, ArrayList<User> allUsers) {
        for (User u : allUsers) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                if (u.getPassword().equals(password)) {
                    return u;
                }
                return null;
            }
        }
        return null;
    }

    /**
     * if the user (email) does not exist create a new customer/seller
     * if the user exists return null
     * @param email
     * @param password
     * @param allUsers
     * @param isCustomer
     * @param name
     * @return
     */
    public static User signUp(String email, String password, ArrayList<User> allUsers, boolean isCustomer, String name) {
        for (User u : allUsers) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return null;
            }
        }

        if (isCustomer) {
            return new Customer(email, password, name);
        } else {
            return new Seller(email, password, name);
        }
    }

}

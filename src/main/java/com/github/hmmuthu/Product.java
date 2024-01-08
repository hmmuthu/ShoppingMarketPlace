package com.github.hmmuthu;
import java.util.UUID;

public class Product {
    private String name;
    private String description;
    private int quantity;
    private double price;
    private UUID id;


    public Product(String name, String description, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public UUID getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * checks if quantity is a valid number
     * @param quantity
     * @return
     */
    public static boolean validateQuantity(int quantity) {
        if(quantity >= 0) {
            return true;
        }
        return false;
    }

    /**
     * checks if price is a valid double
     * @param price
     * @return
     */
    public static boolean validatePrice(double price) {
        if(price > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Product) {
            Product givenProduct = (Product) o;
            if (getId() != null && givenProduct.getId() != null) {
                return givenProduct.getId().equals(getId());
            }
        }
        return false;
    }

    /**
     * clone a product and set the quantity given, and decrease the original quantity by the quantity given.
     * return null if quantity is too low
     * @param quantity
     * @return
     */
    public Product buy(int quantity) {
        if (quantity < getQuantity()) {
            Product cartProduct = new Product(getName(), getDescription(), quantity, getPrice());
            setQuantity(this.quantity - quantity);
            return cartProduct;
        } else {
            return null;
        }
    }

    /**
     * add the given quantity back to the original product
     * @param quantity
     */
    public void replace(int quantity) {
        setQuantity(this.quantity + quantity);
    }
}

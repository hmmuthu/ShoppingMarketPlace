package com.github.hmmuthu;
import java.util.ArrayList;
import java.util.UUID;

public class Store {
    private String name;
    private String sellerEmail;
    private UUID id;
    private ArrayList<Product> products = new ArrayList<>();

    public Store (String name, String sellerEmail) {
        this.name = name;
        this.sellerEmail = sellerEmail;
        this.id = UUID.randomUUID();
    }

    public Store (UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String store) {
        this.name = store;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct(Product product) {
        int index = products.indexOf(product);
        if (index == -1)
            return null;
        return products.get(index);
    }

    /**
     * return a product that matches the product name
     * @param productName
     * @return
     */
    public Product getProduct(String productName) {
        for (Product p : products) {
            if (p.getName().equals(productName)){
                return p;
            }
        }
        return null;
    }

    /**
     * if product does not exist in store, adds the product and returns true
     * Otherwise returns false
     * @param product
     * @return
     */
    public boolean addProduct(Product product) {
        if (products.contains(product)) {
            return false;
        }
        products.add(product);
        return true;
    }

    /**
     * if product is found delete and return true. Else return false
     * @param product
     * @return
     */
    public boolean deleteProduct(Product product) {
        Product p = getProduct(product);
        if (p != null) {
            products.remove(p);
            return true;
        }
        return false;
    }

    public boolean equals(Object o) {
        if (o instanceof Store) {
            Store givenStore = (Store) o;
            if (getId() != null && givenStore.getId() != null) {
                return givenStore.getId().equals(getId());
            }

            if (givenStore.getSellerEmail() != null && getSellerEmail() != null &&
                givenStore.getName() != null && getName() != null) {
                return givenStore.getName().equals(getName()) &&
                        givenStore.getSellerEmail().equalsIgnoreCase(getSellerEmail());
            }
        }
        return false;
    }
}

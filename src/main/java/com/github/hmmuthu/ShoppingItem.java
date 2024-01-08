package com.github.hmmuthu;
public class ShoppingItem {
    private Product product;
    private String sellerEmail;
    private String storeName;

    public ShoppingItem(Product product, String sellerEmail, String storeName) {
        this.storeName = storeName;
        this.sellerEmail = sellerEmail;
        this.product = product;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public Product getProduct() {
        return product;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean equals(Object o) {
        if (o instanceof ShoppingItem) {
            ShoppingItem givenItem = (ShoppingItem) o;
            if (product.getId() != null && givenItem.getProduct().getId() != null) {
                return givenItem.getProduct().getId().equals(product.getId());
            }
        }
        return false;
    }
}

package com.example.multiscreenswithlist;

public class Cart {
    private String username;
    private int productID;
    private int quantity;

    public Cart(String username, int productID, int quantity) {
        this.username = username;
        this.productID = productID;
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

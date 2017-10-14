package com.example.android.quickinventoryapp.BaseClasses;


public class HistoryInfo {
    private String customerName;
    private String productName;
    private String date;
    private String quantity;
    private String totalPrice;


    public HistoryInfo(String customerName, String productName, String date, String quantity, String totalPrice) {
        this.customerName = customerName;
        this.productName = productName;
        this.date = date;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}

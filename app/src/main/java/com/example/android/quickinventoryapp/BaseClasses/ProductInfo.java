package com.example.android.quickinventoryapp.BaseClasses;



public class ProductInfo {
    private String productName;
    private String productID;
    private String quatity;
    private String unit;
    private String price;
    private String alertMeWhen;

    public ProductInfo(String productName, String productID, String quatity, String unit, String price, String alertMeWhen) {
        this.productName = productName;
        this.productID = productID;
        this.quatity = quatity;
        this.unit = unit;
        this.price = price;
        this.alertMeWhen = alertMeWhen;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getQuatity() {
        return quatity;
    }

    public void setQuatity(String quatity) {
        this.quatity = quatity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAlertMeWhen() {
        return alertMeWhen;
    }

    public void setAlertMeWhen(String alertMeWhen) {
        this.alertMeWhen = alertMeWhen;
    }
}

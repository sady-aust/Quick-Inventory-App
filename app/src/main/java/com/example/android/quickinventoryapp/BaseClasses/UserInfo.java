package com.example.android.quickinventoryapp.BaseClasses;



public class UserInfo {
    private String userName;
    private String password;
    private String shopName;
    private String ShopOwneName;

    public UserInfo(String userName, String password, String shopName, String shopOwneName) {
        this.userName = userName;
        this.password = password;
        this.shopName = shopName;
        ShopOwneName = shopOwneName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopOwneName() {
        return ShopOwneName;
    }

    public void setShopOwneName(String shopOwneName) {
        ShopOwneName = shopOwneName;
    }
}

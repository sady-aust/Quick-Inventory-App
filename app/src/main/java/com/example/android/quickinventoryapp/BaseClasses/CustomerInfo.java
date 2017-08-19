package com.example.android.quickinventoryapp.BaseClasses;


public class CustomerInfo {
    private String cutomerName;
    private String companyName;
    private String phone;
    private String email;
    private String addressLine1;
    private String getAddressLine2;
    private String city;
    private String zipCode;

    public CustomerInfo(String cutomerName, String companyName, String phone, String email, String addressLine1, String getAddressLine2, String city, String zipCode) {
        this.cutomerName = cutomerName;
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.getAddressLine2 = getAddressLine2;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getGetAddressLine2() {
        return getAddressLine2;
    }

    public void setGetAddressLine2(String getAddressLine2) {
        this.getAddressLine2 = getAddressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

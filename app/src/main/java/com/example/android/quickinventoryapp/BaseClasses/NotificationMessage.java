package com.example.android.quickinventoryapp.BaseClasses;



public class NotificationMessage {
    private String Messgae;
    private String id;

    public NotificationMessage(String messgae, String id) {
        Messgae = messgae;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationMessage(String messgae) {
        Messgae = messgae;
    }

    public String getMessgae() {
        return Messgae;
    }

    public void setMessgae(String messgae) {
        Messgae = messgae;
    }
}

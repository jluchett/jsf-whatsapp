package com.adur.transkal.frw.whatsapp.beans;

public class WhatsappContact {
    private String id;
    private String name;
    private String phoneNumber;

    public WhatsappContact(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}

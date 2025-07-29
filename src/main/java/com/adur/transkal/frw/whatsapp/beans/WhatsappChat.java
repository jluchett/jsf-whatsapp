package com.adur.transkal.frw.whatsapp.beans;

import java.util.ArrayList;
import java.util.List;

public class WhatsappChat {
    private String id;
    private WhatsappContact contact;
    private List<WhatsappMessage> messages;

    public WhatsappChat(String id, WhatsappContact contact) {
        this.id = id;
        this.contact = contact;
        this.messages = new ArrayList<>();
    }

    public void addMessage(WhatsappMessage message) {
        this.messages.add(message);
    }

    public String getLastMessage() {
        if (messages.isEmpty()) {
            return "No hay mensajes";
        }
        return messages.get(messages.size() - 1).getText();
    }

    

    // Getters and Setters
    public String getId() {
        return id;
    }

    public WhatsappContact getContact() {
        return contact;
    }

    public List<WhatsappMessage> getMessages() {
        return messages;
    }
    

}

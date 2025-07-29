package com.adur.transkal.frw.whatsapp.beans;

public class WhatsappMessage {
    private String text;
    private String sender; // "user" o "contact"
    private String time;

    public WhatsappMessage(String text, String sender, String time) {
        this.text = text;
        this.sender = sender;
        this.time = time;
    }

    @Override
    public String toString() {
        return "WhatsappMessage{" +
                "text='" + text + '\'' +
                ", sender='" + sender + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public String getTime() {
        return time;
    }

}

package com.adur.transkal.frw.whatsapp.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.adur.transkal.frw.whatsapp.beans.WhatsappChat;
import com.adur.transkal.frw.whatsapp.beans.WhatsappContact;
import com.adur.transkal.frw.whatsapp.beans.WhatsappMessage;
import com.adur.transkal.frw.whatsapp.services.WhatsappApiService;

/**
 * This class is responsible for managing WhatsApp chat functionalities.
 * It includes methods to handle chat operations such as adding messages and retrieving the last message.
 */
@Named
@SessionScoped
public class WhatsappChatBacking implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<WhatsappChat> chats;
    private WhatsappChat selectedChat;
    
    @Inject
    private WhatsappApiService whatsappApiService;

    @PostConstruct
    public void init() {
        // Inicializar con datos de ejemplo
        chats = new ArrayList<>();

        WhatsappContact contact1 = new WhatsappContact("1", "Cliente 1", "1234567890");
        WhatsappContact contact2 = new WhatsappContact("2", "Cliente 2", "0987654321");

        WhatsappChat chat1 = new WhatsappChat("1", contact1);
        chat1.addMessage(new WhatsappMessage("Hola, ¿cómo estás?", "contact", "10:00 AM"));

        WhatsappChat chat2 = new WhatsappChat("2", contact2);
        chat2.addMessage(new WhatsappMessage("Buenos días", "contact", "9:30 AM"));

        chats.add(chat1);
        chats.add(chat2);
        selectedChat = chat1; // Seleccionar el primer chat por defecto
    }

    public void sendMessageText(String text){
        if (text != null && !text.trim().isEmpty()) {
            WhatsappMessage message = new WhatsappMessage(text, "user", getCurrentTime());
            selectedChat.addMessage(message);

            try {
                // Enviar el mensaje a través del servicio de API de WhatsApp
                whatsappApiService.sendTextMessage(selectedChat.getContact().getPhoneNumber(), text);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getCurrentTime() {
        return java.time.LocalTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("hh:mm a")
        );
    }

    // Getters and Setters
    public List<WhatsappChat> getChats() {
        return chats;
    }

    public WhatsappChat getSelectedChat() {
        return selectedChat;
    }

    public void setSelectedChat(WhatsappChat selectedChat) {
        this.selectedChat = selectedChat;
    }
  
}

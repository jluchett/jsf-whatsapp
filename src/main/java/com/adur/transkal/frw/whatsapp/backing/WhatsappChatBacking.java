package com.adur.transkal.frw.whatsapp.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private String messageText;
    private String searchTerm;
    
    @Inject
    private WhatsappApiService whatsappApiService;

    @PostConstruct
    public void init() {
        // Inicializar con datos de ejemplo
        chats = new ArrayList<>();

        WhatsappContact contact1 = new WhatsappContact("1", "Cliente 1", "1234567890");
        WhatsappContact contact2 = new WhatsappContact("2", "Cliente 2", "0987654321");

        WhatsappChat chat1 = new WhatsappChat("1", contact1);
        chat1.addMessage(new WhatsappMessage("Hola, ¿cómo estás?", "contact", getCurrentTime()));

        WhatsappChat chat2 = new WhatsappChat("2", contact2);
        chat2.addMessage(new WhatsappMessage("Buenos días", "contact", getCurrentTime()));

        chats.add(chat1);
        chats.add(chat2);
        selectedChat = chat1;
    }

     public void sendMessage() {
        if (messageText != null && !messageText.trim().isEmpty()) {
            WhatsappMessage message = new WhatsappMessage(messageText, "user", getCurrentTime());
            selectedChat.addMessage(message);
            
            try {
                whatsappApiService.sendTextMessage(selectedChat.getContact().getPhoneNumber(), messageText);
            } catch (Exception e) {
                e.printStackTrace();
                // Manejar error
            }
            
            messageText = ""; // Limpiar el campo de texto
        }
    }

    public void selectChat(String chatId) {
        selectedChat = chats.stream()
                          .filter(c -> c.getId().equals(chatId))
                          .findFirst()
                          .orElse(null);
    }
    
    public List<WhatsappChat> getFilteredChats() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            System.out.println("No search term provided, returning all chats.");
            return chats;
           
        }
        System.out.println("Filtering chats with search term: " + searchTerm);
        return chats.stream()
                  .filter(c -> c.getContact().getName().toLowerCase().contains(searchTerm.toLowerCase()) || 
                             c.getLastMessage().toLowerCase().contains(searchTerm.toLowerCase()))
                             .collect(Collectors.toList());
    }


    private String getCurrentTime() {
        return java.time.LocalTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("hh:mm a")
        );
    }

    // Getters and Setters
    public List<WhatsappChat> getChats() { return chats; }
    public WhatsappChat getSelectedChat() { return selectedChat; }
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    public String getSearchTerm() { return searchTerm; }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
  
}

package com.adur.transkal.frw.whatsapp.services;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/whatsapp")
public class WhatsappWebSocket {

  private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

  @OnOpen
  public void onOpen(Session session) {
      sessions.add(session);
  }

  @OnClose
  public void onClose(Session session) {
      sessions.remove(session);
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    // Aquí puedes agregar la lógica para manejar el mensaje entrante
    // Por ejemplo, podrías enviar el mensaje a un servicio de WhatsApp API o guardarlo en una base de datos.
    // Broadcast del mensaje a todas las sesiones
    synchronized (sessions) {
      for (Session s : sessions) {
        if (s.isOpen()) {
          try {
            s.getBasicRemote().sendText(message);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}

package com.adur.transkal.frw.whatsapp.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class WhatsappApiService {
  private static final String API_URL = "https://graph.facebook.com/v22.0/";
  private String accessToken = "TU_TOKEN_DE_ACCESO";
  private String phoneNumberId = "ID_DE_NUMERO_TELEFONO";


  /**
   * Envia un mensaje de texto a un destinatario específico a través de la API de WhatsApp.
   *
   * @param recipientId El ID del destinatario al que se enviará el mensaje.
   * @param message El contenido del mensaje de texto a enviar.
   * @return Una cadena que indica el resultado del envío del mensaje.
   * @throws IOException Si ocurre un error al procesar la solicitud.
   */
  public String sendTextMessage(String recipientId, String message) throws IOException {
    String url = API_URL + phoneNumberId + "/messages";
    
    Map<String, Object> messageMap = new HashMap<>();
    messageMap.put("messaging_product", "whatsapp");
    messageMap.put("to", recipientId);
    messageMap.put("type", "text");

    Map<String, String> textMap = new HashMap<>();
    textMap.put("body", message);
    messageMap.put("text", textMap);

    ObjectMapper mapper = new ObjectMapper();
    String requestBody = mapper.writeValueAsString(messageMap);

    // Aquí deberías realizar la llamada HTTP a la API de WhatsApp
    // utilizando la biblioteca de tu elección (por ejemplo, HttpClient)
    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);
    post.setHeader("Authorization", "Bearer " + accessToken);
    post.setHeader("Content-Type", "application/json");
    post.setEntity(new StringEntity(requestBody));

    HttpResponse response = client.execute(post);
    HttpEntity entity = response.getEntity();

    return EntityUtils.toString(entity);
    
  }
}

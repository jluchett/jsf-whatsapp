package com.adur.transkal.frw.whatsapp.services;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.adur.transkal.frw.whatsapp.util.ConfigUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/webhook")
public class WhatsappWebhook {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response verifyWebhook(
      @QueryParam("hub.mode") String mode,
      @QueryParam("hub.challenge") String challenge,
      @QueryParam("hub.verify_token") String verifyToken) {

    String expectedToken = ConfigUtil.getProperty("TOKEN_VERIFICATION");

    if (mode != null && verifyToken != null){
      if (mode.equals("subscribe") && verifyToken.equals(expectedToken)) {
        return Response.ok(challenge).build();
      }
    }
    
    return Response.ok("Webhook verified").build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response handleIncomingMessage(@Context HttpServletRequest request, String payload) {
      // Aquí puedes agregar la lógica para manejar el mensaje entrante
      // Por ejemplo, podrías enviar el mensaje a un servicio de WhatsApp API o guardarlo en una base de datos.

      System.out.println("Mensaje recibido: " + payload);
      return Response.ok().build();
  }
  
}

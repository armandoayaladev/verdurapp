/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


/**
 *
 * @author Armando
 */
public class NotificacionesEmail {
    
    private Configuraciones config;

    public NotificacionesEmail() {
        config = new Configuraciones();
    }
    
    
    
 public Boolean verificarConexioInternet(){
    Socket socket = null;
     try {
         socket = new Socket("www.google.com",80);
         if(socket.isConnected()){
             socket.close();
             return true;
         }
     } catch (Exception e) {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(NotificacionesEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
     }
     return false;
 }   
 public void SendMail(String mensaje) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        String datosUsuario = obtenerDatosUsuarioEmail();
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(datosUsuario.split(";")[0], datosUsuario.split(";")[1]);
                    }
                });
 
        try {
 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(datosUsuario.split(";")[0]));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(datosUsuario.split(";")[0]));
            message.setSubject("Consecutivo para Verdurapp");
            String correo = "<html>" +
                            "<strong style='background:rgb(0,204,51); color: rgb(255,255,102); font-family:Impact; font-size: 35px; font-style: italic; padding: 0px 12px 0px 5px;'>VerdurApp</strong>" +
                            "<br/>" +
                            "<p>" +
                            "Se ha ejecutado una factura. El consecutivo generado es: <strong style='color:red;'>{consecutivo}</strong>" +
                            "</p>" +
                            "</html>";
            message.setContent(correo.replace("{consecutivo}", mensaje),"text/html");
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String obtenerDatosUsuarioEmail() throws Exception{
        try {
            File archivoConfigEmail = new File(config.getUrlConfigEmail());
            FileReader fr = new FileReader(archivoConfigEmail);
            BufferedReader br = new BufferedReader(fr);
            String datos = br.readLine();
            br.close();
            fr.close();
            return datos;
        } catch (Exception e) {
            throw e;
        }
    }
}

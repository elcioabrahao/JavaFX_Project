/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stmodel;

/**
 *
 * @author PrasanthKumar
 */

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import org.apache.log4j.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Level;

public class SendMail {
final static Logger logger = Logger.getLogger(SendMail.class.getName());

    public static boolean composeEmail(String fromName, String from, String to, String subject, String body, String dispFileName, String fileName, String username, String password) {
      // Recipient's email ID needs to be mentioned.
//      String to = "xyz@gmail.com";//change accordingly

      // Sender's email ID needs to be mentioned
  //    String from = "abc@gmail.com";//change accordingly
   //   final String username = "abc";//change accordingly
   //   final String password = "*****";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from, fromName));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject(subject);

         // Now set the actual message
        // message.setText(body);
         
         // Create the message part
          BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText(body);

         // Create a multipar message
          Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         //String filename = "/home/manisha/file.txt";
          DataSource source = new FileDataSource(fileName);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(dispFileName);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         
         // Send message
         Transport.send(message);

         logger.info("Sent message successfully....");
         return true;

      } catch (MessagingException e) {
          System.out.println("a++++++++++++++++++++++++++++++++++"); 
          
          Logger.getLogger(SendMail.class.getName()).log(Level.FATAL, null, e);
           return false;
      } catch (UnsupportedEncodingException ex) {
          System.out.println("a=============================="); 
          Logger.getLogger(SendMail.class.getName()).log(Level.FATAL, null, ex);
           return false;
       }catch(Exception e){
        
            System.out.println("ajsdfgfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
            
            Logger.getLogger(SendMail.class.getName()).log(Level.FATAL, null, e);   
            return false;
       }
   }
}
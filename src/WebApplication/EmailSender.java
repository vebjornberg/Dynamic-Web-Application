package WebApplication;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.annotation.Generated;

public class EmailSender {
	
	public static boolean sendEmail(String recipient, String link) throws Exception {
		Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", true); 
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", "noreplylibrarydb@gmail.com"); //usr_name 
	    props.put("mail.smtp.password", "inderentilandy"); // pass 
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", true);



	    Session session = Session.getInstance(props,null);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        InternetAddress from = new InternetAddress("noreplylibrarydb@gmail.com");
	        message.setSubject("DBL - new user");
	        message.setFrom(from);
	        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

	        // Create a multi-part to combine the parts
	        Multipart multipart = new MimeMultipart("alternative");

	        // Create your text message part
	        BodyPart messageBodyPart = new MimeBodyPart();
	        
	        messageBodyPart.setText("Hi and welcome to DBL, \n Please click the below link to confirm your email and create your account\n\n" + link + "\nRegards, \nDBL tema :)");

	        // Add the text part to the multipart
	        multipart.addBodyPart(messageBodyPart);
	        
	        /*
	        // Create the html part
	        messageBodyPart = new MimeBodyPart();
	        String htmlMessage = "Our html text";
	        messageBodyPart.setContent(htmlMessage, "text/html");
	        // Add html part to multi part
	        multipart.addBodyPart(messageBodyPart);
			*/
	        // Associate multi-part with message
	        message.setContent(multipart);

	        // Send message
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", "noreplylibrarydb@gmail.com", "inderentilandy");
	     
	        transport.sendMessage(message, message.getAllRecipients());

	        return true;
	    } catch (AddressException e) {
	        e.printStackTrace();
	        throw e;
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        throw e;
	    }
	    
	}
}
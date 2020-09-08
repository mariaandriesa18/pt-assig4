package data;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class EmailSender {
	private String filename;
	public EmailSender(String filename) {
		
		this.filename = filename;
		sendEmail();
	}
    public void sendPlainTextEmail(String smtp_host, String smtp_port,
            final String smtp_username, final String smtp_password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {
    	    	
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtp_host);
        properties.put("mail.smtp.port", smtp_port);
        properties.put("mail.smtp.user", smtp_username); // User name
        properties.put("mail.smtp.password", smtp_password); // password
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.debug", "true");
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtp_username, smtp_password);
            }
        };
       
        Session session = Session.getInstance(properties, auth);
        Transport transport = session.getTransport("smtp");
        
		transport.connect(smtp_host, smtp_username, smtp_password);
     
        
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(smtp_username));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(message);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
        messageBodyPart = new  MimeBodyPart();
       // String filename = "bill.txt";
        
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);

        msg.setContent(multipart);
        Transport.send(msg, msg.getAllRecipients());
        
        transport.close();
    }
 

    public void sendEmail() {
        // SMTP server information
        String host = "smtp.gmail.com";
        String port = "465";
        String mailFrom = "mockmymail@gmail.com";
        String password = "carapacea1234";
 
        // outgoing message information
        String mailTo = "maria18and@gmail.com";
        String subject = "Order Bill";
        String message = "Bellow, you have attached the bill from the order you've just placed.\n We hope you'll enjoy the food!";
 
 
        try {
            sendPlainTextEmail(host, port, mailFrom, password, mailTo,
                    subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to send email.");
            ex.printStackTrace();
        }
    }
}
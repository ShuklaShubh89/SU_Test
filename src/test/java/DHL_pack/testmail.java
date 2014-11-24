package DHL_pack;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class testmail {

	public static void main(String[] args) {


		try{
		       // Recipient's email ID needs to be mentioned.
		       String to = "sarvesh.kalia@accenture.com";
		    

		       // Sender's email ID needs to be mentioned
		       String from = "shubham.a.shukla@accenture.com";

		      // Host address
		       String host = "smtp.office365.com";

		       // Get system properties
		       Properties properties = System.getProperties();

		       // Setup mail server
		       // Get the default Session object.
		       properties.setProperty("mail.transport.protocol", "smtp");
		       properties.setProperty("mail.smtp.auth", "false");
		       properties.setProperty("mail.smtp.user", "dir\\shubham.a.shukla");
		       properties.setProperty("mail.smtp.isSSL", "true");
		       properties.setProperty("mail.smtp.port", "587");
		       properties.setProperty("mail.smtp.host", host);
		       properties.setProperty("mail.debug", "true");
		       properties.setProperty("mail.smtp.starttls.enable", "true"); 
		       properties.setProperty("mail.smtp.EnableSSL.enable", "true"); 
		       properties.put("mail.smtp.socketFactory.fallback", "true");
		 
		       Session session = Session.getDefaultInstance(properties);

		      // Create a default MimeMessage object.
		       MimeMessage message = new MimeMessage(session);

		       // Set From: header field of the header.
		       message.setFrom(new InternetAddress(from));

		       // Set To: header field of the header.
		       message.addRecipient(Message.RecipientType.TO,
		                                   new InternetAddress(to));

		       // Set Subject: header field
		       message.setSubject("SAP HealthChecks");
		       BodyPart messageBodyPart = new MimeBodyPart();
		       Multipart multipart = new MimeMultipart();
		    // Now set the actual message
		       message.setText("Please find the SAP Health checks xlsx attached");
		       message.saveChanges();
		       // Set text message part
		       // multipart.addBodyPart(messageBodyPart);
		       messageBodyPart = new MimeBodyPart();
		       String filename = "C:\\Users\\shubham.a.shukla\\IBM\\rationalsdp\\workspace\\FI_HealthCheck\\Health_Checks_SAP.xlsx";
		   /*  ((MimeBodyPart) messageBodyPart).attachFile(new File(filename));
		       messageBodyPart.setHeader("Content-Type", "text/plain; charset=\"us-ascii\"; name=\"mail.txt");*/
		       DataSource source = new FileDataSource(filename);
		       messageBodyPart.setDataHandler(new DataHandler(source));
		       messageBodyPart.setFileName(filename);
		       multipart.addBodyPart(messageBodyPart);
		       message.setContent(multipart);
		       message.saveChanges();
		                    
		       
		       // Send message
		       Transport.send(message);
		       System.out.println("Sent message successfully....");
				}catch(Exception e){
				   e.printStackTrace();
				}

	}

}

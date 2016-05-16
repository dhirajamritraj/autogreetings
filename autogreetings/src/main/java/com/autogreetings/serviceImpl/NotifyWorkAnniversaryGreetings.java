package com.autogreetings.serviceImpl;

import java.io.File;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.autogreetings.authenticate.AuthenticateGreetingEmployee;

public class NotifyWorkAnniversaryGreetings extends NotifyCustomGreetings {
	@Override
	public void sendMail(String host, String port, final String userName, final String password, String toAddress)
			throws AddressException, MessagingException {

		Session session = AuthenticateGreetingEmployee.authenticateEmployeeForGreetings(host, port, userName, password);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject("Happy Work Anniversary !!!");
		msg.setSentDate(new Date());
		// set plain text message
		msg.setContent(this.prepareGreetingsBody(), "text/html");

		// sends the e-mail
		Transport.send(msg);

	}

	private MimeMultipart prepareGreetingsBody() throws MessagingException {
		// This mail has 2 part, the BODY and the embedded image
		MimeMultipart multipart = new MimeMultipart("related");

		// first part (the html)
		BodyPart messageBodyPart = new MimeBodyPart();
		String htmlText = "<H1>Happy 3rd fake work anniversary Pankaj</H1><img src=\"cid:image\">";
		messageBodyPart.setContent(htmlText, "text/html");
		// add it
		multipart.addBodyPart(messageBodyPart);

		// second part (the image)
		messageBodyPart = new MimeBodyPart();
		File imgaePath = new File(
				this.getClass().getClassLoader().getResource("com/autogreeting/birthday/pics/bday1.jpg").getPath());
		DataSource fds = new FileDataSource(imgaePath);

		messageBodyPart.setDataHandler(new DataHandler(fds));
		messageBodyPart.setHeader("Content-ID", "<image>");

		// add image to the multipart
		multipart.addBodyPart(messageBodyPart);
		return multipart;
	}

}

package com.autogreetings.serviceImpl;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.autogreetings.authenticate.AuthenticateGreetingEmployee;
import com.autogreetings.service.GreetingsNotifier;

public class NotifyCustomGreetings implements GreetingsNotifier {
	public void sendMail(String host, String port, final String userName, final String password, String toAddress)
			throws AddressException, MessagingException {

		Session session = AuthenticateGreetingEmployee.authenticateEmployeeForGreetings(host, port, userName, password);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject("dummy");
		msg.setSentDate(new Date());
		// set plain text message
		msg.setContent(this.prepareGreetingsBody(), "text/html");

		// sends the e-mail
		Transport.send(msg);

	}

	private MimeMultipart prepareGreetingsBody() throws MessagingException {
		return null;
	}
}

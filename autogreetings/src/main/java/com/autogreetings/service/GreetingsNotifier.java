package com.autogreetings.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface GreetingsNotifier {
	public void sendMail(String host, String port, final String userName, final String password)
			throws AddressException, MessagingException;
}

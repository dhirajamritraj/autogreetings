package com.autogreetings.serviceImpl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.autogreetings.service.GreetingsNotifier;

public class NotifyCustomGreetings implements GreetingsNotifier {
	

	@Override
	public void sendMail(String host, String port, String userName,
			String password) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		
	}
}

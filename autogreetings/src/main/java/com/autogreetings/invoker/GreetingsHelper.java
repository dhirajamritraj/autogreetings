package com.autogreetings.invoker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.autogreetings.service.GreetingsNotifier;
import com.autogreetings.serviceImpl.NotifyBirthDayGreetings;
import com.autogreetings.serviceImpl.NotifyWorkAnniversaryGreetings;

public class GreetingsHelper {

	public static void sendGreetings() {
		// SMTP server information
		String host = "smtp.office365.com";
		String port = "587";
		String mailFrom = "dhiraj.amritraj@nagarro.com";
		String password = "Windows@05";

		//GreetingsNotifier mailer = new NotifyBirthDayGreetings();
		GreetingsNotifier mailer = new NotifyWorkAnniversaryGreetings();
		
		try {
			mailer.sendMail(host, port, mailFrom, password);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			ex.printStackTrace();
		}
	}

}

package com.autogreetings.invoker;

import com.autogreetings.service.GreetingsNotifier;
import com.autogreetings.serviceImpl.NotifyBirthDayGreetings;

public class GreetingsHelper {

	public static void sendGreetings() {
		// SMTP server information
		String host = "smtp.office365.com";
		String port = "587";
		String mailFrom = "dhiraj.amritraj@nagarro.com";
		String password = "nagarro@04";

		// outgoing message information
		String mailTo = "pankaj.mishra@nagarro.com";
		GreetingsNotifier mailer = new NotifyBirthDayGreetings();
		try {
			mailer.sendMail(host, port, mailFrom, password, mailTo);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			ex.printStackTrace();
		}
	}

}

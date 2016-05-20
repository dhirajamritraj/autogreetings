package com.autogreetings.invoker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.autogreetings.service.GreetingsNotifier;
import com.autogreetings.serviceImpl.NotifyBirthDayGreetings;

public class GreetingsHelper {

	public static void sendGreetings() {
		// SMTP server information
		String host = "smtp.office365.com";
		String port = "587";
		String mailFrom = "dhiraj.amritraj@nagarro.com";
		String password = "";

		// outgoing message information
		Properties p = new Properties();
		InputStream input;
		String mailCC = null;
		try {
			input = new FileInputStream(GreetingsHelper.class.getClassLoader().getResource("com/autogreeting/birthday/bdayTemplate.properties").getPath());
			p.load(input);
			mailCC = p.getProperty("cc_mail_id").replaceAll("\"", "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GreetingsNotifier mailer = new NotifyBirthDayGreetings();
		try {
			mailer.sendMail(host, port, mailFrom, password, mailCC);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			ex.printStackTrace();
		}
	}

}

package com.autogreetings.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
import com.autogreetings.dao.GreetingsDao;
import com.autogreetings.daoImpl.GreetingsDaoImpl;
import com.autogreetings.model.Employee;

public class NotifyBirthDayGreetings extends NotifyCustomGreetings {

	GreetingsDao greetingsDao = new GreetingsDaoImpl();

	@Override
	public void sendMail(String host, String port, final String userName,
			final String password, String ccAddress) throws AddressException,
			MessagingException {

		Session session = AuthenticateGreetingEmployee
				.authenticateEmployeeForGreetings(host, port, userName,
						password);

		List<Employee> bdayEmployeeList = getEmployeeDetails();

		for (Employee employee : bdayEmployeeList) {
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(userName));
			InternetAddress[] ccAddresses = { new InternetAddress(ccAddress) };
			InternetAddress toAddress = new InternetAddress(
					employee.getEmailID());
			msg.setRecipient(Message.RecipientType.TO, toAddress);
			msg.setRecipients(Message.RecipientType.CC, ccAddresses);
			msg.setSubject("Happy BirtDay " + employee.getName() + "!!");
			msg.setSentDate(new Date());
			// set plain text message
			msg.setContent(prepareGreetingsBody(employee), "text/html");

			// sends the e-mail
			Transport.send(msg);
		}

	}

	private MimeMultipart prepareGreetingsBody(Employee employee) throws MessagingException {
		MimeMultipart multipart = new MimeMultipart("related");

		// first part (the html)
		BodyPart messageBodyPart = new MimeBodyPart();
		String htmlText = "<img src=\"cid:image\">"+prepareTextTemplate(employee);
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

	public List<Employee> getEmployeeDetails() {
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			employeeList = greetingsDao.getListOfGreetingsEmployee();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeeList;

	}

	private String prepareTextTemplate(Employee employee) {
		Properties p = new Properties();
		InputStream input;
		String templateText = null;
		try {
			input = new FileInputStream(this.getClass().getClassLoader().getResource("com/autogreeting/birthday/bdayTemplate.properties").getPath());
			p.load(input);
			templateText = MessageFormat.format(p.getProperty("template1"), employee.getName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return templateText;
	}

}

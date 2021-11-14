package com.demo.email;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public final class EmailProvider  {

	private final String SMTP_HOST = "smtp.gmail.com";
	private final String USER_NAME = "springapp845@gmail.com";
	private final String PASSWORD  = "/springapp845/";
	private final int PORT = 587;

	private static volatile EmailProvider emailProvider;
	private final JavaMailSender emailSender;

	private EmailProvider() {
		this.emailSender = configure();
	}

	private JavaMailSender configure() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(SMTP_HOST);
		mailSender.setUsername(USER_NAME);
		mailSender.setPassword(PASSWORD);
		mailSender.setPort(PORT);

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.starttls.enable", true);
		mailProperties.put("mail.smtp.ssl.trust", SMTP_HOST);

		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
	}

	public JavaMailSender getEmailSender() {
		return this.emailSender;
	}

	public static EmailProvider getInstance() {
		if (emailProvider == null) { 
			synchronized (EmailProvider.class) {
				if (emailProvider == null) {
					emailProvider = new EmailProvider();
				}
			}
		}
		return emailProvider;
	}
}
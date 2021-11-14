package com.demo.email;

import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

public class Email extends SimpleMailMessage {

	private static final long serialVersionUID = 3131274760105461942L;

	private EmailProvider provider;

	public Email(String sender, String recipient, String subject, String message) {
		provider = EmailProvider.getInstance();
		setFrom(sender);
		setTo(recipient);
		setSubject(subject);
		setText(message);
		setSentDate(new Date());
	}

	public void send() {
		provider.getEmailSender().send(this);
	}

}

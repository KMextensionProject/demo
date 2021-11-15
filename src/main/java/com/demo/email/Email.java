package com.demo.email;

import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

import com.demo.helpers.TypeMap;
import com.demo.pojos.DemoObject;

// TODO: implement this class as builder pattern due to complex construction?
public class Email extends SimpleMailMessage implements DemoObject {

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

	@Override
	public TypeMap toTypeMap() {
		return null;
	}

}

package com.github.kawakicchi.sample.javamail.provider;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class GmailProvider implements MailProvider {

	private String user;
	private String password;

	public GmailProvider(final String user, final String password) {
		this.user = user;
		this.password = password;
	}

	public Session getSession() {
		Properties property = new Properties();
		property.put("mail.smtp.host", "smtp.gmail.com");
		property.put("mail.smtp.auth", "true");
		property.put("mail.smtp.starttls.enable", "true");
		property.put("mail.smtp.host", "smtp.gmail.com");
		property.put("mail.smtp.port", "587");
		property.put("mail.smtp.debug", "true");

		Session session = Session.getInstance(property,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});
		return session;
	}
}

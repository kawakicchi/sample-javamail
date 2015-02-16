package com.github.kawakicchi.sample.javamail;

import java.io.File;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.github.kawakicchi.sample.javamail.provider.MailProvider;

public class SampleMail {

	private MailProvider provider;

	public SampleMail(final MailProvider provider) {
		this.provider = provider;
	}

	public void send(final String from, final String to) {

		try {
			InternetAddress[] addresses = InternetAddress.parse(to);

			MimeMessage msg = new MimeMessage(provider.getSession());
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, addresses);
			msg.setSubject("件名", "ISO-2022-JP");
			msg.setSentDate(new Date());

			// 本文
			MimeBodyPart body = new MimeBodyPart();
			body.setText("本文", "ISO-2022-JP");

			// 添付
			MimeBodyPart file = new MimeBodyPart();
			FileDataSource ds = new FileDataSource(new File("pom.xml"));
			file.setDataHandler(new DataHandler(ds));
			file.setFileName(MimeUtility.encodeWord(ds.getName()));

			// Multipart
			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(body);
			multiPart.addBodyPart(file);

			msg.setContent(multiPart);

			Transport.send(msg);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (java.io.UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
	}

}

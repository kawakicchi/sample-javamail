package com.github.kawakicchi.sample.javamail.provider;

import javax.mail.Session;

public interface MailProvider {

	public Session getSession();
}

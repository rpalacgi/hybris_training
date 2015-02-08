/**
 *
 */
package com.epam.training.services.impl;

import de.hybris.platform.core.Registry;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service("mailSender")
public class MailSenderFactory implements FactoryBean<JavaMailSender>
{
	private static final Logger LOG = Logger.getLogger(MailSenderFactory.class);

	private static final String PARAM_HOST = "mail.smtp.server";
	private static final String PARAM_PORT = "mail.smtp.port";
	private static final String PARAM_USER = "mail.smtp.user";
	private static final String PARAM_PWD = "mail.smtp.password";
	private static final String PARAM_START_TLS = "mail.smtp.starttls.enable";

	private final JavaMailSender mailSender;

	public MailSenderFactory()
	{
		final String host = Registry.getMasterTenant().getConfig().getParameter(PARAM_HOST);
		final String user = Registry.getMasterTenant().getConfig().getParameter(PARAM_USER);
		final String port = Registry.getMasterTenant().getConfig().getParameter(PARAM_PORT);
		final String pwd = Registry.getMasterTenant().getConfig().getParameter(PARAM_PWD);
		final String startTls = Registry.getMasterTenant().getConfig().getParameter(PARAM_START_TLS);

		final JavaMailSenderImpl sender = new JavaMailSenderImpl();
		if (host == null || host.isEmpty() || port == null || port.isEmpty())
		{
			LOG.error("Can not start mail sender, please configure properties " + PARAM_HOST + " and " + PARAM_PORT);
		}
		else
		{
			sender.setHost(host);
			sender.setPort(Integer.parseInt(port));
			if (user != null)
			{
				sender.setUsername(user);
			}
			if (pwd != null)
			{
				sender.setPassword(pwd);
			}
			if (startTls != null && Boolean.parseBoolean(startTls))
			{
				final Properties javaMailProperties = new Properties();
				javaMailProperties.setProperty(PARAM_START_TLS, "true");
				sender.setJavaMailProperties(javaMailProperties);
			}
		}
		mailSender = sender;
	}

	@Override
	public JavaMailSender getObject() throws Exception
	{
		return mailSender;
	}

	@Override
	public Class getObjectType()
	{
		return JavaMailSender.class;
	}

	@Override
	public boolean isSingleton()
	{
		return true;
	}
}

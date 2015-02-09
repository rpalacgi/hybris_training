package com.epam.training.services.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.epam.training.model.OrganizationModel;
import com.epam.training.services.MailService;


@Component(value = "mailService")
public class DefaultMailService implements MailService
{
	private static final String SUBJECT = "Your customers list";
	private static final String NO_CUSTOMERS_TEXT = "No customers :(";
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SessionService sessionService;

	@Override
	public void sendCustomersListMail(final List<CustomerModel> customersList, final OrganizationModel organizationModel)
	{
		final MailPreparator mailPreparator = new MailPreparator()
		{
			@Override
			public void prepare(final MimeMessageHelper mimeMessageHelper) throws MessagingException
			{
				final StringBuilder messageText = new StringBuilder();
				int i = 1;
				for (final CustomerModel customer : customersList)
				{
					messageText.append(i + ". ");
					messageText.append(customer.getName() + "\n");
					i++;
				}
				if (customersList.isEmpty())
				{
					messageText.append(NO_CUSTOMERS_TEXT);
				}

				mimeMessageHelper.setText(messageText.toString());
				mimeMessageHelper.setSubject(SUBJECT);
				mimeMessageHelper.setTo(organizationModel.getEmail());
			}
		};
		send(mailPreparator);
	}

	private void send(final MailPreparator mailPreparator)
	{
		sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public void executeWithoutResult()
			{
				final MimeMessagePreparator messagePreparator = new MimeMessagePreparator()
				{
					@Override
					public void prepare(final MimeMessage mimeMessage) throws Exception
					{
						final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
						mailPreparator.prepare(mimeMessageHelper);
					}
				};
				mailSender.send(messagePreparator);
			}
		});
	}

	protected interface MailPreparator
	{
		void prepare(MimeMessageHelper mimeMessageHelper) throws MessagingException;
	}

	public void setMailSender(final JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

}

package com.epam.training.jobs;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.training.model.OrganizationModel;
import com.epam.training.services.MailService;
import com.epam.training.services.OrganizationService;


public class SendCustomersListJob extends AbstractJobPerformable<CronJobModel>
{
	Logger logger = Logger.getLogger(SendCustomersListJob.class);

	private MailService mailService;
	private OrganizationService organizationService;

	@Override
	public PerformResult perform(final CronJobModel cronJobModel)
	{
		logger.debug("Job executing started");
		final List<OrganizationModel> organizations = organizationService.findAllOrganizations();
		for (final OrganizationModel organization : organizations)
		{
			sendMailToOrganization(organization);
		}
		logger.debug("Job executing finished");
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	private void sendMailToOrganization(final OrganizationModel organization)
	{
		final String email = organization.getEmail();
		if (email != null && !email.isEmpty())
		{
			logger.debug(String.format("Sending mail with customers list to [%s]", organization.getEmail()));
			mailService.sendCustomersListMail(new ArrayList<CustomerModel>(organization.getCustomers()), organization);
		}
		else
		{
			logger.debug(String.format("Organizarion with name [%s] has no saved email", organization.getName()));
		}
	}

	public void setMailService(final MailService mailService)
	{
		this.mailService = mailService;
	}

	public void setOrganizationService(final OrganizationService organizationService)
	{
		this.organizationService = organizationService;
	}
}

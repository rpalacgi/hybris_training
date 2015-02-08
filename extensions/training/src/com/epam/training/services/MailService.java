package com.epam.training.services;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

import com.epam.training.model.OrganizationModel;




public interface MailService
{
	void sendCustomersListMail(List<CustomerModel> customersList, OrganizationModel organizationModel);
}

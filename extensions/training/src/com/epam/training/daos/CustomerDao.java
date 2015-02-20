package com.epam.training.daos;

import de.hybris.platform.core.model.user.CustomerModel;


public interface CustomerDao
{
	CustomerModel findCustomerById(String name);
}

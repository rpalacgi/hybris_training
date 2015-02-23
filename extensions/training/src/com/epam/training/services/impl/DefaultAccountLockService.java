package com.epam.training.services.impl;


import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epam.training.daos.CustomerDao;
import com.epam.training.services.AccountLockService;


@Component(value = "accountLockService")
public class DefaultAccountLockService implements AccountLockService
{
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	ModelService modelService;

	private final Map<String, Boolean> accountLockStatusCache = new ConcurrentHashMap<String, Boolean>();

	@Value(value = "${attemptCountMax}")
	private Integer attemptCountMax;

	@Override
	public void handleFailedLogin(final String userName)
	{
		final CustomerModel customer = customerDao.findCustomerById(userName);
		boolean status = false;
		if (customer != null)
		{
			Integer attemptCount = customer.getAttemptCount();
			attemptCount = attemptCount == null ? 0 : attemptCount;
			if (attemptCount >= attemptCountMax)
			{
				status = true;
				customer.setStatus(status);
			}
			customer.setAttemptCount(attemptCount + 1);
			modelService.save(customer);
		}
		updateAccountLockStatusCache(userName, status);
	}


	@Override
	public void handleSuccessLogin(final String userName)
	{
		final CustomerModel customer = customerDao.findCustomerById(userName);
		final boolean status = false;
		if (customer != null)
		{
			customer.setStatus(status);
			customer.setAttemptCount(0);
			modelService.save(customer);
		}
		updateAccountLockStatusCache(userName, status);
	}

	@Override
	public boolean isAccountLocked(final String userName)
	{
		if (!accountLockStatusCache.containsKey(userName))
		{
			final CustomerModel customer = customerDao.findCustomerById(userName);
			final Boolean status = customer != null ? customer.getStatus() : false;
			updateAccountLockStatusCache(userName, status);
		}
		return accountLockStatusCache.get(userName);
	}

	private void updateAccountLockStatusCache(final String userName, final boolean status)
	{
		accountLockStatusCache.put(userName, status);
	}
}

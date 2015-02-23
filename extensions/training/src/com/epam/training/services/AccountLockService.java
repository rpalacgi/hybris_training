package com.epam.training.services;

public interface AccountLockService
{
	void handleFailedLogin(String userName);

	void handleSuccessLogin(String userName);

	boolean isAccountLocked(String userName);

}

package com.epam.training.storefront.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.epam.training.services.AccountLockService;


public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
	private static final String SPRING_SECURITY_ERROR_MESSAGE = "SPRING_SECURITY_ERROR_MESSAGE";
	private static final String SPRING_SECURITY_LAST_USERNAME = "SPRING_SECURITY_LAST_USERNAME";
	private static final String PARAMETER_USERNAME = "j_username";

	private BruteForceAttackCounter bruteForceAttackCounter;
	private AccountLockService accountLockService;

	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException exception) throws IOException, ServletException
	{
		final String userName = request.getParameter(PARAMETER_USERNAME);
		// Register brute attacks
		bruteForceAttackCounter.registerLoginFailure(request.getParameter(PARAMETER_USERNAME));

		// Store the j_username in the session
		request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME, request.getParameter(PARAMETER_USERNAME));
		request.getSession().setAttribute(SPRING_SECURITY_ERROR_MESSAGE, exception.getMessage());

		accountLockService.handleFailedLogin(userName);
		super.onAuthenticationFailure(request, response, exception);
	}



	protected BruteForceAttackCounter getBruteForceAttackCounter()
	{
		return bruteForceAttackCounter;
	}

	@Required
	public void setBruteForceAttackCounter(final BruteForceAttackCounter bruteForceAttackCounter)
	{
		this.bruteForceAttackCounter = bruteForceAttackCounter;
	}

	public AccountLockService getAccountLockService()
	{
		return accountLockService;
	}

	public void setAccountLockService(final AccountLockService accountLockService)
	{
		this.accountLockService = accountLockService;
	}
}

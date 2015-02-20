package com.epam.training.storefront.controllers.pages;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.epam.training.storefront.breadcrumb.Breadcrumb;
import com.epam.training.storefront.controllers.util.GlobalMessages;
import com.epam.training.storefront.forms.LoginForm;
import com.epam.training.storefront.forms.RegisterForm;


/**
 * Abstract base class for login page controllers
 */
public abstract class AbstractLoginPageController extends AbstractRegisterPageController
{
	protected static final String SPRING_SECURITY_LAST_USERNAME = "SPRING_SECURITY_LAST_USERNAME";
	protected static final String SPRING_SECURITY_ERROR_MESSAGE = "SPRING_SECURITY_ERROR_MESSAGE";


	protected String getDefaultLoginPage(final boolean loginError, final HttpSession session, final Model model)
			throws CMSItemNotFoundException
	{
		final LoginForm loginForm = new LoginForm();
		model.addAttribute(loginForm);
		model.addAttribute(new RegisterForm());

		final String username = retrieveUserName(session);
		final String errorMessage = retrieveErrorMessage(session);

		loginForm.setJ_username(username);
		storeCmsPageInModel(model, getCmsPage());
		setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
		model.addAttribute("metaRobots", "index,no-follow");


		final Breadcrumb loginBreadcrumbEntry = new Breadcrumb("#", getMessageSource().getMessage("header.link.login", null,
				getI18nService().getCurrentLocale()), null);
		model.addAttribute("breadcrumbs", Collections.singletonList(loginBreadcrumbEntry));

		if (loginError)
		{
			GlobalMessages.addErrorMessage(model, errorMessage);
		}

		return getView();
	}

	private String retrieveUserName(final HttpSession session)
	{
		final String username = (String) session.getAttribute(SPRING_SECURITY_LAST_USERNAME);
		if (username != null)
		{
			session.removeAttribute(SPRING_SECURITY_LAST_USERNAME);
		}
		return username;
	}

	private String retrieveErrorMessage(final HttpSession session)
	{
		final String errorMessage = (String) session.getAttribute(SPRING_SECURITY_ERROR_MESSAGE);
		if (errorMessage != null)
		{
			session.removeAttribute(SPRING_SECURITY_ERROR_MESSAGE);
		}
		return errorMessage;
	}
}

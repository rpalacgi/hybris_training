package com.epam.traininghmc.jalo;

import de.hybris.platform.core.Registry;
import de.hybris.platform.util.JspContext;

import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.traininghmc.constants.TraininghmcConstants;


public class TraininghmcManager extends GeneratedTraininghmcManager
{
	private static final Logger LOG = Logger.getLogger(TraininghmcManager.class.getName());

	public static TraininghmcManager getInstance()
	{
		return (TraininghmcManager) Registry.getCurrentTenant().getJaloConnection().getExtensionManager()
				.getExtension(TraininghmcConstants.EXTENSIONNAME);
	}

	public TraininghmcManager() // NOPMD
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("constructor of TraininghmcManager called.");
		}
	}

	@Override
	public void init()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("init() of TraininghmcManager called. " + getTenant().getTenantID());
		}
	}

	@Override
	public void destroy()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("destroy() of TraininghmcManager called, current tenant: " + getTenant().getTenantID());
		}
	}

	@Override
	public void createEssentialData(final Map<String, String> params, final JspContext jspc)
	{
		// implement here code creating essential data
	}

	@Override
	public void createProjectData(final Map<String, String> params, final JspContext jspc)
	{
		// implement here code creating project data
	}
}

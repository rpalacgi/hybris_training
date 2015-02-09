package com.epam.training.jalo;

import de.hybris.platform.core.Registry;
import de.hybris.platform.util.JspContext;

import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.training.constants.TrainingConstants;


public class TrainingManager extends GeneratedTrainingManager
{
	private static final Logger LOG = Logger.getLogger(TrainingManager.class.getName());

	/**
	 * Get the valid instance of this manager.
	 *
	 * @return the current instance of this manager
	 */
	public static TrainingManager getInstance()
	{
		return (TrainingManager) Registry.getCurrentTenant().getJaloConnection().getExtensionManager()
				.getExtension(TrainingConstants.EXTENSIONNAME);
	}

	public TrainingManager() // NOPMD
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("constructor of TrainingManager called.");
		}
	}

	@Override
	public void init()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("init() of TrainingManager called. " + getTenant().getTenantID());
		}
	}

	@Override
	public void destroy()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("destroy() of TrainingManager called, current tenant: " + getTenant().getTenantID());
		}
	}

	/**
	 *
	 * @param params
	 *           the parameters provided by user for creation of objects for the extension
	 * @param jspc
	 *           the jsp context; you can use it to write progress information to the jsp page during creation
	 */
	@Override
	public void createEssentialData(final Map<String, String> params, final JspContext jspc)
	{
		// implement here code creating essential data
	}

	/**
	 * Implement this method to create data that is used in your project. This method will be called during the system
	 * initialization.
	 *
	 * An example use is to import initial data like currencies or languages for your project from an csv file.
	 *
	 * @param params
	 *           the parameters provided by user for creation of objects for the extension
	 * @param jspc
	 *           the jsp context; you can use it to write progress information to the jsp page during creation
	 */
	@Override
	public void createProjectData(final Map<String, String> params, final JspContext jspc)
	{
		// implement here code creating project data
	}
}

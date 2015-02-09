package com.epam.training.hmc;

import de.hybris.platform.hmc.AbstractEditorMenuChip;
import de.hybris.platform.hmc.AbstractExplorerMenuTreeNodeChip;
import de.hybris.platform.hmc.EditorTabChip;
import de.hybris.platform.hmc.extension.HMCExtension;
import de.hybris.platform.hmc.extension.MenuEntrySlotEntry;
import de.hybris.platform.hmc.generic.ClipChip;
import de.hybris.platform.hmc.generic.ToolbarActionChip;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class TrainingHMCExtension extends HMCExtension
{
	private static final String RESOURCE_PATH = "com.epam.training.hmc.locales";

	@Override
	public List<EditorTabChip> getEditorTabChips(final DisplayState paramDisplayState,
			final AbstractEditorMenuChip paramAbstractEditorMenuChip)
	{
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<AbstractExplorerMenuTreeNodeChip> getTreeNodeChips(final DisplayState paramDisplayState, final Chip paramChip)
	{
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<MenuEntrySlotEntry> getMenuEntrySlotEntries(final DisplayState paramDisplayState, final Chip paramChip)
	{
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<ClipChip> getSectionChips(final DisplayState paramDisplayState, final ClipChip paramClipChip)
	{
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<ToolbarActionChip> getToolbarActionChips(final DisplayState paramDisplayState, final Chip paramChip)
	{
		return Collections.EMPTY_LIST;
	}

	@Override
	public ResourceBundle getLocalizeResourceBundle(final Locale paramLocale) throws MissingResourceException
	{
		return null;
	}

	@Override
	public String getResourcePath()
	{
		return RESOURCE_PATH;
	}

}

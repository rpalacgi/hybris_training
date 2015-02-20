package com.epam.training.widgets;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Label;

import com.epam.training.services.TrainingbackofficeService;
import com.hybris.cockpitng.util.DefaultWidgetController;

public class TrainingbackofficeController extends DefaultWidgetController
{
	private static final long serialVersionUID = 1L;
	private Label label;

	@WireVariable
	private TrainingbackofficeService trainingbackofficeService;

	@Override
	public void initialize(final Component comp)
	{
		super.initialize(comp);
		label.setValue(trainingbackofficeService.getHello() + " TrainingbackofficeController");
	}
}

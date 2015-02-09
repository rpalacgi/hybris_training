package com.epam.training.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.training.daos.OrganizationDao;
import com.epam.training.model.OrganizationModel;
import com.epam.training.services.OrganizationService;


@Component(value = "organizationService")
public class DefaultOrganizationService implements OrganizationService
{
	@Autowired
	private OrganizationDao organizationDao;

	@Override
	public List<OrganizationModel> findAllOrganizations()
	{
		return organizationDao.findAllOrganizations();
	}
}

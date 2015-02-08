package com.epam.training.daos;

import java.util.List;

import com.epam.training.model.OrganizationModel;


/**
 *
 */
public interface OrganizationDao
{
	OrganizationModel findOrganizationById(Integer id);

	List<OrganizationModel> findAllOrganizations();
}

package com.epam.training.services;

import java.util.List;

import com.epam.training.model.OrganizationModel;


public interface OrganizationService
{
	List<OrganizationModel> findAllOrganizations();
}

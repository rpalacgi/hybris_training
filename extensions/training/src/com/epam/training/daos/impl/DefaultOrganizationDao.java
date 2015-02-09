package com.epam.training.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.training.daos.OrganizationDao;
import com.epam.training.model.OrganizationModel;


@Component(value = "organizationDao")
public class DefaultOrganizationDao implements OrganizationDao
{
	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public OrganizationModel findOrganizationById(final Integer id)
	{
		final String queryString = String.format("SELECT {p:%s} FROM {%s as p} WHERE {p:%s}=?id", OrganizationModel.PK,
				OrganizationModel._TYPECODE, OrganizationModel.ID);
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(queryString);
		searchQuery.addQueryParameter("id", id);
		final List<OrganizationModel> result = flexibleSearchService.<OrganizationModel> search(searchQuery).getResult();
		return result.size() == 0 ? null : result.get(0);
	}

	@Override
	public List<OrganizationModel> findAllOrganizations()
	{
		final String queryString = String.format("SELECT {p:%s} FROM {%s as p}", OrganizationModel.PK, OrganizationModel._TYPECODE);
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(queryString);
		return flexibleSearchService.<OrganizationModel> search(searchQuery).getResult();
	}

}

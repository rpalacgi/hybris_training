package com.epam.training.daos.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.training.daos.CustomerDao;


@Component(value = "customerDao")
public class DefaultCustomerDao implements CustomerDao
{
	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public CustomerModel findCustomerById(final String id)
	{
		final String queryString = String.format("SELECT {p:%s} FROM {%s as p} WHERE {p:%s}=?id", CustomerModel.PK,
				CustomerModel._TYPECODE, CustomerModel.UID);
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(queryString);
		searchQuery.addQueryParameter("id", id);
		final List<CustomerModel> result = flexibleSearchService.<CustomerModel> search(searchQuery).getResult();
		return result.size() == 0 ? null : result.get(0);
	}

}

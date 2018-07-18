package com.devyy.service.impl;

import com.devyy.dao.CustomerDao;
import com.devyy.domain.Customer;
import com.devyy.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/* 
	 * Ìí¼Ó¿Í»§
	 */
	@Override
	public void add(Customer customer) {
		
		customerDao.add(customer);
		
	}

}

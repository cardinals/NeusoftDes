package com.devyy.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.devyy.dao.CustomerDao;
import com.devyy.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public void add(Customer customer) {
		
		this.getHibernateTemplate().save(customer);
		
	}

}

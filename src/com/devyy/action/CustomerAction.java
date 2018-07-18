package com.devyy.action;

import com.devyy.domain.Customer;
import com.devyy.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

private Customer customer = new Customer();
	
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Customer getModel() {
		// 返回自动封装的数据
		return customer;
	}
	
	// 添加客户
	public String add(){
		
		// 调用 service 进行添加操作
		customerService.add(customer);
		
		return NONE;
	}
	
	
	// 编辑客户
	
	
	// 展示所有客户

}

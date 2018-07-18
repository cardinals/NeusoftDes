package com.devyy.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.devyy.dao.UserDao;
import com.devyy.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	// ×¢Èë UserDao
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}

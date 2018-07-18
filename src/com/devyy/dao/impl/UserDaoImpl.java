package com.devyy.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.devyy.dao.UserDao;

@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {	
}

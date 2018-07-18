package com.devyy.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.devyy.dao.UserDao;
import com.devyy.domain.User;
import com.devyy.service.UserService;
import com.devyy.utils.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	// ע�� UserDao
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/*
	 * ͨ����¼��������֤
	 */
	@Override
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}	
	
	/**
	 * ע���û�
	 */
	@Override
	public void save(User user) {		
		// ���Ҫ��������ܣ�����Ҫ�Ȼ�ȡ��������֮���ٴ洢
		String pass = user.getUser_password();
		// ʹ�� md5 ���м���
		String passPlus = MD5Utils.md5(pass);
		// �Ѽ��ܺ���������¸�ֵ
		user.setUser_password(passPlus);
		
		// �����û��� Code ֵ
		user.setUser_code("admin");
		
		// �����û���״̬
		user.setUser_state("1");
		
		// ���� dao ִ�����ݿ⽻��
		userDao.save(user);
		
		/*
		 * DataIntegrityViolationException �����������쳣��Ҫȥ�����ݱ��Ƿ��� not null Լ��������У�����Ҫ��ֵ
		 */		
	}

	/* 
	 * �û���¼
	 */
	@Override
	public User login(User user) {
		
		// С���⣺����ע���ʱ�������Ǽ��ܹ��ģ�
		// �����ʱ��Ҫ���û��������붼�ó������ȶԵĻ���
		// ��Ҫ�Ƚ�������н������������ܽ��бȶ�
		String pass = user.getUser_password();
		
		// ���¼���һ�� == ����
		user.setUser_password(MD5Utils.md5(pass));
		
		// ���� Dao 
		return userDao.login(user);
	}
}

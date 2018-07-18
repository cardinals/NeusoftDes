package com.devyy.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.devyy.dao.UserDao;
import com.devyy.domain.User;
import com.devyy.service.UserService;
import com.devyy.utils.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	// 注入 UserDao
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/*
	 * 通过登录名进行验证
	 */
	@Override
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}	
	
	/**
	 * 注册用户
	 */
	@Override
	public void save(User user) {		
		// 如果要给密码加密，则需要先获取，加密了之后，再存储
		String pass = user.getUser_password();
		// 使用 md5 进行加密
		String passPlus = MD5Utils.md5(pass);
		// 把加密后的密码重新赋值
		user.setUser_password(passPlus);
		
		// 设置用户的 Code 值
		user.setUser_code("admin");
		
		// 设置用户的状态
		user.setUser_state("1");
		
		// 调用 dao 执行数据库交互
		userDao.save(user);
		
		/*
		 * DataIntegrityViolationException 数据完整性异常，要去看数据表是否有 not null 约束，如果有，必须要给值
		 */		
	}

	/* 
	 * 用户登录
	 */
	@Override
	public User login(User user) {
		
		// 小问题：我们注册的时候，密码是加密过的，
		// 如果此时需要把用户名和密码都拿出来做比对的话，
		// 需要先将密码进行解析出来，才能进行比对
		String pass = user.getUser_password();
		
		// 重新加密一次 == 解密
		user.setUser_password(MD5Utils.md5(pass));
		
		// 调用 Dao 
		return userDao.login(user);
	}
}

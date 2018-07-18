package com.devyy.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.devyy.domain.User;
import com.devyy.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * 注册功能
	 */
	public String regist() {
		// 接收请求参数
		userService.save(user);
		return LOGIN;
	}

	/*
	 * 登录功能
	 */
	public String login() {

		// 1. 调用 service
		User exsitUser = userService.login(user);

		// 2. 判断 exsitUser 是否为空
		if (exsitUser == null) {
			System.out.println("用户名或密码不正确，检查后再登录...");
			return LOGIN;
		} else {
			// 如果登录成功，则需要把用户的实例放在 session 域中
			// 其他页面也能共享当前用户数据
			ServletActionContext.getRequest().getSession().setAttribute("exsitUser", exsitUser);

			// 如果登录成功了之后，我们应该是去首页
			return "loginOK";
		}
	}

	/*
	 * 退出功能
	 */
	public String exit() {
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		return LOGIN;
	}

	/*
	 * 通过登录名，判断，登录名是否已经存在
	 */
	public String checkCode() {

		// 调用业务层，查询
		User u = userService.checkCode(user.getUser_code());

		// 获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");

		try {
			// 获取输出流
			PrintWriter writer = response.getWriter();

			// 进行判断
			if (u != null) {
				// 说明：登录名查询到用户了，说明登录已经存在了，不能注册
				writer.print("no");
			} else {
				// 说明，不存在登录名，可以注册
				writer.print("yes");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

}

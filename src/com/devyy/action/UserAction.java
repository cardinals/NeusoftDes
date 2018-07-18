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
	 * ע�Ṧ��
	 */
	public String regist() {
		// �����������
		userService.save(user);
		return LOGIN;
	}

	/*
	 * ��¼����
	 */
	public String login() {

		// 1. ���� service
		User exsitUser = userService.login(user);

		// 2. �ж� exsitUser �Ƿ�Ϊ��
		if (exsitUser == null) {
			System.out.println("�û��������벻��ȷ�������ٵ�¼...");
			return LOGIN;
		} else {
			// �����¼�ɹ�������Ҫ���û���ʵ������ session ����
			// ����ҳ��Ҳ�ܹ���ǰ�û�����
			ServletActionContext.getRequest().getSession().setAttribute("exsitUser", exsitUser);

			// �����¼�ɹ���֮������Ӧ����ȥ��ҳ
			return "loginOK";
		}
	}

	/*
	 * �˳�����
	 */
	public String exit() {
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		return LOGIN;
	}

	/*
	 * ͨ����¼�����жϣ���¼���Ƿ��Ѿ�����
	 */
	public String checkCode() {

		// ����ҵ��㣬��ѯ
		User u = userService.checkCode(user.getUser_code());

		// ��ȡresponse����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");

		try {
			// ��ȡ�����
			PrintWriter writer = response.getWriter();

			// �����ж�
			if (u != null) {
				// ˵������¼����ѯ���û��ˣ�˵����¼�Ѿ������ˣ�����ע��
				writer.print("no");
			} else {
				// ˵���������ڵ�¼��������ע��
				writer.print("yes");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

}

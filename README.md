# NeusoftDes —— 东软（Neusoft）实训项目（CRM）

**CRM** ( Customer Relationship Management ) **客户关系管理系统**

## 一、环境准备

- Eclipse Java EE IDE for Web Developers. 【 Version: Oxygen.1a Release (4.7.1a) 】
- [Apache Tomcat 9.0.10](http://mirrors.hust.edu.cn/apache/tomcat/tomcat-9/v9.0.10/bin/apache-tomcat-9.0.10-windows-x64.zip)
- MySQL 5.7.20 

## 二、项目环境搭建

	数据库名：neusoftdes
	用户名：root
	密码：root
	包名：com.devyy.*

> 添加注释： **“Ctrl+Shift+/”**    
> 消除注释： **“Ctrl+Shift+\”**    
> 格式化代码： **“Ctrl+Shift+F”**    
> Getters、Setters、toString(）： **“Alt+Shift+S”**   


1. 新建 **Dynamic Web Project”** 项目。
2. 添加 **jar** 包（48 个）。
3. 添加 **web.xml** 文档。
		
		<?xml version="1.0" encoding="UTF-8"?>
		<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns="http://xmlns.jcp.org/xml/ns/javaee"
			xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
			id="WebApp_ID" version="3.1">
			<display-name>NeusoftDes</display-name>
		
		
			<!-- 配置Spring框架整合WEB的监听器 -->
			<listener>
				<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
			</listener>
			<context-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>classpath:applicationContext.xml</param-value>
			</context-param>
		
		
			<!-- 解决延迟加载的问题 -->
			<filter>
				<filter-name>OpenSessionInViewFilter</filter-name>
				<filter-class>org.springframework.orm.hibernate5.support.OpenSessionInViewFilter</filter-class>
			</filter>
			<filter-mapping>
				<filter-name>OpenSessionInViewFilter</filter-name>
				<url-pattern>/*</url-pattern>
			</filter-mapping>
		
		
			<!-- 配置Struts2框架的核心的过滤器 -->
			<filter>
				<filter-name>struts2</filter-name>
				<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
			</filter>
			<filter-mapping>
				<filter-name>struts2</filter-name>
				<url-pattern>/*</url-pattern>
			</filter-mapping>
		
			<welcome-file-list>
				<welcome-file>login.jsp</welcome-file>
			</welcome-file-list>
		</web-app>		
	
	- 注释掉 **“解决延迟加载的问题”** 部分		

4. 项目名右键 新建 **“Source Folder”**。
	- 添加 **applicationContext.xml**

			<?xml version="1.0" encoding="UTF-8"?>
			<beans xmlns="http://www.springframework.org/schema/beans"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:context="http://www.springframework.org/schema/context"
				xmlns:aop="http://www.springframework.org/schema/aop"
				xmlns:tx="http://www.springframework.org/schema/tx"
				xsi:schemaLocation="http://www.springframework.org/schema/beans 
				http://www.springframework.org/schema/beans/spring-beans.xsd
				http://www.springframework.org/schema/context
				http://www.springframework.org/schema/context/spring-context.xsd
				http://www.springframework.org/schema/aop
				http://www.springframework.org/schema/aop/spring-aop.xsd
				http://www.springframework.org/schema/tx 
				http://www.springframework.org/schema/tx/spring-tx.xsd">
				
				<!-- 先配置C3P0的连接池 -->
				<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
					<property name="driverClass" value="com.mysql.jdbc.Driver"/>
					<property name="jdbcUrl" value="jdbc:mysql:///crm_28"/>
					<property name="user" value="root"/>
					<property name="password" value="root"/>
				</bean>
				
				<!-- LocalSessionFactoryBean加载配置文件 -->
				<bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
					<!-- 先加载连接池 -->
					<property name="dataSource" ref="dataSource"/>
					<!-- 加载方言，加载可选 -->
					<property name="hibernateProperties">
						<props>
							<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
							<prop key="hibernate.show_sql">true</prop>
							<prop key="hibernate.format_sql">true</prop>
							<prop key="hibernate.hbm2ddl.auto">update</prop>
						</props>
					</property>
					
					<!-- 引入映射的配置文件 -->
					<property name="mappingResources">
						<list>
							<value>com/itheima/domain/User.hbm.xml</value>
							<value>com/itheima/domain/Customer.hbm.xml</value>
							<value>com/itheima/domain/Dict.hbm.xml</value>
						</list>
					</property>
				</bean>
				
				<!-- 先配置平台事务管理器 -->
				<bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
					<property name="sessionFactory" ref="sessionFactory"/>
				</bean>
				
				<!-- 开启事务的注解 -->
				<tx:annotation-driven transaction-manager="transactionManager"/>
				
				<!-- 配置客户模块 -->
				<bean id="customerAction" class="com.itheima.web.action.CustomerAction" scope="prototype">
					<property name="customerService" ref="customerService"/>
				</bean>
				
				<bean id="customerService" class="com.itheima.service.CustomerServiceImpl">
					<property name="customerDao" ref="customerDao"/>
				</bean>
				
				<bean id="customerDao" class="com.itheima.dao.CustomerDaoImpl">
					<property name="sessionFactory" ref="sessionFactory"/>
				</bean>
				
				<!-- 配置用户的模块 -->
				<bean id="userAction" class="com.itheima.web.action.UserAction" scope="prototype">
					<property name="userService" ref="userService"/>
				</bean>
				
				<bean id="userService" class="com.itheima.service.UserServiceImpl">
					<property name="userDao" ref="userDao"/>
				</bean>
				
				<bean id="userDao" class="com.itheima.dao.UserDaoImpl">
					<property name="sessionFactory" ref="sessionFactory"/>
				</bean>
				
			</beans>

		- 修改 **c3p0** 的配置（新建数据库）
		- 注释 **“引入映射的配置文件”** 中的 <list> 部分
		- 注释 **“配置客户模块”** ，因为现在没有这些类，否则启动就会报错。			


	- 添加 **log4j.properties**

			### direct log messages to stdout ###
			log4j.appender.stdout=org.apache.log4j.ConsoleAppender
			log4j.appender.stdout.Target=System.err
			log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
			log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n
			
			### direct messages to file mylog.log ###
			log4j.appender.file=org.apache.log4j.FileAppender
			log4j.appender.file.File=c\:mylog.log
			log4j.appender.file.layout=org.apache.log4j.PatternLayout
			log4j.appender.file.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n
			
			### set log levels - for more verbose logging change 'info' to 'debug' ###
			
			log4j.rootLogger=info, stdout

	- 添加 **struts.xml**

			<?xml version="1.0" encoding="UTF-8" ?>
			<!DOCTYPE struts PUBLIC
				"-//Apache Software Foundation//DTD Struts Configuration 2.3//EN"
				"http://struts.apache.org/dtds/struts-2.3.dtd">
			<struts>
				
				<!-- 先配置包结构 -->
				<package name="neusoftdes" extends="struts-default" namespace="/">
					
					<!-- 配置全局的结果页面 -->
					<global-results>
						<result name="login" type="redirect">/login.jsp</result>
					</global-results>
					
					<!-- 配置客户的Action，如果Action由Spring框架来管理，class标签上只需要编写ID值就OK -->
					<action name="customer_*" class="customerAction" method="{1}">
						<result name="page">/jsp/customer/list.jsp</result>
					</action>
					
					<!-- 配置用户的模块 -->
					<action name="user_*" class="userAction" method="{1}">
						<result name="loginOK" type="redirect">/index.jsp</result>
					</action>
					
				</package>
			    
			</struts>

5. 导入项目的页面资料（ WebContent ）

## 三、用户模块

1. **MySQL** 中新建数据表 `sys_user`

		CREATE TABLE `sys_user` (
						`user_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
						`user_code` varchar(32) NOT NULL COMMENT '用户账号',
						`user_name` varchar(64) NOT NULL COMMENT '用户名称',
						`user_password` varchar(32) NOT NULL COMMENT '用户密码',
						`user_state` char(1) NOT NULL COMMENT '1:正常,0:暂停',
						PRIMARY KEY (`user_id`)
					) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

	插入一条数据：

		insert into `sys_user` values(1, "admin", "管理员", "admin", 1);


2. 新建 **domain** 包，然后添加 **User** 类（用户的模块）。

		package com.devyy.domain;
		
		import java.io.Serializable;
		
		public class User implements Serializable{
			
			// 主键
			private Long user_id;
			// 登录名称
			private String user_code;
			// 用户姓名
			private String user_name;
			// 密码（保存的时候，需要加密处理）
			private String user_password;
			// 用户的状态 1正常 0暂停
			private String user_state;
		
			// get 和 set 方法
			public Long getUser_id() {
				return user_id;
			}
		
			public void setUser_id(Long user_id) {
				this.user_id = user_id;
			}
		
			public String getUser_code() {
				return user_code;
			}
		
			public void setUser_code(String user_code) {
				this.user_code = user_code;
			}
		
			public String getUser_name() {
				return user_name;
			}
		
			public void setUser_name(String user_name) {
				this.user_name = user_name;
			}
		
			public String getUser_password() {
				return user_password;
			}
		
			public void setUser_password(String user_password) {
				this.user_password = user_password;
			}
		
			public String getUser_state() {
				return user_state;
			}
		
			public void setUser_state(String user_state) {
				this.user_state = user_state;
			}
		
			@Override
			public String toString() {
				return "User [user_id=" + user_id + ", user_code=" + user_code + ", user_name=" + user_name + ", user_password="
						+ user_password + ", user_state=" + user_state + ", getUser_id()=" + getUser_id() + ", getUser_code()="
						+ getUser_code() + ", getUser_name()=" + getUser_name() + ", getUser_password()=" + getUser_password()
						+ ", getUser_state()=" + getUser_state() + "]";
			}
		
		}

3. 编写 **User.hbm.xml** 映射文件。

		<?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE hibernate-mapping PUBLIC 
		    "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		    "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">			   
		<hibernate-mapping>
			<class name="com.devyy.domain.User" table="sys_user">
				<id name="user_id" column="user_id">
					<generator class="native"/>
				</id>				
				<property name="user_code" column="user_code"/>
				<property name="user_name" column="user_name"/>
				<property name="user_password" column="user_password"/>
				<property name="user_state" column="user_state"/>
			</class>			
		</hibernate-mapping>

4. 编辑 **applicationContext.xml** 文件。打开刚才注释掉的 **“引入映射的配置文件”** 。然后修改如下：

		<!-- 引入映射的配置文件 -->
		<property name="mappingResources">
			<list>
				<value>com/devyy/domain/User.hbm.xml</value>
			</list>
		</property>

5. 接着新建 **action** 包，并添加 **UserAction** 类。
			
		package com.devyy.action;
		
		import com.devyy.domain.User;
		import com.opensymphony.xwork2.ActionSupport;
		import com.opensymphony.xwork2.ModelDriven;
		
		public class UserAction extends ActionSupport implements ModelDriven<User> {
			private User user = new User();
		
			@Override
			public User getModel() {
				return user;
			}
		}

6. 继续编辑 **applicationContext.xml** 文件。添加 **“配置用户的模块”** 。


		<!-- 配置用户的模块 -->
		<bean id="userAction" class="com.devyy.action.UserAction" scope="prototype"></bean>
			
7. 编辑 **struts.xml** 文件。添加 **“配置用户的模块”** 。

		<!-- 先配置包结构 -->
		<package name="crm" extends="struts-default" namespace="/">
			<!-- 配置用户的模块 -->
			<action name="user_*" class="userAction" method="{1}"></action>
		</package>

8. 新建 **service** 包，并添加 **UserService**（用户的业务层）。


	- 首先添加一个 **UserService** 接口类。

			package com.devyy.service;

			public interface UserService {
			}


	- 再添加一个 **UserServiceImpl** 实现类。
写业务层的时候，首先要想到添加 **@Transaction** 事务的注解。

			/** 用户的业务层 */
			@Transactional
			public class UserServiceImpl implements UserService {
			}
	
9. 继续编辑 **applicationContext.xml** 文件。在 **“配置用户的模块中”** 添加 **userService** 配置。

		<bean id="userService" class="com.devyy.service.impl.UserServiceImpl"></bean> 

10. 编辑 **UserAction** 类。现在 action 有了，service 也有了，接着就完成依赖注入。

		private UserService userService;
		public void setUserService(UserService userService) {
			this.userService = userService;
		}

11. 编辑 **applicationContext.xml** 文件。给 userAction 的 bean 中完成注入。

		<bean id="userAction" class="com.devyy.action.UserAction" scope="prototype">
			<property name="userService" ref="userService"/>
		</bean>

12. 新建 **dao** 包，并添加 **UserDao**。
	- 首先添加一个 **UserDao** 接口类。
				
			package com.devyy.dao;
			
			public interface UserDao {			
			}

	- 再添加一个 **UserDaoImpl** 实现类。
				
			package com.devyy.dao.impl;
			
			import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
			import org.springframework.transaction.annotation.Transactional;
			
			import com.devyy.dao.UserDao;
			
			@Transactional
			public class UserDaoImpl extends HibernateDaoSupport implements UserDao {	
			}


13. 编辑 **applicationContext.xml** 文件。在 **“配置用户的模块中”** 添加 userDao 配置。

		<bean id="userDao" class="com.devyy.dao.impl.UserDaoImpl"> </bean>

14. 编辑 **UserServiceImpl** 类。完成依赖注入。

		private UserDao userDao;
		public void setUserDao(UserDao userDao) {
			this.userDao = userDao;
		}
		
15. 编辑 **applicationContext.xml** 文件。
	- 接着在 service 中注入 dao。
	
			<bean id="userService" class="com.devyy.service.impl.UserServiceImpl">
				<property name="userDao" ref="userDao"/>
			</bean>

	- dao 要完成 **“增删改查”**，就需要模版注入。

			<bean id="userDao" class="dao.UserDaoImpl">
				<property name="sessionFactory" ref="sessionFactory"/>
			</bean>

----

## 四、用户模块-注册功能（登录名校验）

1. 编辑 **register.jsp** 页面。

		<!-- 引入 jQuery 库 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>


		<form action="${pageContext.request.contextPath}/user_regist.action"
			method="post" onsubmit="return checkForm()">
	
			<div class="main">
				<div class="mainin">
					<div class="mainin1">
						<ul>
							<li><span>用户名：</span> <input name="user_name" type="text"
								id="user_name" onblur="checkCode()" class="SearchKeyword" /> <span
								id="userNameSpan" style="FONT-WEIGHT: bold; COLOR: red"></span></li>
							<li><span>密码：</span> <input type="password"
								name="user_password" id="user_password" class="SearchKeyword2" />
								<span id="passwordSpan" style="FONT-WEIGHT: bold; COLOR: red"></span>
							</li>
							<li><button class="tijiao" type="submit">马上注册</button></li>
						</ul>
					</div>
					<div class="footpage">
						<span style="" font-family:arial;""="">Copyright ?</span>2018 <a
							href="http://www.baidu.com/" target="_blank">客户信息管理平台</a> － 也许很好用
					</div>
				</div>
			</div>
		</form>

2. 编辑 **UserAction** 类，添加 **checkCode()** 方法。

	因为我们是模型注入方式，所以我们不需要再接收数据了，因为模型已经自动帮我们封装好数据了，直接调用 service 层来操作即可。

		/** 通过登录名，判断，登录名是否已经存在 */
		public String checkCode(){

			// 调用业务层，查询
			User u = userService.checkCode(user.getUser_code());

			// 获取response对象
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");

			try {
				// 获取输出流
				PrintWriter writer = response.getWriter();

				// 进行判断
				if(u != null){
					// 说明：登录名查询到用户了，说明登录已经存在了，不能注册
					writer.print("no");
				}else{
					// 说明，不存在登录名，可以注册
					writer.print("yes");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
			return NONE;
		}

3. 编辑 **UserServiceImpl** 类，添加 **checkCode()** 方法。

		/*
		 * 通过登录名进行验证
		 */
		@Override
		public User checkCode(String user_code) {
			return userDao.checkCode(user_code);
		}	

4. 编辑 **UserDaoImpl** 类，添加 **checkCode()** 方法。

		/*
		 * 通过登录名进行验证
		 */
		@Override
		public User checkCode(String user_code) {
			List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ?", user_code);
	
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
			return null;
		}

5. 阻止表单提交。

	给表单添加 **onsubmit** 事件。

		// 可以阻止表单的提交
		function checkForm(){

			// 先让校验名称的方法先执行以下
			checkCode();	
	
			// 获取 error 的数量，如果数量 > 0，说明存在错误，不能提交表单
			if($(".error").size() > 0){
				return false;
			}
		}

6. **MD5Utils** 工具类

		package com.devyy.utils;
		
		import java.math.BigInteger;
		import java.security.MessageDigest;
		import java.security.NoSuchAlgorithmException;
		
		public class MD5Utils {
			/**
			 * 使用 md5 的算法进行加密
			 */
			public static String md5(String plainText) {
				
				byte[] secretBytes = null;
				
				try {
					secretBytes = MessageDigest.getInstance("md5").digest(
							plainText.getBytes());
					
				} catch (NoSuchAlgorithmException e) {
					throw new RuntimeException("没有md5这个算法！");
				}
				
				String md5code = new BigInteger(1, secretBytes).toString(16);	// 16进制数字
				
				// 如果生成数字未满 32 位，需要前面补 0
				for (int i = 0; i < 32 - md5code.length(); i++) {
					md5code = "0" + md5code;
				}
				
				return md5code;
			}
		}

## 五、用户模块-注册功能（功能实现）

1. 编辑 **UserAction** 类的 **regist()** 方法。

		/*
		 * 注册功能
		 */
		public String regist() {
			// 接收请求参数
			userService.save(user);
			return LOGIN;
		}

2. 编辑 **UserServiceImpl** 类的 **save()** 方法。

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

3. 编辑 **UserDaoImpl** 类的 **save()** 方法。

		/*
		 * 保存用户 
		 */
		@Override
		public void save(User user) {
			this.getHibernateTemplate().save(user);
		}

4. 编辑 **struts.xml** 文件。

		<!-- 配置全局的结果页面 -->
		<global-results>
			<result name="login" type="redirect">/login.jsp</result>
		</global-results>

5. **web.xml** 文件中配置异常处理

		<welcome-file-list>
			<welcome-file>login.jsp</welcome-file>
		</welcome-file-list>
	
		<error-page>
			<error-code>500</error-code>
			<location>/jsp/error.jsp</location>
		</error-page>

## 六、用户模块-登录和退出功能

### 【登录实现】

1. 编辑 **login.jsp** 登录页面。

	修改控件 name 属性值。
		
	登录名 → user_code；

	密码 → user_password。
		
	提交按钮，删掉它的 onclick 属性。
		
	修改表单的 action 属性值，

		action="${ pageContext.request.contextPath }/user_login.action"

2. 编辑 **UserAction** 类，添加 **login()** 方法。


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

3. **struts.xml** 文件	

		<!-- 配置用户的模块 -->
		<action name="user_*" class="userAction" method="{1}">
			<result name="loginOK" type="redirect">/index.jsp</result>
		</action>

4. 编辑 **UserServiceImpl** 类，添加 **lgoin() **方法。

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

5. 编辑 **UserDaoImpl** 类，添加 **login()** 方法。

		/*
		 * 登录功能，通过用户名和密码和用户的状态
		 */
		@Override
		public User login(User user) {
	
			// QBC的查询，按条件进行查询
			DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
			
			// 拼接查询的条件
			// criteria.add(Restrictions.eq("user_code", user.getUser_code()));
			criteria.add(Restrictions.eq("user_password", user.getUser_password()));
			criteria.add(Restrictions.eq("user_state", "1"));
			
			// 查询
			List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
			
			if(list != null && list.size() > 0){
				return list.get(0);
			}
			return null;
		}

### 【退出实现】

1. 编辑 **top.jsp** 页面。

	当前用户：${ existUser.user_name }

		<a href="${ pageContext.request.contextPath }/user_exit.action" target=_top>
			<font color=red>安全退出</font>
		</a>
	
2. 编辑 **UserAction** 类，添加 **exit()** 方法。

		/*
		 * 退出功能
		 */
		public String exit() {
			ServletActionContext.getRequest().getSession().removeAttribute("existUser");
			return LOGIN;
		}

---































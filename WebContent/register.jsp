<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>客户信息管理平台-注册</title>
<link rel="stylesheet" type="text/css" href="css/style2.css" />

<!-- 引入 jQuery 库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("input[name=user_name]").focus();
	});
	
	// 验证是否为空
	function checkCode() {

		// 1. 获取到对应的控件
		// 根据控件的 id 值获取对应的控件
		// 因为 js 是一个弱类型语言，接收变量的时候，都使用 var 关键字
		// 弱类型语言：没有编译的语言
		var userName = $("#user_name").val();

		// 2. 判断是否有值
		// trim() 去除前后空格
		if (userName.trim() == "") {
			// 进行提示
			$("#userNameSpan").html("用户名不能为空");
		} else {

			// 如果不为空，则进行注册验证，看数据库中是否已经有此用户了
			// 可以采用异步请求，把用户名发送到服务端进行校验
			var url = "${pageContext.request.contextPath}/user_checkCode.action";
			var param = {
				"user_name" : userName
			};

			// data 是服务端返回的数据
			$.post(url, param, function(data) {
				// 操作 data：yes 和 no
				if (data && data == "no") {
					$("#userNameSpan").html("用户名已经存在了");
				} else {
					$("#userNameSpan").html("用户名可用");
				}
			});
		}
	}

	// 可以阻止表单的提交
	function checkForm() {

		// 先让校验名称的方法先执行以下
		checkCode();

		// 获取 error 的数量，如果数量 > 0，说明存在错误，不能提交表单
		if ($(".error").size() > 0) {
			return false;
		}
	}
</script>
</head>
<body>

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
	<%-- <form action="${pageContext.request.contextPath}/user_register.action"
		method="post">
		<div class="main">
			<div class="mainin">
				<div class="mainin1">
					<ul>
						<li><span>用户名：</span> onblur 主要是绑定光标的失焦事件 
								onfocus 主要是绑定光标的聚焦事件
							 <input name="userName" type="text" id="user_name"
							onblur="checkCode()" placeholder="用户名" /> <span
							id="userNameSpan" style="FONT-WEIGHT: bold; COLOR: red"></span></li>
						<li><span>昵称：</span> <input name="smallName" type="text"
							id="user_code" placeholder="昵称" /> <span id="smallNameSpan"
							style="FONT-WEIGHT: bold; COLOR: red"></span></li>
						<li><span>密码：</span> <input type="password" name="password"
							id="user_password" placeholder="密码" /> <span id="passwordSpan"
							style="FONT-WEIGHT: bold; COLOR: red"></span></li>
						<li><button class="tijiao" type="submit">马上注册</button></li>
					</ul>
				</div>
				<div class="footpage">
					<span style="" font-family:arial;""="">Copyright ?</span>2018 <a
						href="http://www.baidu.com/" target="_blank">客户信息管理平台</a> － 也许很好用
				</div>
			</div>
		</div>
	</form> --%>
	<!-- <div class="main">
		<div class="mainin">
			<div class="mainin1">
				<ul>
					<li><span>用户名：</span><input name="loginName" type="text"
						id="loginName" placeholder="登录名"></li>
					<li><span>密码：</span><input type="password" name="Possword"
						id="Possword" placeholder="密码" ></li>
					<li><button class="tijiao">马上注册</button></li>
				</ul>
			</div>
			<div class="footpage">
				<span style="" font-family:arial;""="">Copyright ?</span>2018 <a
					href="http://www.baidu.com/" target="_blank">客户信息管理平台</a> － 也许很好用
			</div>
		</div>
	</div> -->
	<img src="images/loading.gif" id="loading"
		style="display: none; position: absolute;" />
	<div id="POPLoading" class="cssPOPLoading">
		<div style="height: 110px; border-bottom: 1px solid #9a9a9a">
			<div class="showMessge"></div>
		</div>
		<div style="line-height: 40px; font-size: 14px; letter-spacing: 1px;">
			<a onclick="puc()">确定</a>
		</div>
	</div>
	<div style="text-align: center;">
		<p>
			更多课程：<a href="http://jss.ke.qq.com/" target="_blank">Jss</a>
		</p>
	</div>
</body>
</html>
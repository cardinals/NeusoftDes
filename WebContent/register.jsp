<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <title>客户信息管理平台-注册</title>
	<link rel="stylesheet" type="text/css" href="css/style2.css" />   
</head>
<body>
    <div class="main">
    	<div class="mainin">
            <div class="mainin1">
            	<ul>
                	<li><span>用户名：</span><input name="loginName" type="text" id="loginName" placeholder="登录名" class="SearchKeyword"></li>
                    <li><span>密码：</span><input type="password" name="Possword" id="Possword" placeholder="密码"/ class="SearchKeyword2"></li>
                    <li><button class="tijiao">马上注册</button></li>
                </ul>
            </div>
            <div class="footpage"><span style="" font-family:arial;""="">Copyright ?</span>2018 <a href="http://www.baidu.com/" target="_blank">客户信息管理平台</a> － 也许很好用</div>
        </div>
    </div>
<img src="images/loading.gif" id="loading" style=" display:none;position:absolute;" />
<div id="POPLoading" class="cssPOPLoading">
    <div style=" height:110px; border-bottom:1px solid #9a9a9a">
        <div class="showMessge"></div>
    </div>
    <div style=" line-height:40px; font-size:14px; letter-spacing:1px;">
        <a onclick="puc()">确定</a>
    </div>
</div>
<div style="text-align:center;">
<p>更多课程：<a href="http://jss.ke.qq.com/" target="_blank">Jss</a></p>
</div>
</body>
</html>
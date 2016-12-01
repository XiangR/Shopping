<%@page import="com.xiangR.shopping.exception.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.xiangR.shopping.*"%> 
<%@ page import="com.xiangR.shopping.user.*"%>
<%@ page import="com.xiangR.shopping.client.*, com.xiangR.shopping.product.*" %>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	try {
		User u = UserManager.validate(username, password);
		session.setAttribute("user", u);
	} catch(UserNotFoundException e) {
		out.println("User not found！");
		return;
	} catch(PasswordNotCorrectException e) {
		out.println("Password not Correct！ ");
		return;
	}

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>自服务系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<center>
  		欢迎您： <%=username %>
  		<br>
  		<a href="UserModify.jsp">修改我的信息</a>
  		<br>
  		<a href="ChangePassword.jsp">修改我的密码</a>
  		<br>
  		<a href="Order.jsp">浏览以往订单</a>
  	</center>
  </body>
</html>

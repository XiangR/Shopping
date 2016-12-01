<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xiangR.shopping.user.*"%>
<%@ page import="com.xiangR.shopping.client.*"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%
	User u = (User)session.getAttribute("user");
	if(u == null) {
		out.println("您现在没有登录，如果您确认购买，只能按照市场价。");
		out.println("<a href=ConfirmNormal.jsp>继续购买</a>");
		out.println("<a href=UserLogin.jsp>登录</a>");
		u = new User();
	//	response.sendRedirect("UserLogin.jsp");
	//	return;
	} else {
		response.sendRedirect("Confirm.jsp");
	}
%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ChooseCofirm.jsp' starting page</title>
    
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
    This is my JSP page. <br>
  </body>
</html>

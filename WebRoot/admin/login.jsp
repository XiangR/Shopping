<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
    
<%
	String action = request.getParameter("action");
	if(action != null && action.equals("login")) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username == null || !username.equals("admin")) {
			out.println("您的用户名输入错误！ ");
		} else if (password == null || !password.equals("admin")){
			out.println("您的密码输入错误！");
		} else {
			//request.getSession().setAttribute("admin", "true");
			session.setAttribute("admin", "true");
			response.sendRedirect("index.jsp");
		}
	}
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form name="form" action="login.jsp" method="post">
	<input type="hidden" name="action" value="login"/>
<table border="1" align="center">
	<tr>
		<td colspan="2" align="center">后台登录</td>
	</tr>
	<tr>
		<td>admin name:</td>
		<td><input type="text" size="10" name="username"/></td>
	</tr>
	<tr>
		<td>admin password:</td>
		<td><input type="password" size="10" name="password"/></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="login"/><input type="reset" value="reset"/></td>
	</tr>
</table>
</form>
</body>
</html>

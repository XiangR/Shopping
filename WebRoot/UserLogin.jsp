<%@page import="com.xiangR.shopping.exception.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.xiangR.shopping.*"%> 
<%@ page import="com.xiangR.shopping.user.*"%>
<%
String action = request.getParameter("action");
if(action != null && action.equals("login")) {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	User u = null;
	try{
		u = UserManager.validate(username, password);
	} catch(UserNotFoundException e) {
		out.println("User not found！");
		return;
	} catch(PasswordNotCorrectException e) {
		out.println("Password not Correct！ ");
		return;
	}
	request.getSession().setAttribute("user", u);
	response.sendRedirect("Confirm.jsp");
	//response.sendRedirect("selfservice.jsp");
	
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<form action="UserLogin.jsp" method="post">
	<input type="hidden" name="action" value="login"/>
<table border="1" align="center">
	<tr>
		<td colspan="2" align="center">User Login</td>
	</tr>
	<tr>
		<td>username:</td>
		<td><input type="text" size="10" name="username"/></td>
	</tr>
	<tr>
		<td>password:</td>
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
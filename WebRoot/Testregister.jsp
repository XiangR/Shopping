<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.xiangR.shopping.*"%>
<%@ page import="com.xiangR.shopping.user.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	request.setCharacterEncoding("utf-8");
	String action = request.getParameter("action");
	if(action != null && action.equals("register")) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");		// 在后台一般对二次密码都有一个获取 
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setPhone(phone);
		u.setAddr(addr);
		u.save();
		out.println("祝贺你，添加成功 ！ ");
		out.println(u.getUsername());
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>金尚商城会员注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="JavaScript" src="script/regcheckdata.js"></script>
</head>

<body>
	<form method="get" name="form" action="Testregister.jsp"
			onSubmit="return checkdata();">
		<input type="hidden" name="action" value="register" />

		<table class="tableborder" align="center" cellpadding="4"
			cellspacing="1" width="97%">
			<tbody>
				<tr>
					<td colspan="2" class="header">注册 - 必填内容</td>
				</tr>
				<tr>
					<td class="altbg1" width="21%">用户名:</td>
					<td class="altbg2"><input id="userid" name="username"
						size="25" maxlength="25" type="text" onblur="validate()">
						<span id="usermsg"></span>
				</tr>

				<tr>
					<td class="altbg1">密码:</td>
					<td class="altbg2"><input name="password" size="25"
						type="password">
					</td>
				</tr>
				<tr>
					<td class="altbg1">确认密码:</td>
					<td class="altbg2"><input name="password2" size="25"
						type="password">
					</td>
				</tr>

				<tr>
					<td class="altbg1">&#30005;&#35805;:</td>
					<td class="altbg2"><input name="phone" type="text" id="phone"
						size="25">
					</td>
				</tr>

				<tr>
					<td class="altbg1" valign="top">送货地址:</td>
					<td class="altbg2"><textarea name="addr" cols="60" rows="5"
							id="addr"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<center>
			<input name="regsubmit" value="提 &nbsp; 交" type="submit">
		</center>
	</form>
</body>
</html>

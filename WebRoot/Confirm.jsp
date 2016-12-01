<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xiangR.shopping.user.*"%>
<%@ page import="com.xiangR.shopping.client.*"%>

<%
	User u = (User)session.getAttribute("user");
	if(u == null) {
		out.println("您现在没有登录，如果您确认购买，只能按照市场价。");
		out.println("<a href=ConfirmNormal.jsp>继续购买</a>");
		out.println("<a href=UserLogin.jsp>登录</a>");
		u = new User();
	//	response.sendRedirect("UserLogin.jsp");
	//	return;
	}
	
	Cart c = (Cart)session.getAttribute("cart");
	if(c == null) {
		c = new Cart();
		session.setAttribute("cart", c);
	}
%>


<%
	List<CartItem> items = c.getItems();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>最后确认</title>

</head>
<body>
<center>这里是您的购买信息（Confirm）</center><br>
<table align="center" border="1">
	<tr>
		<td>产品ID</td>
		<td>产品名称</td>
		<td>购买数量</td>
		<td>单价</td>
		<td>总价</td>

	</tr>
	<%
	for(Iterator<CartItem> it = items.iterator(); it.hasNext(); ) {
		CartItem ci = it.next();
		%>
		<tr>
			<td><%=ci.getProduct().getId() %></td>
			<td><%=ci.getProduct().getName() %></td>
			<td><%=ci.getCount() %></td>
			<td><%=ci.getProduct().getMemberPrice() %></td>
			<td><%=ci.getProduct().getMemberPrice() * ci.getCount() %></td>
			
		</tr>
		<%
	}
	%>
	<tr>
		<td colspan=4>
			
		</td>
		<td>
			总价: <%=c.getTotalMemberPrice()%>
		</td>
	</tr>
	<tr>
		<td colspan=5>
			<form action="Order.jsp" method="post">
			欢迎你: <%=u.getUsername() %> <br><br>
			phone: <%=u.getPhone() %> <br><br>
			Addr : <textarea name="addr"><%=u.getAddr() %></textarea>
			<input type="submit" value="确认下单"> 
			</form>
		</td>
	</tr>
</table>

</body>
</html>

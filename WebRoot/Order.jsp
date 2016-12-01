<%@page import="java.sql.Timestamp"%>
<%@page import="com.xiangR.shopping.sale.SalesOrder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	import="com.xiangR.shopping.user.*,com.xiangR.shopping.client.*,com.xiangR.shopping.order.*,java.util.*"
	pageEncoding="utf-8"%>


<%
	request.setCharacterEncoding("utf-8");

	User user = (User) session.getAttribute("user");
	if (user == null) {
		//response.sendRedirect("UserLogin.jsp");
		//return;
		
		// 因为 user 的必要性 我们在session不存在情况下 必须在new出来一个
		user = new User();
		user.setId(-1);
	}

	Cart c = (Cart) session.getAttribute("cart");
	if (c == null) {
		c = new Cart();
		session.setAttribute("cart", c);
	}

	String addr = request.getParameter("addr");
	SalesOrder so = new SalesOrder();
	so.setCart(c);
	so.setAddr(addr);
	so.setoDate(new Timestamp(System.currentTimeMillis()));
	so.setUser(user);
	so.setStatus(0);		// status 0 状态
	
System.out.println(so);
	so.save();	
	
//	session.removeAttribute("cart");
%>

<%=user.getUsername() %> 谢谢您在本站购物
单已下! 号:
<%=so.getId() %>


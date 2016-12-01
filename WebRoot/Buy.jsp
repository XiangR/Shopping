 <%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.xiangR.shopping.client.*, com.xiangR.shopping.product.*" %>

<%--
	// check cart session
	Cart cart = (Cart)session.getAttribute("cart");
	if(cart == null) {
		cart = new Cart();
		session.setAttribute("cart", cart);
	}
--%>
	<jsp:useBean id="cart" class="com.xiangR.shopping.client.Cart" scope="session"></jsp:useBean>
<%
	if(cart == null) {
		out.println("没有任何的购物项");
		return;
	}

	request.setCharacterEncoding("utf-8");
	String action = request.getParameter("action");
	
	// add	
	if(action != null && action.trim().equals("add")) {
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = ProductMgr.getInstance().loadById(id);
		CartItem ci = new CartItem();
		ci.setProduct(p);
		ci.setCount(1);
		cart.addItem(ci);
	}
	
	// delete
	if(action != null && action.trim().equals("delete")) {
		int id = Integer.parseInt(request.getParameter("id"));
		cart.deletItem(id);
	}
	
	// update
	if(action != null && action.trim().equals("update")) {
		// 通过遍历的方式 更新每个 CartItem 的 cuont 
		for(int i=0; i<cart.getItems().size(); i++) {
			CartItem ci = cart.getItems().get(i);
			int count = Integer.parseInt(request.getParameter("p" + ci.getProduct().getId()));
			ci.setCount(count);
		}
	}
 %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	List<CartItem> items = cart.getItems();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>购物车</title>

</head>
<body>

<center>您的购物车</center><br>
<form action="Buy.jsp" method="get">
<input type="hidden" name="action" value="update"/>
<table align="center" border="1">
	<tr>
		<td>产品ID</td>
		<td>产品名称</td>
		<td>购买数量</td>
		<td>单价</td>
		<td>会员价</td>
		<td>总价(按照会员价)</td>
		<td>处理</td>
	</tr>
	<%
	for(Iterator<CartItem> it = items.iterator(); it.hasNext(); ) {
		CartItem ci = it.next();
		%>
		<tr>
			<td><%=ci.getProduct().getId() %></td>
			<td><%=ci.getProduct().getName() %></td>
			<td>
				<input type="text" size=3 name="<%="p" + ci.getProduct().getId() %>" value="<%=ci.getCount() %>">				
			</td>
			<td><%=ci.getProduct().getNormalPrice() %></td>
			<td><%=ci.getProduct().getMemberPrice() %></td>
			<td><%=ci.getProduct().getMemberPrice()*ci.getCount() %></td>
			<td>
				
				<a href="Buy.jsp?action=delete&id=<%=ci.getProduct().getId() %>">删除</a>
				
			</td>
		</tr>
		<%
	}
	%>
	</table>
	<center>
		您购物车里的总价 ：<%=cart.getTotalMemberPrice() %> <br>
		<a href="ChooseConfirm.jsp">下单</a> &nbsp&nbsp &nbsp&nbsp
		<a href="javascript:document.forms[0].submit()">修改</a>
	</center>

</form>
</body>
</html>

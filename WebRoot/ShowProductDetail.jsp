<%@page import="java.sql.Timestamp"%>
<%@ page language="java" import="com.xiangR.shopping.product.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.xiangR.shopping.category.*"%>
<%@ page import="java.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	
	int id = Integer.parseInt(request.getParameter("id"));
	Product p = ProductMgr.getInstance().loadById(id);
	
%>
<script type="text/javascript">	
	//parent.main.location.reload();	
</script>
<%
%>

<html>
<head>
<title>金尚商城产品添加</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords"
	content="Discuz!,Board,Comsenz,forums,bulletin board,">
<meta name="description" content="醒客论坛专区  - Discuz! Board">
<meta name="generator" content="Discuz! 4.0.0RC4 with Templates 4.0.0">
<meta name="MSSmartTagsPreventParsing" content="TRUE">
<meta http-equiv="MSThemeCompatible" content="Yes">
</head>
<body>
		<center>产品展示</center><br>
		<center>
		<img border="0" height="90" width="75" alert=<%=p.getName() %> src="images/product/<%=p.getId() + ".gif"%>"/><br>
		<br>
		商品的名称　　：<%=p.getName() %><br><br>
		商品的描述　　：<%=p.getDescr() %><br><br>
		商品的普通价格：<%=p.getNormalPrice() %><br><br>
		商品的会员价格：<%=p.getMemberPrice() %><br><br>
		<a href="Buy.jsp?id=<%=id %>&action=add">我买了</a>
		</center>
</body>
</html>
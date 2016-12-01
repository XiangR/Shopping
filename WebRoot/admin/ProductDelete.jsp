<%@page import="com.xiangR.shopping.category.CategoryDAO"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.xiangR.shopping.product.*"%>

<%
	
	int id = Integer.parseInt(request.getParameter("id"));
	ProductMgr.getInstance().deleteById(id);
	
%>

<html>
<head>
<title>删除产品</title>
</head>
<center>删除成功！</center>
<body>
	<script type="text/javascript">	
		// 这句话可以重新load 回原页面
		parent.main.location.reload();
	</script>
</body>
</html>
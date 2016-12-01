<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%--<jsp:include page="_sessionCheck.jsp" flush="true"/>
--%>
<%
	String admin = (String) session.getAttribute("admin");
	if(admin == null && !admin.equals("true")) {
		response.sendRedirect("login.jsp");
	}
%>
<html>

<script language="javascript">
	state = 0 ;
	menuState = 0;
	mainState = 0;
</script>

<head>
<title>金尚商城管理平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
 
<frameset rows="29,*" frameborder="0" border="0" framespacing="0" cols="*">
  <frame name="top" scrolling="NO" noresize src="top.html">
  <frameset cols="20%,*" frameborder="0" border="0" framespacing="0" rows="*" scrolling="NO" name="mleft">
    <frame src="menu.jsp" frameborder=NO border="0" scrolling="NO" >
    <frameset rows="20,100%,*" name="content" frameborder="1" framespacing="1" cols="*">
      <frame src="title.html" frameborder=0 noresize scrolling="NO" name="mtitle">
      <frame src="" frameborder=0  name="main" marginwidth="0" marginheight="0" scrolling="YES">
      <frame src="" frameborder=0  name="detail">
    </frameset>
  </frameset>
</frameset>
<noframes>
</noframes>
</html>
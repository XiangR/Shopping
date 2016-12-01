<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<HTML>
<HEAD>
<TITLE></TITLE>
<LINK REL=stylesheet HREF="script/toc.css" TYPE="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script/AdminTree.js"></script>
<STYLE TYPE='text/css'>
.level1 {margin-left:30;}
.level2 {display:none;margin-left:38;}
</STYLE>
</HEAD>
<BODY onload="init()" topmargin="0" leftmargin="0" rightmargin="0">
<DIV CLASS="level1" ID='head2Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head2");'><IMG border=0 name=imEx  src="images/arrowUp.gif" id=ttt> 用户管理</a>
</DIV>

<DIV CLASS="level2" ID='head2Child'>
	<A href="UserList.jsp" id=ttt target=main><LI>用户列表</LI></a>
</DIV>

<DIV CLASS="level1" ID='head3Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head3");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> 类别管理</a>
</DIV>

<DIV CLASS="level2" ID='head3Child'>
	<A href="CategoryAdd.jsp" id=ttt target=main><LI>添加类别</LI></a>
	<A href="CategoryList.jsp" id=ttt target=main><LI>类别列表</LI></a>
	<A href="CategoryListAJAX.jsp" id=ttt target=main><LI>类别列表AJAX</LI></a>
</DIV>

<DIV CLASS="level1" ID='head4Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head4");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> 产品管理</a>
</DIV>

<DIV CLASS="level2" ID='head4Child'>
	<A href="ProductList.jsp" id=ttt target=main><LI>产品列表</LI></a>
	<A href="ProductAdd.jsp" id=ttt target=main><LI>产品添加</LI></a>
	<A href="ProductSearch.jsp" id=ttt target=main><LI>产品搜索</LI></a>
</DIV>

<DIV CLASS="level1" ID='head6Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head6");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> 订单管理</a>
</DIV>

<DIV CLASS="level2" ID='head6Child'>
	<A href="OrderList.jsp" id=ttt target=main><LI>订单列表</LI></a>
</DIV>

<DIV CLASS="level1" ID='head7Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head7");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> 统计分析</a>
</DIV>

<DIV CLASS="level2" ID='head7Child'>
	<A href="../servlet/SalesCountServlet" id=ttt target=main><LI>销量统计</LI></a>
</DIV>

<DIV CLASS="level1" ID='head8Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head8");' id=ttt><IMG border=0 height=13 name=imEx  src="images/arrowUp.gif" width=17> 直播管理</a>
</DIV>

<DIV CLASS="level2" ID='head8Child'>
      <A href="Live.jsp" id=ttt target=main><LI>直播组织</LI></a>
      <A href="LiveWorkstation.jsp" id=ttt target=main><LI>直播工作站管理</LI></a>
</DIV>

</BODY>
</html>

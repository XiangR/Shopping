<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.xiangR.shopping.category.*"%>
<%@ page import="java.util.*"%>
<jsp:include page="_sessionCheck.jsp" flush="true"/>
<%
	List<Category> categories = CategoryDAO.getCategories();
%>

<html>
<head>
<script language="javascript" src="script/TV20.js"></script>
<script language="javascript">
	var req;
	var gKey;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	var t = 2;
	function showTime() {
		
		t -= 1;
		document.getElementById("div1").innerHTML = t;
		if(t == 0) {
			location.href = "CategoryModify.jsp?id=" + key;;	
		}
		setTimeout("showTime()", 1000);
	}
	
	function myLabelDblClick(key, parentKey) {
		
		showTime();
		
	/*
		if(findNode(key).subitems.length > 0) return;
		init();
		gKey = key;
		var url = "GetCategoryChilds2.jsp?id=" + escape(key);
		req.open("GET", url, true);
		req.onreadystatechange = callback;
		req.send(null);
		*/
	}
	
	function callback() {
		if(4 == req.readyState) {
			if(200 == req.status) {
				eval(req.responseText);
				var node = findNode(gKey);
				if(node.subitems.length > 0) {
					node.refresh();
					node.open();
				}
			}
		}
		
	}
	function modify(key, parentkey) {
		alert('a');	
	}
	
</script>
</head> 

<body>
<div id="div1"></div>
<script language="javascript">
<!--
	// 绑定一个 双击事件
	addListener("dblclick", "myLabelDblClick");
	
	addNode(-1,0,"所有类别","images/top.gif");
	<%
	for(int i=0; i<categories.size(); i++) {
		Category c = categories.get(i);
		%>
		addNode(<%=c.getPid()%>,<%=c.getId()%>,"<%=c.getName()%>","images/top.gif");
		<%
	}
	%>
	showTV();
-->
</script>
</body>
</html>
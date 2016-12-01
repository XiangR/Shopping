<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="com.xiangR.shopping.category.*"%>
<%@ page import="com.xiangR.shopping.product.*"%>
<%@ page import="com.xiangR.shopping.dao.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<title>My JSP 'ProductSearch.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function check() {
	// 对数据的一个检测
	with(document.forms['complex']) {
		if(lowNormalPrice.value == null || lowNormalPrice.value == "") {
			lowNormalPrice.value = -1;
		}
		if(highNormalPrice.value == null || highNormalPrice.value == "") {
			highNormalPrice.value = -1;
		}
		if(lowMemberPrice.value == null || lowMemberPrice.value == "") {
			lowMemberPrice.value = -1;
		}
		if(highMemberPrice.value == null || highMemberPrice.value == "") {
			highMemberPrice.value = -1;
		}
		
	}
	/*
	var selectedCategory = document.complex.categoryId.options[document.complex.categoryId.selectedIndex];
	var selectedValue = selectedCategory.value;
	if(selectedValue.split("|")[1] == "1") {
		alert("请选择第二级分类！");
		document.complex.categoryId.focus();
		return false;
	} else {
		selectedCategory.value = selectedValue.split("|")[0];
	}
	return true;
	*/
}
</script>
	</head>

	<body>
		<center>
		<%--
			简单搜索
			<form name="simple" action="SearchResult.jsp" method="get">
				<input type="text" size="10" name="keyword" />
				搜索
				<input value="提 &nbsp; 交" type="submit">
			</form>
		--%>
			<br>
			
			<form name="Normal" action="NormalSearchResult.jsp" method="post" onsubmit="return check()">
				<input type="hidden" name="action" value="complex" />
				<table width="750" align="center" border="2">
					<tr>
						<td colspan="2" align="center">
							普通搜索
						</td>
					</tr>
					<tr>
						<td>
							类别：
						</td>
						<td>
							<%
								List<Category> categorys = CategoryDAO.getCategories();
							
								for(Iterator<Category> it = categorys.iterator(); it.hasNext(); ) {
									Category c = it.next();
									
									if(c.isLeaf()) {
								%>
									<input type="checkbox" name="categoryId" value="<%=c.getId() %>"><%=c.getName() %><br>
								<%
									} else {
									%>
									<%=c.getName() %>
									<%
									}
								}
							%>
						</td>
					</tr>
					<tr>
						<td>
							关键字：
						</td>
						<td>
							<input type=text name="keyWord" size="15" maxlength="12">
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="提交">
							<input type="reset" value="重置">
						</td>
					</tr>

				</table>
			</form>
				<br><br>
			<form name="complex" action="ComplexSearchResult.jsp" method="post" onsubmit="return check()">
				<input type="hidden" name="action" value="complex" />
				<table width="750" align="center" border="2">
					<tr>
						<td colspan="2" align="center">
							高级搜索
						</td>
					</tr>
					<tr>
						<td>
							类别：
						</td>
						<td>
							<select name="categoryId" style="font-size: 9pt; color: rgb(85, 85, 85);">
								<%-- 选中所有的类别 --%>
								<option selected="selected" value="-1">
									--所有商品--
									<%
										
										for(Iterator<Category> it = categorys.iterator(); it.hasNext(); ) {
											Category c = it.next();
											String ptr = "";
											for(int i = 1; i < c.getGrade(); i ++) {
												ptr += "---";
											}
									%>
								</option>
								<option value="<%=c.getId()%>">
											<%=ptr + c.getName() %>
								</option>
									<%
										}
									%>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							关键字：
						</td>
						<td>
							<input type=text name="keyWord" size="15" maxlength="12">
						</td>
					</tr>
					<tr>
						<td>
							市场价格
						</td>
						<td>
							From:
							<input type=text name="lowNormalPrice" size="15" maxlength="12">
							To
							<input type=text name="highNormalPrice" size="15" maxlength="12">
						</td>
					</tr>
					<tr>
						<td>
							会员价格
						</td>
						<td>
							From:
							<input type=text name="lowMemberPrice" size="15" maxlength="12">
							To
							<input type=text name="highMemberPrice" size="15" maxlength="12">
						</td>
					</tr>
					<tr>
						<td>
							日期
						</td>
						<td>
							From:
							<input type=text name="startDate" size="15" maxlength="12">
							To
							<input type=text name="endDate" size="15" maxlength="12">
						</td>
					</tr>

					<tr>
						<td></td>
						<td>
							<input type="submit" value="提交">
							<input type="reset" value="重置">
						</td>
					</tr>

				</table>
			</form>
		</center>
	</body>
</html>

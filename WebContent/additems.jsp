<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="therollshop.model.*"%>
<%
Admin auth = (Admin) request.getSession().getAttribute("authadmin");
if (auth != null) {
	request.setAttribute("auth", auth);
} else {
	response.sendRedirect("admin.jsp");
}
//response.setIntHeader("refresh", 1);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="includes/head.jsp"%>
<title>Add Items</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nav_foot.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/additems.css" />
<body>
	<%@include file="includes/lock_and_top.jsp"%>
	<h3>&mdash; ADD ITEMS &mdash;</h3>
	</div>

	<div class="container">
		<form method="POST" action="enter-items" enctype="multipart/form-data"
			id="form">
			<h4>Add item details</h4>
			<input type="text" name="item-name" placeholder="Item Name" required />
			<input type="text" name="item-price" placeholder="Item Price" required />
			<input type="text" name="item-description"
				placeholder="Item Description" required /> <input type="number"
				name="item-stock" placeholder="Item stock" required />
			<h5>Select image</h5>
			<input type="file" id="file" name="file" placeholder="Item Image"
				accept="image/*" required /> <input type="submit" value="Submit" />

		</form>
	</div>


	<div class="nav">
		<%
		if (auth != null) {
		%>
		<a href="additems.jsp" class="nav__link nav__link__active"> <span
			class="material-icons nav__icon"> restaurant_menu </span> <span
			class="nav__text" id="mn">Add</span>
		</a> <a href="deliver.jsp" class="nav__link"> <span
			class="material-icons nav__icon"> takeout_dining </span> <span
			class="nav__text" id="or">Deliver</span>
		</a> <a id="lgo" class="nav__link" href="admin-log-out"> <span
			class="material-icons nav__icon"> logout </span> <span
			class="nav__text">Logout</span>
		</a>
		<%
		} else {
		%>
		<a id="lgo" class="nav__link nav__link__active" href="admin-log-out">
			<span class="material-icons nav__icon"> logout </span> <span
			class="nav__text">Logout</span>
		</a>
		<%
		}
		%>

	</div>
</body>
</html>
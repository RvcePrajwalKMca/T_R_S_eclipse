<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="therollshop.model.*"%>
<%
Admin auth = (Admin) request.getSession().getAttribute("authadmin");
if (auth != null) {
	response.sendRedirect("additems.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Login here!</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/lgn_rgr.css" />
<link>
</head>
<body>
	<%@include file="includes/lock_and_top.jsp"%>
	<h3>&mdash; ADMIN LOGIN &mdash;</h3>
	</div>
	<form action="admin-login" method="post" class="sign-in-form">
		<h2 class="title">Sign in</h2>
		<div class="input-field">
			<i class="fas fa-user"></i> <input type="text" name="admin-id"
				placeholder="Admin Id" required>
		</div>
		<div class="input-field">
			<i class="fas fa-lock"></i> <input type="password"
				name="admin-password" placeholder="Password" required>
		</div>
		<input type="submit" value="Login" class="btn-lin btn solid" id="lgn" />
	</form>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
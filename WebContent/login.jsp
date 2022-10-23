<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="therollshop.model.*"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Login here!</title>
<%@include file="includes/head.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/lgn_rgr.css" />
<link>
</head>
<body>
	<%@include file="includes/lock_and_top.jsp"%>
	<h3>&mdash; LOGIN &mdash;</h3>
	</div>
	<form action="user-login" method="post" class="sign-in-form">
		<h2 class="title">Sign in</h2>
		<div class="input-field">
			<i class="fas fa-user"></i> <input type="text" name="login-email"
				placeholder="Username(email)" required>
		</div>
		<div class="input-field">
			<i class="fas fa-lock"></i> <input type="password"
				name="login-password" placeholder="Password" required>
		</div>
		<input type="submit" value="Login" class="btn-lin btn solid" id="lgn" />
		<div class="content">
			<h3>New here ?</h3>
			<p style="font-size: 16px">
				<a class="login"
					href="${pageContext.request.contextPath}/register.jsp">Register
					now</a> and enjoy delicious rolls from THE ROLL SHOP
			</p>

		</div>
	</form>


	<%@include file="includes/footer.jsp"%>
</body>
</html>
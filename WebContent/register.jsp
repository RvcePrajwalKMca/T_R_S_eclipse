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
<title>Insert title here</title>
<%@include file="includes/head.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/lgn_rgr.css" />
<link>
</head>
<body>
	<%@include file="includes/lock_and_top.jsp"%>
	<h3>&mdash; REGISTER &mdash;</h3>
	</div>
	<form action="user-register" class="sign-up-form" method="post">
		<h2 class="title">Sign up</h2>
		<div class="input-field">
			<i class="fas fa-user"></i> <input type="text" name="register-name"
				placeholder="Username" required>
		</div>
		<div class="input-field">
			<i class="fas fa-envelope"></i> <input type="email"
				name="register-email" placeholder="Email" required>
		</div>
		<div class="input-field">
			<i class="fas fa-lock"></i> <input type="password"
				name="register-password" placeholder="Password" required>
		</div>
		<input type="submit" class="btn-sup btn solid" value="Sign up" />
		<div class="content">
			<h3>One of us ?</h3>
			<p style="font-size: 16px">
				<a class="login" href="${pageContext.request.contextPath}/login.jsp">Login
					now</a> and order in real time
			</p>

		</div>
	</form>
	<%@include file="includes/footer.jsp"%>
</body>
</html>
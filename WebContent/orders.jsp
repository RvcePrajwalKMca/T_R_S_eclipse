<%@page import="java.text.DecimalFormat"%>
<%@page import="therollshop.connection.DbCon"%>
<%@page import="therollshop.dao.*"%>
<%@page import="therollshop.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
List<Order> orders = null;
if (auth != null) {
	request.setAttribute("auth", auth);
	orders= new OrderDao(DbCon.getConnection()).userOrders(auth.getId());
	/*orders = orderDao.userOrders(auth.getId()); */
} else {
	response.sendRedirect("login.jsp");
}
ArrayList<Bucket> bucket_list = (ArrayList<Bucket>) session.getAttribute("bucket-list");
//List<Bucket> bucketItem = null;
if (bucket_list != null) {
	/* ItemDao iDao = new ItemDao(DbCon.getConnection());
	bucketItem = iDao.getBucketItems(bucket_list);*/
	request.setAttribute("bucket-list", bucket_list);
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<title>TRS bucket</title>
<%@include file="includes/head.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bucket.css" />
<link>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nav_foot.css" />
<body>
	<%@include file="includes/lock_and_top.jsp"%>
	<h3>&mdash; ORDER &mdash;</h3>
	</div>


<div class="container my-3">
	<div class="d-flex py-3" style="background-color:white;">All Orders</div>
	<table class="table table-light">
		<thead>
			<tr>
				<th scope="col">Date</th>
				<th scope="col">Name</th>
				<th scope="col">Quantity</th>
				<th class="price-column" scope="col">Price</th>
				<th scope="col">Cancel</th>
			</tr>
		</thead>
		<tbody>
		
		<%
			if(orders!=null){
				for(Order o:orders){%>
					<tr>
						<td><%= o.getDate() %></td>
						<td><%= o.getName() %></td>
						<td><%= o.getQuantity() %></td>
						<td class="price-column"><%= o.getPrice() %></td>
						<td><a class = "btn btn-sm btn-danger" href="cancel-order?id=<%= o.getOrderId() %>">x</a></td>
					</tr>
				<%}
			}
		%>
		
		</tbody>
		</table>
	</div>


	<nav class="nav">
		<a href="index.jsp" class="nav__link"> <span
			class="material-icons nav__icon"> restaurant_menu </span> <span
			class="nav__text" id="mn">Menu</span>
		</a> <a href="bucket.jsp" class="nav__link"> <span
			class="material-icons nav__icon"> fastfood </span> <span
			class="nav__text" id="or">Bucket<span id="hover_badge">&nbsp(<%=(bucket_list != null) ? bucket_list.size() : 0%>)
			</span></span>
		</a>
		<%
		if (auth != null) {
		%>
		<a href="orders.jsp" class="nav__link nav__link__active"> <span
			class="material-icons nav__icon"> room_service </span> <span
			class="nav__text" id="or">Order</span>
		</a> <a id="lgo" class="nav__link" href="log-out"> <span
			class="material-icons nav__icon"> logout </span> <span
			class="nav__text">Logout</span>
		</a>
		<%
		} else {
		%>
		<a href="login.jsp" class="nav__link"> <span
			class="material-icons nav__icon"> login </span> <span
			class="nav__text" id="or">Login</span>
		</a>
		<%
		}
		%>

	</nav>
	<%@include file="includes/footer.jsp"%>
	<script src="${pageContext.request.contextPath}/js/all.js"></script>

</body>
</html>
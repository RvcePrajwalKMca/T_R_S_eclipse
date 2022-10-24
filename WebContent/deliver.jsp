<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="therollshop.connection.DbCon"%>
<%@page import="therollshop.dao.*"%>
<%@page import="therollshop.model.*"%>
<%@page import="java.util.*"%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>
<%
Admin auth = (Admin) request.getSession().getAttribute("authadmin");
List<Order> orders = null;
if (auth != null) {
	request.setAttribute("auth", auth);
	orders = new OrderDao(DbCon.getConnection()).allOrders();
} else {
	response.sendRedirect("admin.jsp");
}
ArrayList<Bucket> bucket_list = (ArrayList<Bucket>) session.getAttribute("bucket-list");
if (bucket_list != null) {
	/* ItemDao iDao = new ItemDao(DbCon.getConnection());
	bucketItem = iDao.getBucketItems(bucket_list);*/
	request.setAttribute("bucket-list", bucket_list);
}
response.setIntHeader("refresh", 5);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="includes/head.jsp"%>
<title>Deliver Orders</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nav_foot.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bucket.css" />
<body>
	<%@include file="includes/lock_and_top.jsp"%>
	<h3>&mdash; Deliver Orders &mdash;</h3>
	</div>

	<div class="container my-3">
		<div class="d-flex py-2" style="background-color: white;">All
			Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Order id</th>
					<th scope="col">Name</th>
					<th scope="col">Quantity</th>
					<th class="price-column" scope="col">Price</th>
					<th scope="col">Deliver</th>
				</tr>
			</thead>
			<tbody>

				<%
				if (orders != null) {
					for (Order o : orders) {
				%>
				<tr>
					<td><%=o.getOrderId()%></td>
					<td><%=o.getName()%></td>
					<td><%=o.getQuantity()%></td>
					<td class="price-column"><%=o.getPrice()%></td>
					<td><a class="btn btn-sm btn-danger"
						href="deliver-order?id=<%=o.getOrderId()%>">&#10003;</a></td>
				</tr>
				<%
				}
				}
				%>

			</tbody>
		</table>
	</div>


	<nav class="nav">
		<%
		if (auth != null) {
		%>
		<a href="additems.jsp" class="nav__link"> <span
			class="material-icons nav__icon"> restaurant_menu </span> <span
			class="nav__text" id="mn">Add</span>
		</a> <a href="deliver.jsp" class="nav__link nav__link__active"> <span
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
	</nav>
	<%@include file="includes/footer.jsp"%>

</body>
</html>
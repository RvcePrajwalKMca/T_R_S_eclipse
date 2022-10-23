<%@page import="therollshop.connection.DbCon"%>
<%@page import="therollshop.dao.ItemDao"%>
<%@page import="therollshop.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);


User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
} else {
	response.sendRedirect("login.jsp");
}

ArrayList<Bucket> bucket_list = (ArrayList<Bucket>) session.getAttribute("bucket-list");
List<Bucket> bucketItem = null;
if (bucket_list != null) {
	ItemDao iDao = new ItemDao(DbCon.getConnection());
	bucketItem = iDao.getBucketItems(bucket_list);
	double total = iDao.gettotalCartPrice(bucket_list);
	request.setAttribute("bucket-list", bucket_list);
	request.setAttribute("total", total);
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
<h3>&mdash; BUCKET &mdash;</h3>
</div>


<div class="container my-3">
	<div class="d-flex py-3">
		<div id="price">Total Price: Rs. ${(total>0)?dcf.format(total):0}</div> 
		<%-- <div class="d-flex py-3" style="background-color:white;">Total Price: Rs. ${(total>0)?dcf.format(total):0}</div> --%>
		<a id="checkout" class="mx-4 btn btn-sm chk" href="bucket-check-out">Order
			all</a>
	</div>
	<table class="table table-light">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th class="price-column" scope="col">Price</th>
				<th scope="col">Order Now</th>
				<th scope="col" class="stock-column">Stock</th>
				<th scope="col">Cancel</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (bucket_list != null) {
				for (Bucket b : bucketItem) {
			%>
			<tr>
				<td><%=b.getName()%></td>
				<td class="price-column"><%=dcf.format(b.getPrice())%></td>
				<td>
					<form id="wrapper" action="order-now" method="post"
						class="form-inline">
						<input type="hidden" name="id" value="<%=b.getId()%>"
							class="form-input">
						<div class="form-group d-flex justify-content-between">
							<a id="wrapper" class="btn btn-sm btn-decre"
								href="quantity-inc-dec?action=dec&id=<%=b.getId()%>"><i
								class="fas fa-minus-square"></i></a> <input id="count" type="text"
								name="quantity" class="form-control"
								value="<%=b.getQuantity()%>" readonly> <a id="wrapper"
								class="btn bnt-sm btn-incre"
								href="quantity-inc-dec?action=inc&id=<%=b.getId()%>"><i
								class="fas fa-plus-square"></i></a>
						</div>
						<button type="submit" id="checkout" class="btn btn-sm chk"
							style="padding: 0;">Order</button>
					</form>
				</td>
				<td class="stock-column"><%=b.getStock()%></td>
				<td><a href="remove-from-bucket?id=<%=b.getId()%>"
					class="btn btn-sm btn-danger" >x</a></td>
			</tr>

			<%
			}
			}
			%>
		</tbody>
	</table>
</div>


<nav class="nav">
	<a href="index.jsp" class="nav__link"> <span
		class="material-icons nav__icon"> restaurant_menu </span> <span
		class="nav__text" id="mn">Menu</span>
	</a> <a href="bucket.jsp" class="nav__link  nav__link__active"> <span
		class="material-icons nav__icon"> fastfood </span> <span
		class="nav__text" id="or">Bucket<span id="hover_badge">&nbsp(<%=(bucket_list != null) ? bucket_list.size() : 0%>)
		</span>
	</span>
	</a>
	<%
	if (auth != null) {
	%>
	<a href="orders.jsp" class="nav__link"> <span
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
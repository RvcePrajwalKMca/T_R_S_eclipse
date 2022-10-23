<%@page import="therollshop.connection.DbCon"%>
<%@page import="therollshop.model.*"%>
<%@page import="therollshop.dao.*"%>
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
ItemDao it = new ItemDao(DbCon.getConnection());
List<Item> items = it.getAllItems();
ArrayList<Bucket> bucket_list = (ArrayList<Bucket>) session.getAttribute("bucket-list");
List<Bucket> bucketItem = null;
if (bucket_list != null) {
	ItemDao iDao = new ItemDao(DbCon.getConnection());
	bucketItem = iDao.getBucketItems(bucket_list);
	request.setAttribute("bucket-list", bucket_list);
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
<title>Browse menu</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nav_foot.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style2.css" />
<body>
	<%@include file="includes/lock_and_top.jsp"%>
	<h3>&mdash; MENU &mdash;</h3>
	</div>
	<div class="menu" id="cont">
		<%
		if (!items.isEmpty()) {
			for (Item i : items) {
		%>
		<div class="rolls">
			<img src="images/item-images/<%=i.getImage()%>" />
			<div class="details">
				<div class="details-sub">
					<h5><%=i.getName()%>
					</h5>
					<h5 class="cost">
						Rs.
						<%=dcf.format(i.getPrice())%></h5>
				</div>
				<p>
					<%=i.getDescription()%>
				</p>
				<div class="cart">
					<a style="text-decoration: none; color: white;"
						href="add-to-bucket?id=<%=i.getId()%>">Add to bucket</a> <span
						class="data-badge">0</span>
				</div>
			</div>
		</div>
		<%
		}
		}
		%>

	</div>
	<nav class="nav">
		<a href="index.jsp" class="nav__link nav__link__active"> <span
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
	<script src="${pageContext.request.contextPath}/js/brw.js"></script>
	<script src="${pageContext.request.contextPath}/js/all.js"></script>

</body>
</html>

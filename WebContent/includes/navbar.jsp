<nav class="nav">
	<a href="index.jsp" class="nav__link nav__link__active"> <span
		class="material-icons nav__icon"> restaurant_menu </span> <span
		class="nav__text" id="mn">Menu</span>
	</a> <a href="bucket.jsp" class="nav__link"> <span
		class="material-icons nav__icon"> fastfood </span> <span
		class="nav__text" id="or">Bucket</span>
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
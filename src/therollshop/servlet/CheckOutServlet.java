package therollshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import therollshop.connection.DbCon;
import therollshop.dao.OrderDao;
import therollshop.model.Bucket;
import therollshop.model.Order;
import therollshop.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/bucket-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			// taking all bucket items
			ArrayList<Bucket> bucket_list = (ArrayList<Bucket>) request.getSession().getAttribute("bucket-list");
			User auth = (User) request.getSession().getAttribute("auth");

			if (bucket_list != null && auth != null) {
				for (Bucket b : bucket_list) {
					//preparing order object
					Order order = new Order();
					order.setId(b.getId());
					order.setUid(auth.getId());
					order.setQuantity(b.getQuantity());
					order.setDate(formatter.format(date));
					
					//instantiate doa class
					OrderDao oDao = new OrderDao(DbCon.getConnection());
					//insert method
					boolean result= oDao.insertOrder(order);
					if(!result) break;
				}
				bucket_list.clear();
				response.sendRedirect("orders.jsp");
				
			} else {
				if (auth == null) {
					response.sendRedirect("login.jsp");
				} else {
					response.sendRedirect("bucket.jsp");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package therollshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			User auth = (User) request.getSession().getAttribute("auth");
			if (auth != null) {
				String itemId = request.getParameter("id");
				int itemQuantity = Integer.parseInt(request.getParameter("quantity"));
				if (itemQuantity <= 0) {
					itemQuantity = 1;
				}
				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(itemId));
				orderModel.setUid(auth.getId());
				orderModel.setQuantity(itemQuantity);
				orderModel.setDate(formatter.format(date));
				OrderDao orderDao = new OrderDao(DbCon.getConnection());
				boolean result = orderDao.insertOrder(orderModel);
				if (result) {
					ArrayList<Bucket> bucket_list = (ArrayList<Bucket>) request.getSession().getAttribute("bucket-list");
					if (bucket_list != null) {
						for (Bucket b : bucket_list) {
							if (b.getId() == Integer.parseInt(itemId)) {
								bucket_list.remove(bucket_list.indexOf(b));
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
				} else {
					out.print("order failed");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

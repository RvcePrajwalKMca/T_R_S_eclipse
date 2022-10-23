package therollshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import therollshop.model.Bucket;

@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Bucket> bucket_list = (ArrayList<Bucket>) request.getSession().getAttribute("bucket-list");
			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (Bucket b : bucket_list) {
						out.print(b.getStock());
						if (b.getId() == id) {
							int quantity = b.getQuantity();
							quantity++;
							b.setQuantity(quantity);
							response.sendRedirect("bucket.jsp");
						}
					}
				}
				else if (action.equals("dec")) {
					for (Bucket b : bucket_list) {
						if (b.getId() == id && b.getQuantity()>1) {
							int quantity = b.getQuantity();
							quantity--;
							b.setQuantity(quantity);
							break;
						}
					}
					response.sendRedirect("bucket.jsp");
				}
				else {
					response.sendRedirect("bucket.jsp");
				}
			}
		}
	}

}

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

/**
 * Servlet implementation class RemoveFromBucketServlet
 */
@WebServlet("/remove-from-bucket")
public class RemoveFromBucketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveFromBucketServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if (id != null) {
				ArrayList<Bucket> bucket_list = (ArrayList<Bucket>) request.getSession().getAttribute("bucket-list");
				if (bucket_list != null) {
					for (Bucket b : bucket_list) {
						if (b.getId() == Integer.parseInt(id)) {
							bucket_list.remove(bucket_list.indexOf(b));
							break;
						}
					}
					response.sendRedirect("bucket.jsp");
				}
			} else {
				response.sendRedirect("bucket.jsp");
			}
		}
	}

}

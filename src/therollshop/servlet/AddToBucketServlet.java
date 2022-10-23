package therollshop.servlet;

import therollshop.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add-to-bucket")
public class AddToBucketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			ArrayList<Bucket> bucketList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			Bucket bu = new Bucket();
			bu.setId(id);
			bu.setQuantity(1);

			HttpSession session = request.getSession();
			ArrayList<Bucket> bucket_list = (ArrayList<Bucket>) session.getAttribute("bucket-list");

			if (bucket_list == null) {
				bucketList.add(bu);
				session.setAttribute("bucket-list", bucketList);
				response.sendRedirect("index.jsp");
			} else {
				bucketList = bucket_list;
				boolean exist = false;

				for (Bucket b : bucket_list) {
					if (b.getId() == id) {
						exist = true;
//						out.println(
//								"<h3 style='color:crimson;text-align:center'>Item already exits in cart.<a href='bucket.jsp'>GO to bucket page</a></h3>");

						response.setContentType("text/html");
						out.println("<script type = \"text/javascript\">");
						out.println("alert(\"Item already present in bucket\")");
						out.println("window.location.href = 'http://localhost:8081/therollshop/index.jsp'");
						out.println("</script>");

					}

				}
				if (!exist) {
					bucketList.add(bu);
					response.sendRedirect("index.jsp");
				}
			}
//			for (Bucket b : bucket_list) {
//				out.println(b.getId());
//			}

		}
	}
}

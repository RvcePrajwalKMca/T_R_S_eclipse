package therollshop.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import therollshop.connection.DbCon;
import therollshop.dao.*;
import therollshop.model.*;

import javax.servlet.annotation.*;
/* The Java file upload Servlet example */

@WebServlet(name = "AddItemsServlet", urlPatterns = { "/enter-items" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddItemsServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Receive file uploaded to the Servlet from the HTML5 form */
		try (PrintWriter out = response.getWriter()) {
			Part filePart = request.getPart("file");
			String fileName = filePart.getSubmittedFileName();
			Admin auth = (Admin) request.getSession().getAttribute("authadmin");
			if (auth != null) {
				Item itemModel = new Item();
				itemModel.setName(request.getParameter("item-name"));
				itemModel.setPrice(Double.parseDouble(request.getParameter("item-price")));
				itemModel.setDescription(request.getParameter("item-description"));
				itemModel.setStock(Integer.parseInt(request.getParameter("item-stock")));
				itemModel.setImage(fileName);

				ItemDao itemDao = new ItemDao(DbCon.getConnection());
				boolean result = itemDao.insertItem(itemModel);
				if (result) {
					for (Part part : request.getParts()) {
						part.write("C:\\rvce n studies\\projects\\therollshop\\WebContent\\images\\item-images\\"
								+ fileName);
					}
					response.setContentType("text/html");
					out.println("<script type = \"text/javascript\">");
					out.println("alert(\"Item added successfully!\")");
					out.println("window.location.href = 'http://localhost:8081/therollshop/additems.jsp'");
					out.println("</script>");
				} else {
					response.setContentType("text/html");
					out.println("<script type = \"text/javascript\">");
					out.println("alert(\"Failed to add item!\")");
					out.println("window.location.href = 'http://localhost:8081/therollshop/additems.jsp'");
					out.println("</script>");
				}
			} else {
				response.sendRedirect("admin.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
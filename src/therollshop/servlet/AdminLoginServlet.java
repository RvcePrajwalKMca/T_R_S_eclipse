package therollshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import therollshop.connection.DbCon;
import therollshop.dao.AdminDao;
import therollshop.model.Admin;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String admin_id = request.getParameter("admin-id");
			String password = request.getParameter("admin-password");
			try {
				AdminDao adao = new AdminDao(DbCon.getConnection());
				Admin admin = adao.adminLogin(admin_id, password);
				if (admin != null) {
					request.getSession().setAttribute("authadmin", admin);
					response.sendRedirect("additems.jsp");
				} else {
					response.setContentType("text/html");
					out.println("<script type = \"text/javascript\">");
					out.println("alert(\"Invalid Admin Id or Password!\")");
					out.println("window.location.href = 'http://localhost:8081/therollshop/admin.jsp'");
					out.println("</script>");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}
	}

}

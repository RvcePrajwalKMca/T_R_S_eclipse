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
import therollshop.dao.UserDao;
import therollshop.model.User;

@WebServlet("/user-register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String name = request.getParameter("register-name");
			String email = request.getParameter("register-email");
			String password = request.getParameter("register-password");
			try {
				UserDao udao = new UserDao(DbCon.getConnection());
				User user = udao.userRegister(name, email, password);
				if (user != null) {
					request.getSession().setAttribute("auth", user);
					response.sendRedirect("index.jsp");
				} else {
					response.setContentType("text/html");
					out.println("<script type = \"text/javascript\">");
					out.println("alert(\"User email already present!\")");
					out.println("window.location.href = 'http://localhost:8081/therollshop/register.jsp'");
					out.println("</script>");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}
	}

}

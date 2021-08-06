package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Student;
import model.Admin;
import dao.StudentDAO;
import dao.AdminDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studdao;
	private AdminDAO admindao;
    HttpSession session;
    
    public LoginServlet() {
        super();
        studdao = new StudentDAO();
        admindao = new AdminDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String userpassword = request.getParameter("userpassword");
		String usertype = request.getParameter("usertype");
		
		if(usertype.equals("Student")) {
			Student studinfo = new Student();
			
			studinfo.setStudid(Integer.parseInt(userid));
			studinfo.setStudpassword(userpassword);
			
			String loginstatus = studdao.loginstudent(studinfo);
			if(loginstatus.equals("success")) {
				session = request.getSession(true);
				session.setAttribute("session_id", userid);
				
				response.sendRedirect("StudentRedirectServlet?action=info");
			}
			else {
				request.setAttribute("status", loginstatus);
				RequestDispatcher view = request.getRequestDispatcher("index.jsp.jsp");
				view.forward(request, response);
			}
		}
		else if(usertype.equals("Admin")) {
			Admin admininfo = new Admin();
			
			admininfo.setAdminid(Integer.parseInt(userid));
			admininfo.setAdminpassword(userpassword);
			
			String loginstatus = admindao.loginadmin(admininfo);
			if(loginstatus.equals("success")) {
				session = request.getSession(true);
				session.setAttribute("session_id", userid);

				response.sendRedirect("AdminRedirectServlet?action=student");
			}
			else {
				request.setAttribute("status", loginstatus);
				RequestDispatcher view = request.getRequestDispatcher("Admin_login.jsp");
				view.forward(request, response);
			}
		}
	}
}
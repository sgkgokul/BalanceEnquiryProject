package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.authService;

@WebServlet("/auth")
public class authController extends HttpServlet{

	public void service(HttpServletRequest reqObj, HttpServletResponse resObj) throws ServletException, IOException {
	
		HttpSession se = reqObj.getSession();
		PrintWriter obj = resObj.getWriter();
		String str = reqObj.getParameter("auth");
		String top = (String) se.getAttribute("key");
		String code = authService.getTOTPCode((String)se.getAttribute("key"));
		if(str.equals(code)) {
			se.setAttribute("status", "active");
			resObj.sendRedirect("validuser.jsp");
		}
		else {
		obj.println("<html><body>");
		obj.println("<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Google Authentication Failed</h1>");
		obj.println("<h3 style= 'text-align:center;'><a href='login' style='text-decoration: none;'>Click here to Login</a></h3>");
		obj.println("<h3 style= 'text-align:center;'><a href='index.html' style=' text-decoration: none;'>If you dont have Account Click here to Register </a></h3>");
		obj.print("</body></html>");
		}
	}
}

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import service.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/welcome")
public class welcomeServlet extends HttpServlet{

	public void service(HttpServletRequest reqObj, HttpServletResponse resObj) throws ServletException, IOException {
			
			userService ser = new userService();
			
			PrintWriter obj = resObj.getWriter();
			
			if(ser.checkuser(reqObj.getParameter("num"),reqObj.getParameter("pass"))) {
				
				HttpSession se =reqObj.getSession();
			
				se.setAttribute("id", reqObj.getParameter("num"));
				
				String key =authService.checkKey(reqObj.getParameter("num"));
				se.setAttribute("key", key);
				String name = reqObj.getParameter("num");
				String sr = "https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/"+name+"?secret="+key+"&chs=160x160&chld=L|0";
				se.setAttribute("src", sr);
				resObj.sendRedirect("googleAuth.jsp");
			}
			else {
				obj.println("<html><body>");
				obj.println("<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Invalid User Details</h1>");
				obj.println("<h3 style= 'text-align:center;'><a href='login' style='text-decoration: none;'>Click here to Login</a></h3>");
				obj.println("<h3 style= 'text-align:center;'><a href='index.html' style=' text-decoration: none;'>If you dont have Account Click here to Register </a></h3>");
				obj.print("</body></html>");
			}
	}
}
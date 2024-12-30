package controller;

import java.io.IOException;
import java.io.PrintWriter;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class loginServlet extends HttpServlet{

	public void service(HttpServletRequest reqObj, HttpServletResponse resObj) throws ServletException, IOException {
		PrintWriter obj = resObj.getWriter();
		obj.println("<html><body>");
		obj.println("<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Login Page </h1>");
		obj.println("<form style= text-align:center action='welcome'>");
		obj.println(" <input type=text placeholder=MobileNumber name=num style= 'width: 205px; padding: 8px;font-size: 15px;'><br><br>");
		obj.println(" <input type=password placeholder=Password name=pass style= 'width: 205px; padding: 8px;font-size: 15px;'><br><br>");
		obj.println(" <input type=submit value=Login style= 'width: 165px; padding: 8px;font-size: 15px;'>");
		obj.print("</form>");
		obj.println("<h3 style= 'text-align:center;'><a href='index.html' style=' text-decoration: none;'>If you dont have Account Click here to Register </a></h3>");
		obj.println("</body></html>");
	}
}

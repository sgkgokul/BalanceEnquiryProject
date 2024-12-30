package controller;

import java.io.IOException;
import java.io.PrintWriter;

import service.*;
import models.*;
import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class registerServlet extends HttpServlet{

	public void service(HttpServletRequest reqObj, HttpServletResponse resObj) throws ServletException, IOException {
		PrintWriter obj = resObj.getWriter();
		userModel mod=new userModel();
			mod.setName(reqObj.getParameter("name"));
			mod.setLname(reqObj.getParameter("lname"));
			mod.setMobNum(reqObj.getParameter("mob"));
			mod.setPass(reqObj.getParameter("pass"));
		userService ser = new userService();
		if(ser.setservice(mod)) {
			obj.println("<html><body>");
			obj.println("<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Account Successfully Created </h1>");
			obj.println("<h3 style= 'text-align:center'><a href='/LoginPage/login'>Click here to Login</a></h3>");
			obj.print("</body></html>");
		}
		else {
			obj.println("<html><body>");
			obj.println("<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>The Mobile Number is Already Used!</h1>");
			obj.println("<h3 style= 'text-align:center'><a href='/LoginPage/login'>Click here to Login</a></h3>");
			obj.print("</body></html>");
		}
	}

}
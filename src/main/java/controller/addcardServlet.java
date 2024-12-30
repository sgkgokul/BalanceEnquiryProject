package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import service.*;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.cardModel;
import models.userModel;

@WebServlet("/addcard")
public class addcardServlet extends HttpServlet{

	public void service(HttpServletRequest reqObj, HttpServletResponse resObj) throws ServletException, IOException {
		
		HttpSession se = reqObj.getSession();
		
		if(se.getAttribute("status")==null)
			resObj.sendRedirect("login");
		else {
//			System.out.print("hi");
			PrintWriter out = resObj.getWriter();
			cardModel mod = new cardModel();
			
			mod.setCard(reqObj.getParameter("card"));
			mod.setAmount(Integer.parseInt(reqObj.getParameter("bal")));
			mod.setId((String) se.getAttribute("id"));
			cardService ser = new cardService();
			if(ser.createCard(mod)) {
				out.println("<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Card Successfully Added</h1>");
				out.println("<h3 style= 'text-align:center'><a href='validuser.jsp' style='text-decoration:none; color: #1a1a56;'>Home</a></h3>");
			}
			else {
				out.println("<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Card Already Exists With Same Card Name </h1>");
				out.println("<h3 style= 'text-align:center'><a href='validuser.jsp' style='text-decoration:none; color: #1a1a56;'>Home</a></h3>");
			}
			
		}
	}
}
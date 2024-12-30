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

@WebServlet("/addmoney")
public class addMoneyServlet extends HttpServlet{

	public void service(HttpServletRequest reqObj, HttpServletResponse resObj) throws ServletException, IOException {
		
		HttpSession se = reqObj.getSession();
		
		if(se.getAttribute("status")==null)
			resObj.sendRedirect("login");
		else {
			
			if(!(reqObj.getParameter("card")).isEmpty() && !(reqObj.getParameter("bal")).isEmpty()) {
				PrintWriter out = resObj.getWriter();
				cardModel mod = new cardModel();
				
				mod.setCard(reqObj.getParameter("card"));
				mod.setAmount(Integer.parseInt(reqObj.getParameter("bal")));
				mod.setId((String) se.getAttribute("id"));
				cardService ser = new cardService();
				if(ser.addMoney(mod)) {
					String stri = "RS."+reqObj.getParameter("bal") +" Successfully Added To "+reqObj.getParameter("card");
					se.setAttribute("payStatus", stri);
					}
			}
			else {
				String stri = "Payment Failed";
				se.setAttribute("payStatus", stri);
			}
			resObj.sendRedirect("paymentView.jsp");
			
		}
	}
}
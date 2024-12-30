package controller;

import java.io.IOException;
import java.io.PrintWriter;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.*;
import service.*;


@WebServlet("/purchase1")
public class purchaseServlet extends HttpServlet{

	public void service(HttpServletRequest reqObj, HttpServletResponse resObj) throws ServletException, IOException {
	
		HttpSession se = reqObj.getSession();
		
		if(se.getAttribute("status")==null)
			resObj.sendRedirect("login");
		else {
			if(!(reqObj.getParameter("card").isEmpty()) && !(reqObj.getParameter("item").isEmpty()) && !(reqObj.getParameter("bal").isEmpty())) {
				transactionModel mod = new transactionModel();
				PrintWriter out = resObj.getWriter();
				mod.setId((String)se.getAttribute("id"));
				mod.setCard(reqObj.getParameter("card"));
				mod.setItem(reqObj.getParameter("item"));
				mod.setAmount(Integer.parseInt(reqObj.getParameter("bal")));
				transactionService ser = new transactionService();
				if(ser.purchase(mod)) {
					String stri = " Successfully Spended for "+ reqObj.getParameter("item")+"( Rs."+reqObj.getParameter("bal")+" )"+" using  "+reqObj.getParameter("card");
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
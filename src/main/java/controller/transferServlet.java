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


@WebServlet("/transfer1")
public class transferServlet extends HttpServlet{

	public void service(HttpServletRequest reqObj, HttpServletResponse resObj) throws ServletException, IOException {
	
		HttpSession se = reqObj.getSession();
		
		if(se.getAttribute("status")==null)
			resObj.sendRedirect("login");
		else {
			if(!(reqObj.getParameter("toac").isEmpty()) && !(reqObj.getParameter("bal").isEmpty()) && !(reqObj.getParameter("card").isEmpty())) {
				System.out.println("wht");
				transactionModel mod = new transactionModel();
				PrintWriter out = resObj.getWriter();
				mod.setId((String)se.getAttribute("id"));
				mod.setCard(reqObj.getParameter("card"));
				String str = "Transferd to "+reqObj.getParameter("toac"); 
				mod.setItem(str);
				mod.setAmount(Integer.parseInt(reqObj.getParameter("bal")));
				transactionService ser = new transactionService();
				if(ser.transferamo(mod,reqObj.getParameter("toac"))) {
					String stri = "Rs."+reqObj.getParameter("bal")+" Transfered Successfully From "+reqObj.getParameter("card")+ " to "+reqObj.getParameter("toac");
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
package controller;

import java.io.IOException;
//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/logout")
public class logoutServlet extends HttpServlet{

	public void service(HttpServletRequest reqObj, HttpServletResponse resObj) throws ServletException, IOException {
		
		HttpSession sess = reqObj.getSession();
		
		sess.removeAttribute("status");
		sess.removeAttribute("num");
		sess.invalidate();
		resObj.sendRedirect("login");
	}
}
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String str = (String) request.getParameter("choice");
	
	if(str.equals("transfer")){
		RequestDispatcher rd = request.getRequestDispatcher("transfer1");
		rd.forward(request, response);
	}
	else if(str.equals("spend")){
		RequestDispatcher rd = request.getRequestDispatcher("purchase1");
		rd.forward(request, response);
	}
	else{
		RequestDispatcher rd = request.getRequestDispatcher("addmoney");
		rd.forward(request, response);
	}
	%>
</body>
</html>
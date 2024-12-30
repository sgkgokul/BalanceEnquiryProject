<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="service.*" %>
<%@ page import ="java.util.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%response.setContentType("application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
response.setHeader("Content-Disposition", "attachment;fileName=history.xlsx;"); %>
<table>
	<tr>
		<th>Account</th>
		<th>Purpose</th>
		<th>Amount</th>
	</tr>
	<%
	if(session.getAttribute("status")==null)
		response.sendRedirect("login");
	String stri = (String) session.getAttribute("id");
	transactionService ser = new transactionService();
	List<String> cards = ser.history(stri);
	
	for(String str: cards){
		String s[]=str.split("[+]");
		String st="<tr> <td> " + s[1] + "</td> <td> " + s[0]+"</td>";
		out.println(st);
		if(Integer.parseInt(s[2])<0){
			out.print("<td>"+s[2]+"</td></tr>");
		}
		else{
			out.print("<td> +"+s[2]+"</td></tr>");
		}
	}
		
	%>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "service.*" %>
<%@ page import ="java.util.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	h3,h2{
		text-align:center;
	}
	a{
		text-decoration:none;
		color: #1a1a56;
	}
	#f1{
		display : flex;
	}
	#di1{
		flex :50%;
		margin-left:175px;
	}
	#di2{
		flex :50%;
		margin-right:175px;
	}
	input{
		width: 205px; padding: 8px;font-size: 15px;
	}
	th,td{
		padding: 12px;
    	border: 1px solid #948d8d;
    	font-size: 20px;
	}
	th{
		text-align:center;
	}
	table{
		margin-left: auto;
    	margin-right: auto;
    	border-collapse: collapse;
	}
</style>
</head>
<body>
	<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Cards Management</h1>
		<%
		if(session.getAttribute("status")==null)
			response.sendRedirect("login");
		else{
			String str = (String) session.getAttribute("id");
			cardService ser = new cardService();
			List<String> cards = ser.cardDetails(str);
			%>
			<div id='f1'> <div id='di1'> <h2>Available Cards</h2>
			<table>
			<tr> <th> Card Name </th> <th>Avl Amount </th> </tr>
			<%
			for(String st: cards){
				String arr[] = st.split("[+]");
				String s = "<tr> <td>"+arr[0]+"</td> <td>"+arr[1]+"</td><tr>";
				out.println(s);
			}
		}
			%>
			</table> </div>

		<div id="di2">
			<h2>Add Card</h2>
			<form style= text-align:center action='addcard'>
				<input type=text placeholder=CardName name=card required="required" ><br><br>
				<input type=text placeholder='Avl Balance' pattern="[0-9]+" required="required"  name=bal ><br><br>
				<input type=submit value='Add Card' >
			</form>
			<h3><a href="validuser.jsp">Home</a></h3>
			<h3><a href="logout">Logout</a></h3>
		</div>
		</div>
</body>
</html>
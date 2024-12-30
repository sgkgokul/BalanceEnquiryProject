<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*" %>
<%@ page import = "service.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
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
	h3{
		text-align:center;
	}
	h3:hover {
  		cursor: pointer;
	}
	a{
		text-decoration:none;
		color: #1a1a56;
	}
	#t1{
		text-align:right;
	}
</style>
</head>
<body>
	<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Transaction History</h1>
	<table id="tab">
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
			out.print("<td id='t1'>"+s[2]+"</td></tr>");
		}
		else{
			out.print("<td id='t1'> +"+s[2]+"</td></tr>");
		}
	}
		
	%>
	</table>
	<h3><a href="xlservice.jsp"> Download XL </a></h3>
	<h3 id="h3" > Download Pdf </h3>
	<h3><a href="validuser.jsp">Home</a></h3>
	<h3><a href="logout">Logout</a></h3>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
	<script src= "https://unpkg.com/jspdf-autotable"></script>
	<script type="text/javascript">
	let el = document.getElementById("h3");
	el.addEventListener("click",function(){
		window.jsPDF = window.jspdf.jsPDF;
		let doc = new jsPDF();
		doc.text("Transaction History",75,10);
		doc.autoTable({html: '#tab'});
		doc.save("outut.pdf");
	});
	</script>
</body>

</html>
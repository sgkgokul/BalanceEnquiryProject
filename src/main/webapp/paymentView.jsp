
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
	input{
	width: 225px; padding: 8px;font-size: 15px; display:block; margin-top:20px; margin-right: auto;
    margin-left: auto;
	}
	select{
	width: 247px; padding: 8px;font-size: 15px; display:block; margin-top:20px; margin-right: auto;
    margin-left: auto;
	}
	h3{
		text-align:center;
	}
	a{
		text-decoration:none;
		color: #1a1a56;
	}
</style>
</head>
<body>
	<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Payments Page</h1>
	<%
		if(session.getAttribute("status")==null)
			response.sendRedirect("login");

		String str = (String) session.getAttribute("id");
		cardService ser = new cardService();
		List<String> cards = ser.cardDetails(str);
	%>
	<form style= text-align:center action='choice.jsp' id="f1">
	<select name=choice id="s1">
	<option value="null">--Select Service--</option>
	<option value="spend"> Spend Money </option>
	<option value="transfer"> Self Transfer </option>
	<option value="addmoney"> Add Money </option></select>
	<select name="card" >
	<option value=''>--Select Card--</option>
	<%
	for(String st: cards){
			String arr[]=st.split("[+]");
			String s="<option value=" + arr[0] + ">" + arr[0] + " (Avl Rs."+arr[1]+")</option>";
			out.println(s);
		}
	%>
	</select>
	<input name="item" type="text" placeholder="Item" id="it">
	<select name="toac" id="sl1">
	<option value=''>--To Ac--</option>
	<%
	for(String st: cards){
			String arr[]=st.split("[+]");
			String s="<option value=" + arr[0] + ">" + arr[0] + " (Avl Rs."+arr[1]+")</option>";
			out.println(s);
		}
	%>
	</select>
	<input name="bal" type="text" placeholder="Amount">
	<input type="submit" id="su1">
	</form>
	<%
	if(session.getAttribute("payStatus")!=null){
		String pas =(String) session.getAttribute("payStatus");
	%>
		<h3><%= pas %></h3>
	<%
		session.removeAttribute("payStatus");
	}
	%>
	<h3><a href="validuser.jsp">Home</a></h3>
	<h3><a href="logout">Logout</a></h3>
	<script type="text/javascript" >
	console.log("hi");
		let sel = document.getElementById('s1');
		document.getElementById("su1").style.display = "none";
		sel.addEventListener('change',function(){
			if(this.value!="null")
				document.getElementById("su1").style.display = "block";
			else
				document.getElementById("su1").style.display = "none";
			let val = this.value;
			if(val=="transfer"){
				document.getElementById("it").style.display = "none";
				document.getElementById("sl1").style.display = "block";
			}
			else if(val=="spend"){
				document.getElementById("it").style.display = "block";
				document.getElementById("sl1").style.display = "none";
			}
			else if(val=="addmoney"){
				document.getElementById("it").style.display = "none";
				document.getElementById("sl1").style.display = "none";
			}
		});
	</script>
			
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	img{
		display:block;
		margin-right:auto;
		margin-left:auto;
	}
	form{
		text-align:center;
	}
	input{
		padding: 6px;
    	width: 175px;
	}
</style>
</head>
<body>
<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Google Authentication</h1>
<form action="auth">
<%  
	String str = (String) session.getAttribute("src");
%>
<img src=<%=str %>>
<input type="text" name="auth" placeholder="Enter No"><br><br>
<input type="submit">
</form>

</body>
</html>
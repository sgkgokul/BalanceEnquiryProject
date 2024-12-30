<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "service.*" %>
<%@ page import ="java.util.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"></script>
<style>
	ul{
		text-align:center;
		margin-top:60px;
	}
	.a1{
    text-decoration: none;
    color: black;
    }
    .li1{
    display: inline;
    font-style: italic;
    font-size: 30px;
    padding: 10px 50px;
    margin: 0 8px;
    border-bottom: 2px solid #ccc0c0;
    background-color: #e4dddd;
	}
	a{
		text-decoration:none;
		color: #1a1a56;
	}
	h3{
		text-align:center;
		margin-top: 70px;
    	font-size: 23px;
	}
}
</style>
</head>
<body>
	<%
		if(session.getAttribute("status")==null)
			response.sendRedirect("login");
		String str = (String) session.getAttribute("id");
		purchaseService ser = new purchaseService();
		List<String> purchasehistory = ser.purchaseDetails(str);
		List<String> custom = new ArrayList<String>();
		for(int i=0;i<purchasehistory.size();i++){
			if(i==8){
				int amo=0;
				for(int j=i;j<purchasehistory.size();j++){
					String s = purchasehistory.get(j);
					String a[] = s.split("[+]");
					amo=amo+Integer.parseInt(a[1]);
				}
				custom.add("Others +"+"'"+amo+"'"); 
				break;
			}
			else 
				custom.add(purchasehistory.get(i));
		}
	%>
	<h1 style= 'text-align:center;margin-top:50px;padding-top:25px;padding-bottom:25px; background-color:#1f7b7b1a'>Welcome to the Apllication</h1>
	<ul>
		<a href="cardManagementview.jsp" class="a1">
			<li class="li1">Cards Management</li>
		</a>
		<a href="paymentView.jsp" class="a1">
			<li class="li1">Payments</li>
		</a>
		<a href="transactionview.jsp" class="a1">
			<li class="li1">Transaction History</li>
		</a>
		<a href="logout" class="a1">
			<li class="li1">Logout</li>
		</a>
	</ul><br><br>
	<div style="width:400px; margin-left: auto; margin-right: auto;">
		<h2 style='text-align:center'>Purchase Summary</h2>
		<canvas id="myChart"></canvas>
	</div>
	<script>
		let ctx = document.getElementById('myChart').getContext('2d');
		let colorArray=['#03d5ff','#ff0000','#8903ff','#f7b0ee','#03ff65','#fffc03','#ea964c','#0340ff','#7c7d80'];
		console.log(colorArray[0]);
		let labels = [];
		let data = [];
		<%
			for(String st: custom){
				String arr[] = st.split("[+]");
				String s="'"+arr[0]+"'";
				String a=arr[1];
		%>
		labels.push((<%= s%>));
		data.push(<%= a%>);
		<% }%>
		let myChart = new Chart(ctx, {
    	type: 'pie',
    	data: {
        labels: labels,
        datasets: [{
            label:'Summary',
            data: data,
            backgroundColor: [
            	<% 
            		for(int i=0;i<custom.size();i++){
            			
 				%>
            	colorArray[<%= i%>],
            	<%
            		}
            	%>
                
            ]
        	}]
    	},
    	options: {
    		legend:{
        		display:true,
        		position:"right"
        	}
    	}
		});
	</script>
	
	
</body>
</html>
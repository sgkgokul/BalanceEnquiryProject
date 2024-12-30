package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.cardModel;

public class purchaseService {
	String url ="jdbc:mysql://localhost:3306/userinfo";
	String usr="root";
	String pasw="1234";
	
	public List<String> purchaseDetails(String str) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			}
			catch(Exception e) {
				System.out.println("Class Not Found "+e);
				return null;
			}
			try {
				Connection con = DriverManager.getConnection(url, usr, pasw);
				Statement st=con.createStatement();
				String stri="select * from purchase where id="+"'"+str+"' order by amount desc";
				ResultSet rs=st.executeQuery(stri);
				List<String> li=new ArrayList<String>();  
				while(rs.next()) {
//					System.out.println(rs.getString(2)+" "+rs.getString(3));
					String ob=rs.getString(3)+"+"+rs.getString(2);
					li.add(ob);
				}
				return li;
			}
			catch(Exception e) {
				System.out.println("Connection failed due to "+e);
				return null;
			}
	}
}
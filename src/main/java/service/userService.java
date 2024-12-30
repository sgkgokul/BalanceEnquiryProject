package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import models.*;

public class userService{
	
	String url ="jdbc:mysql://localhost:3306/userinfo";
	String usr="root";
	String pasw="1234";
	public Boolean setservice(userModel mod) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			}
			catch(Exception e) {
				System.out.println("Class Not Found "+e);
				return false;
			}
			try {
				Connection con = DriverManager.getConnection(url, usr, pasw);
				Statement st=con.createStatement();
				String stri="select exists(select * from info where mobNum="+"'"+mod.getMobNum()+"'"+")";
				ResultSet rs=st.executeQuery(stri);
				rs.next();
				int a=rs.getInt(1);
				if(a==0) {
					String str="insert into info values("+
							"'"+mod.getName()+"'"+","+"'"+mod.getLname()+"'"+","+"'"+mod.getMobNum()+"'"+","+"'"+mod.getPass()+"'"+")";
					st.executeUpdate(str);
					st.close();
					return true;
				}
				else
					return false;
			}
			catch(Exception e) {
				System.out.println("Connection failed due to "+e);
				return false;
			}
	}
	public boolean checkuser(String num,String pw) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			}
			catch(Exception e) {
				System.out.println("Class Not Found "+e);
				return false;
			}
			try {
				Connection con = DriverManager.getConnection(url, usr, pasw);
				Statement st=con.createStatement();
				String str = "select exists(select * from info where mobNum="+"'"+num+"'"+")";
				ResultSet rs= st.executeQuery(str);
				rs.next();
				int a=rs.getInt(1);
				if(a==1) {
					String stri = "select passw from info where mobNum="+"'"+num+"'";
					rs=st.executeQuery(stri);
					rs.next();
					if(rs.getString(1).equals(pw)) {
						st.close();
						return true;
					}
					else {
						st.close();
						return false;
					}
				}
				else {
					return false;
				}
			}
			catch(Exception e) {
				System.out.println("Connection failed due to "+e);
				return false;
			}
	}

}
package service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.*;

public class transactionService {
	
	static String url ="jdbc:mysql://localhost:3306/userinfo";
	static String usr="root";
	static String pasw="1234";
	public static boolean purchase(transactionModel mod) {
		
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
				String str = "insert into transaction values("+"'"+mod.getId()+"'"+","+"'"+mod.getCard()+"'"+","+-1*mod.getAmount()+","+"'"+mod.getItem()+"'"+")";
				st.executeUpdate(str);
				str="select balance from cards where id="+"'"+mod.getId()+"'"+"and "+ "card="+"'"+mod.getCard()+"'";
				ResultSet rs = st.executeQuery(str);
				rs.next();
				int am=rs.getInt(1);
				int avl = am-mod.getAmount();
				str="update cards set balance ="+avl+ " where id="+"'"+mod.getId()+"'"+ " and"+ " card= "+"'"+mod.getCard()+"'";
				st.executeUpdate(str);
				str = "select exists(select * from purchase where id="+"'"+mod.getId()+"'"+"and item="+"'"+mod.getItem()+"'"+")";
				rs= st.executeQuery(str);
				rs.next();
				int a=rs.getInt(1);
				if(a==1) {
					str="select amount from purchase where id="+"'"+mod.getId()+"'"+"and item="+"'"+mod.getItem()+"'";
					rs=st.executeQuery(str);
					rs.next();
					am=rs.getInt(1);
					avl=am+mod.getAmount();
					str="update purchase set amount="+avl +" where id="+"'"+mod.getId()+"'"+"and item="+"'"+mod.getItem()+"'";
					st.executeUpdate(str);
				}
				else {
					str="insert into purchase values("+"'"+mod.getId()+"',"+mod.getAmount()+",'"+mod.getItem()+"')";
					st.executeUpdate(str);
					
				}
				st.close();
				return true;
			}
			catch(Exception e) {
				System.out.println("Connection failed due to "+e);
				return false;
			}
	}
	public boolean transferamo(transactionModel mod,String toac) {
		
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
					String str = "insert into transaction values("+"'"+mod.getId()+"'"+","+"'"+mod.getCard()+"'"+","+-1*mod.getAmount()+","+"'"+mod.getItem()+"'"+")";
					st.executeUpdate(str);
					str="select balance from cards where id="+"'"+mod.getId()+"'"+"and "+ "card="+"'"+mod.getCard()+"'";
					ResultSet rs = st.executeQuery(str);
					rs.next();
					int am=rs.getInt(1);
					int avl = am-mod.getAmount();
					str="update cards set balance ="+avl+ " where id="+"'"+mod.getId()+"'"+ " and"+ " card= "+"'"+mod.getCard()+"'";
					st.executeUpdate(str);
					str="select balance from cards where id="+"'"+mod.getId()+"'"+"and "+ "card="+"'"+toac+"'";
					rs = st.executeQuery(str);
					rs.next();
					am=rs.getInt(1);
					avl = am+mod.getAmount();
					str="update cards set balance ="+avl+ " where id="+"'"+mod.getId()+"'"+ " and"+ " card= "+"'"+toac+"'";
//					System.out.println(str);
					st.executeUpdate(str);
					str = "insert into transaction values("+"'"+mod.getId()+"'"+","+"'"+toac+"'"+","+mod.getAmount()+","+"'"+"Credited From "+mod.getCard()+"'"+")";
//					System.out.println(str);
					st.executeUpdate(str);
					st.close();
					return true;
					
				}
				catch(Exception e) {
					System.out.println("Connection failed due to "+e);
					return false;
				}
	}
	public List<String> history(String id) {
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
				String stri="select * from transaction where id="+"'"+id+"'";
				ResultSet rs=st.executeQuery(stri);
				List<String> li=new ArrayList<String>();  
				while(rs.next()) {
//					System.out.println(rs.getString(2)+" "+rs.getString(3));
					String t = String.valueOf(rs.getInt(3));
					String ob=rs.getString(4)+"+"+rs.getString(2)+"+"+t;
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

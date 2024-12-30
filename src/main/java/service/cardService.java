package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.cardModel;

public class cardService {
	String url ="jdbc:mysql://localhost:3306/userinfo";
	String usr="root";
	String pasw="1234";
	public boolean createCard(cardModel mod) {
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
				String stri = "select exists(select card from cards where card="+"'"+mod.getCard()+"'"+"and"+" id="+"'"+mod.getId()+"'"+")";
//				System.out.print(stri);
				ResultSet rs= st.executeQuery(stri);
				rs.next();
				int a=rs.getInt(1);
				System.out.println(a);
				if(a==0) {
					String str="insert into cards values("+"'"+mod.getId()+"'"+","+"'"+mod.getCard()+"'"+","+"'"+mod.getAmount()+"'"+")";
					st.executeUpdate(str);
					st.close();
					return true;
				}
				else {
					st.close();
					return false;
				}
			}
			catch(Exception e) {
				System.out.println("Connection failed due to "+e);
				return false;
			}
	}
	public List<String> cardDetails(String str) {
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
				String stri="select * from cards where id="+"'"+str+"'";
				ResultSet rs=st.executeQuery(stri);
				List<String> li=new ArrayList<String>();  
				while(rs.next()) {
//					System.out.println(rs.getString(2)+" "+rs.getString(3));
					String ob=rs.getString(2)+"+"+rs.getString(3);
					li.add(ob);
				}
				return li;
			}
			catch(Exception e) {
				System.out.println("Connection failed due to "+e);
				return null;
			}
	}
	
	public boolean addMoney(cardModel mod) {
		
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
				String str="select balance from cards where id="+"'"+mod.getId()+"'"+"and "+ "card="+"'"+mod.getCard()+"'";
				ResultSet rs = st.executeQuery(str);
				rs.next();
				int am=rs.getInt(1);
				int avl = am+mod.getAmount();
				str="update cards set balance ="+avl+ " where id="+"'"+mod.getId()+"'"+ " and"+ " card= "+"'"+mod.getCard()+"'";
				st.executeUpdate(str);
				str = "insert into transaction values("+"'"+mod.getId()+"'"+","+"'"+mod.getCard()+"'"+","+mod.getAmount()+","+"'"+"Credited From "+"admin"+"'"+")";
//				System.out.println(str);
				st.executeUpdate(str);
				st.close();
				return true;
			}
			catch(Exception e) {
				System.out.println("Connection failed due to "+e);
				return false;
			}
			
	}

}

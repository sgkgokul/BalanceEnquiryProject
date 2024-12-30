package service;

import java.io.*;
import java.security.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.*;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;

import de.taimos.totp.TOTP;

public class authService{
	static String url ="jdbc:mysql://localhost:3306/userinfo";
	static String usr="root";
	static String pasw="1234";
        public static String generateSecretKey() {
                SecureRandom random = new SecureRandom();
                byte[] bytes = new byte[20];
                random.nextBytes(bytes);
                Base32 base32 = new Base32();
                return base32.encodeToString(bytes);
        }
        public static String getTOTPCode(String secretKey) {
            Base32 base32 = new Base32();
            byte[] bytes = base32.decode(secretKey);
            String hexKey = Hex.encodeHexString(bytes);
            return TOTP.getOTP(hexKey);
        
     }
        
        public static String checkKey(String id) {
        	try {
    			Class.forName("com.mysql.jdbc.Driver");
    			}
    			catch(Exception e) {
    				System.out.println("Class Not Found "+e);
    			}
    			try {
    				Connection con = DriverManager.getConnection(url, usr, pasw);
    				Statement st=con.createStatement();
    				String stri = "select sckey from info where mobNum='"+id+"'";
    				ResultSet rs= st.executeQuery(stri);
    				rs.next();
    				String s=rs.getString(1);
    				if(s==null) {
    					String key =authService.generateSecretKey();
    					stri = "update info set sckey='"+key +"' where mobNum='"+id+"'";
    					st.executeUpdate(stri);
    					st.close();
    					return key;
    				}
    				else {
    					st.close();
    					return s;
    				}
    			}
    			catch(Exception e) {
    				System.out.println("Connection failed due to "+e);
    				return null;
    			}
        }
    
 }
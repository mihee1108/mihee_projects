package mp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBConnector {
	public static void disConnect(ResultSet rs, PreparedStatement st, Connection con){
		try {
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void disConnect(PreparedStatement st, Connection con){
		try {
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnect(){
		Connection con = null;
				
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/choa");
			con = ds.getConnection();		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
		//1. 4가지 정보
		
		String user="user03";
		String password="user03";
		String url ="jdbc:oracle:thin:@192.168.40.128:1521:xe";
		String driver="oracle.jdbc.driver.OracleDriver";
	
		//2. driver를 메모리에 로딩
		//3. Connection
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return con;
	}


}

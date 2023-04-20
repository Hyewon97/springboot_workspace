package map.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GetAddress {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String a="";
	ArrayList<String> addr= null;
	public GetAddress() {
		try {
			String OracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";
			String OracleUser = "dodo";
			String OraclePasswd = "a1234";
			String sql="SELECT * FROM user_data";
			addr=new ArrayList<String>();
			con = DriverManager.getConnection(OracleUrl, OracleUser, OraclePasswd);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			ResultSetMetaData rsmd=rs.getMetaData();
			System.out.println(rsmd.getColumnCount());
			while(rs.next()) {
				addr.add(rs.getString("user_address").substring(6));
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		}
	}

	public ArrayList<String> GetAdr() {
		return addr;
	}

}
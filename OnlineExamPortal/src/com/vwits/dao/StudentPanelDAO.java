package com.vwits.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vwits.util.MyDatabaseConnection;

public class StudentPanelDAO {
	
	public int changePassword(String username,String oldpassword,String newpassword) {
		Connection con;
		int row = 0;
		
		try {
			con = MyDatabaseConnection.connectToDatabase();
			String query = "SELECT PASSWORD FROM EXAMIFYUSERS WHERE USERNAME = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String dbpwd = rs.getString(1);
				if(dbpwd.equals(oldpassword)) {
					String updatePasswordQuery = "UPDATE EXAMIFYUSERS SET PASSWORD = ? WHERE USERNAME = ?";
					PreparedStatement ps2 = con.prepareStatement(updatePasswordQuery);
					ps2.setString(1, newpassword);
					ps2.setString(2, username);
					row = ps2.executeUpdate();
					return row;
				}
			}
			else {
				row = -1;
				return row;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

}

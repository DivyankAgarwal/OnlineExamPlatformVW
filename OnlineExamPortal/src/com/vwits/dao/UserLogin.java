package com.vwits.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vwits.util.MyDatabaseConnection;

public class UserLogin {
	Connection con;
	String name = null;

	public String fetchNameOfUser(String username) {
		con = MyDatabaseConnection.connectToDatabase();
		String query = "SELECT NAME FROM EXAMIFYUSERS WHERE USERNAME=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString(1);
					return name;

			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	public int fetchIDOfUser(String username) {
		con = MyDatabaseConnection.connectToDatabase();
		String query = "SELECT ID FROM EXAMIFYUSERS WHERE USERNAME = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				return id;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public String userLogin(String username, String password, boolean isTeacher) {
		con = MyDatabaseConnection.connectToDatabase();
		boolean isTeacherVerified = false;
		String query = "SELECT USERNAME,PASSWORD,ISTEACHER FROM EXAMIFYUSERS WHERE USERNAME=? AND PASSWORD=?";
		try {
			if (isTeacher) {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					String dbuser = rs.getString(1);
					String dbpwd = rs.getString(2);
					String userType = rs.getString(3);
					System.out.println(userType);
					if (dbuser.equals(username) && dbpwd.equals(password) && userType.equals("Y")) {
						isTeacherVerified = true;
						return "T";
	
					} else if (dbuser.equals(username) && dbpwd.equals(password) && !userType.equals("Y")) {
						return "NT";
					}
				}else {
					return "INVALID";
				}
			} else {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					String dbuser = rs.getString(1);
					String dbpwd = rs.getString(2);
					String userType = rs.getString(3);
					if (dbuser.equals(username) && dbpwd.equals(password) && userType.equals("N")) {
						return "S";
	
					} else if( dbuser.equals(username) && dbpwd.equals(password) && !userType.equals("N") ) {
						return "NS";
					}
				}else {
					return "INVALID";
				}
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

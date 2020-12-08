package com.vwits.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.vwits.model.User;
import com.vwits.util.MyDatabaseConnection;

public class UserRegisterDAO {
	
	Connection con;
	
	public String registerUser(User user) {
		String msg = null;
		int row = 0;
		try {
			con = MyDatabaseConnection.connectToDatabase();
			String query = "INSERT INTO EXAMIFYUSERS VALUES(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, String.valueOf(user.getIsTeacherOption()));
			row = ps.executeUpdate();
			if(row != 0) {
				msg = "S";
				return msg;
			}else {
				msg = "F";
				return msg;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		msg = "Error";
		return msg;
	}

}

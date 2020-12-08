package com.vwits.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.vwits.model.User;
import com.vwits.util.MyDatabaseConnection;

public class AdminPanelDAO {
	
	public Map<Integer,String> getAllStudents(){
		//List<String> studentName = new ArrayList<String>();
		Map<Integer, String> students = new TreeMap<>();
		Connection con;
		try {
			con = MyDatabaseConnection.connectToDatabase();
			String query = "SELECT ID,NAME FROM EXAMIFYUSERS WHERE ISTEACHER = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "N");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				students.put(rs.getInt(1),rs.getString(2));
				//System.out.println(students);
			}
			//System.out.println(students);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return students;
		
	}
	public ResultSet getAllStudentMarks(){
		Connection con;
		ResultSet rs = null;
		try {
			con = MyDatabaseConnection.connectToDatabase();
			String query = "SELECT examifyusers.name,examifyusers.id,examifyuserresult.score FROM examifyusers INNER JOIN examifyuserresult ON examifyusers.id=examifyuserresult.id";
			PreparedStatement ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
//	public static void main(String[] args) {
//		AdminPanelDAO obj = new AdminPanelDAO();
//		obj.getAllStudentMarks();
//	}
	
}

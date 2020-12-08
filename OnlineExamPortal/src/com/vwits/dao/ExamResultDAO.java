package com.vwits.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.vwits.model.Exam;
import com.vwits.util.MyDatabaseConnection;

public class ExamResultDAO {
	
	public int storeResult(Exam e) {
		Connection con;
		boolean scoreInsertSuccessful = false;
		boolean scoreUpdate = false;
		int row = 0;
		 try {
			 con = MyDatabaseConnection.connectToDatabase();
			 String checkResultQuery = "SELECT SCORE FROM EXAMIFYUSERRESULT WHERE ID = ?";
			 PreparedStatement ps = con.prepareStatement(checkResultQuery);
			 ps.setInt(1, e.getId());
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()) {
				 if(rs.getString(1) == null) {
					 PreparedStatement insertScore = con.prepareStatement("INSERT INTO EXAMIFYUSERRESULT(SCORE) VALUES(?)");
					 insertScore.setString(1, e.getScore());
					 scoreInsertSuccessful = insertScore.execute();
					 
				 } else if(rs.getString(1) != null) {
					 PreparedStatement updateScore = con.prepareStatement("UPDATE EXAMIFYUSERRESULT SET SCORE = ? WHERE ID = ?");
					 updateScore.setString(1, e.getScore());
					 updateScore.setInt(2, e.getId());
					 scoreUpdate = updateScore.execute();
					 
				 }
			 }else {
				 String insertRecordQuery = "INSERT INTO EXAMIFYUSERRESULT VALUES(?,?)";
				 PreparedStatement insertRecord = con.prepareStatement(insertRecordQuery);
				 insertRecord.setInt(1, e.getId());
				 insertRecord.setString(2, e.getScore());
				 row = insertRecord.executeUpdate();
				 return row;
				 
			 }
		 }catch(SQLException exp) {
			 exp.printStackTrace();
		 }
		 if(scoreInsertSuccessful == true) {
			 return 99;
		 }else if(scoreUpdate == true) {
			 return 999;
		 }
		return row;
	}

}

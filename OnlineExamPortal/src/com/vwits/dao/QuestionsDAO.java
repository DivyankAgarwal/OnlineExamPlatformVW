package com.vwits.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vwits.util.MyDatabaseConnection;

public class QuestionsDAO {
	List<String> correctAns = new ArrayList<String>();
	
	public ResultSet displayQuestions() {
		Connection con;
		ResultSet rs = null;
		
		try {
			con = MyDatabaseConnection.connectToDatabase();
			String query = "SELECT QUESTION,OPTA,OPTB,OPTC,OPTD,QNO FROM examifyquestions";
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(query);
			return rs;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public List<String> getCorrectAnswers(){
		Connection con;
		ResultSet rs;
		
		try {
			con = MyDatabaseConnection.connectToDatabase();
			String query = "SELECT CORRECTANS FROM examifyquestions";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				correctAns.add(rs.getString(1));
			}
			return correctAns;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return correctAns;
	}

}

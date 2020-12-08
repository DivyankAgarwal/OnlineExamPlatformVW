package com.vwits.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vwits.model.Question;
import com.vwits.util.MyDatabaseConnection;

public class NewQuestionDAO {
	
	public void insertNewQuestion(Question q) {
		Connection con;
		List<String> opts = q.getOptions();
		try {
			con = MyDatabaseConnection.connectToDatabase();
			String query = "INSERT INTO EXAMIFYQUESTIONS VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, q.getQuestion());
			int index = 2;
			for(int i=0;i<opts.size();i++) {
				ps.setString(index, opts.get(i));
				index++;
				if(index == 6)
					break;
			}
			ps.setString(6, q.getCorrectAns());
			ps.setInt(7, q.getQuestionNo());
			int row = ps.executeUpdate();
			System.out.println(row);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}

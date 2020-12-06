package server.SQL;

import server.userBets.userBet;
import java.sql.*;
import java.util.ArrayList;
import server.Team.TeamStats;

public class userBetSQL {
    private String url;
	private Connection conn;
	Statement smt;
	PreparedStatement psmt;
	ResultSet rs;
    private String database;
    
    public userBetSQL(){	
		url = "jdbc:mysql://35.238.205.120:3306/";
		try{
			conn = DriverManager.getConnection(url, "root", "root");//development
			smt = conn.createStatement();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		database = "boiler_bets";
    }

    public String insertBet(userBet betInfo) {
        try{
			String query = "insert into "+ this.database+".userBets "+ 
				"(userId, betId, amount, payout, betSuccess) "+
				"values "+ 
				"(?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(query);
			psmt.setLong(1, betInfo.getUserId());
			psmt.setLong(2, betInfo.getBetId());
			psmt.setLong(3, betInfo.getAmount());
            psmt.setDouble(4, betInfo.getPayout());
            psmt.setString(5, betInfo.getBetSuccess());
            System.out.println(query);
            psmt.executeUpdate();
			psmt.close();
            conn.close();
			return "OK";
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error inserting new user to DB.");
			return "ERROR INSERTING USERBET";
		}
    }
}

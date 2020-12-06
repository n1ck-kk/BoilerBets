package server.SQL;

import java.sql.*;
import java.util.ArrayList;
import server.Team.TeamStats;

public class betStatSQL {
	private String url;
	private Connection conn;
	Statement smt;
	PreparedStatement psmt;
	ResultSet rs;
	private String database;

	public betStatSQL(){	
		url = "jdbc:mysql://35.238.205.120:3306/";
		try{
			conn = DriverManager.getConnection(url, "root", "root");//development
			smt = conn.createStatement();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		database = "boiler_bets";
    }
    
    public String getAvailableBets() {
        try {
            psmt = conn.prepareStatement("select * from "+ this.database+".betStats");
            rs = psmt.executeQuery();
            String result = "{";
			while (rs.next())
			{
                result+= "\""+rs.getString("betId")+"\": {";
				result+= "\"TeamID\": "+rs.getInt("teamId");
				result+= ",\"StartDate\": "+rs.getString("startDate");
				result+= ",\"EndDate\": "+rs.getString("endDate");
                result+= ",\"Odds\": "+rs.getString("odds");
                result+= ",\"Type\": "+rs.getString("type");
				result+=" }, ";
			}
            result = result.substring(0, result.length()-2);
            result +=" }";
            rs.close();
			psmt.close();
            conn.close();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
			return null; //"/bet Fail";
        }
    }

        
    public String getMaxBet() {
        try {
            psmt = conn.prepareStatement("select * from "+ this.database+".betStats where odds = (SELECT max(odds) FROM betStats)");
            rs = psmt.executeQuery();
            String result = "{";
			while (rs.next())
			{
                result+= "\""+rs.getString("betId")+"\": {";
				result+= "\"TeamID\": "+rs.getInt("teamId");
				result+= ",\"StartDate\": "+rs.getInt("startDate");
				result+= ",\"EndDate\": "+rs.getInt("endDate");
                result+= ",\"Odds\": "+rs.getInt("odds");
                result+= ",\"Type\": "+rs.getInt("type");
				result+=" }, ";
			}
            result = result.substring(0, result.length()-2);
            result +=" }";
            rs.close();
			psmt.close();
            conn.close();
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
			return null; //"/bet Fail";
        }
    }
}
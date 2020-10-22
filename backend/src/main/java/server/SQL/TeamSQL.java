package server.SQL;

import java.sql.*;
import java.util.ArrayList;
import server.Team.TeamStats;

public class TeamSQL {

	private String url;
	private Connection conn;
	Statement smt;
	PreparedStatement psmt;
	ResultSet rs;
	private String database;

	public TeamSQL(){	
		url = "jdbc:mysql://35.238.205.120:3306/";
		try{
			conn = DriverManager.getConnection(url, "root", "root");//development
			smt = conn.createStatement();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		database = "boiler_bets";

	}



public String getTeamStats(){
		try{
			psmt = conn.prepareStatement("select * from (select * from "+ this.database+".teamStats) as team_stats,"+ 
            "(select teamId, teamName from "+ this.database+".teams) as teams where team_stats.teamId = teams.teamId");
			rs = psmt.executeQuery();
			String all = "{ ";
			//ArrayList<TeamStats> stats = new ArrayList<TeamStats>();
            //TeamStats ts = new TeamStats();

			while (rs.next())
			{
                //TeamStats u = new TeamStats();

				//u.teamId=  rs.getInt("teamId");
                //u.teamName = 
                all+= "\""+rs.getString("teamName")+"\": {";
				all+= "\"wins\": "+rs.getInt("wins");
				all+= ",\"losses\": "+rs.getInt("losses");
				all+= ",\"winPct\": "+rs.getInt("winPct");
                all+= ",\"avgP\": "+rs.getInt("avgP");
                all+= ",\"avgFGP\": "+rs.getInt("avgFGP");
                all+= ",\"avg3PP\": "+rs.getInt("avg3PP");
                all+= ",\"avgFTP\": "+rs.getInt("avgFTP");
                all+= ",\"avgAST\": "+rs.getInt("avgAST");
                all+= ",\"avgTO\": "+rs.getInt("avgTO");
                all+= ",\"avgBLK\": "+rs.getInt("avgBLK");

				// String lastLogin = rs.getInt("lastLogin");
				all+=" }, ";
				//stats.add(u);
			
				
			}
            all = all.substring(0, all.length()-2);
            all+=" }";
			rs.close();
			psmt.close();
			conn.close();
			System.out.println(all);
			return all;

		}catch(Exception e){
			e.printStackTrace();
			return null;//"/user Fail";
		}
	}

}
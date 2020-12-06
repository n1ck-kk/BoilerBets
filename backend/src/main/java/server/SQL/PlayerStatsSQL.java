package server.SQL;

import java.sql.*;
import java.util.ArrayList;

import server.PlayerStats.PlayerStats;
import server.PlayerStats.PlayerStatsDeserializer;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.security.*;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;

public class PlayerStatsSQL {

    private String url;
	private Connection conn;
	Statement smt;
	PreparedStatement psmt;
	ResultSet rs;
	private String database;

	public PlayerStatsSQL(){	
		url = "jdbc:mysql://35.238.205.120:3306/";
		try{
			conn = DriverManager.getConnection(url, "root", "root");//development
			smt = conn.createStatement();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		database = "boiler_bets";

	}
    
    public String insertPlayerStats(long id, String playerInfo) throws JsonParseException, JsonMappingException, IOException {
        // Parse the arguments
		ObjectMapper om = new ObjectMapper();
		SimpleModule sm = new SimpleModule("PlayerStatsDeserializer", new Version(1, 0, 0, null, null, null));
		sm.addDeserializer(PlayerStats.class, new PlayerStatsDeserializer());
		om.registerModule(sm);

        PlayerStats newPlayer = om.readValue(playerInfo, PlayerStats.class);
        newPlayer.setID(id);

        try{
			String query = "insert into "+ this.database+".playerStats "+ 
				"(playerId, avgP, avgAST, avgBLK, avgSTL, avgTO, avgMin, avgFG, avgFG3, avgFT) "+
				"values "+ 
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(query);
			psmt.setLong(1, id);
			psmt.setLong(2, newPlayer.getAvgP());
			psmt.setLong(3, newPlayer.getAvgAST());
            psmt.setLong(4, newPlayer.getAvgBLK());
            psmt.setLong(5, newPlayer.getAvgSTL());
            psmt.setLong(6, newPlayer.getAvgTO());
            psmt.setLong(7, newPlayer.getAvgMin());
            psmt.setLong(8, newPlayer.getAvgFG());
            psmt.setLong(9, newPlayer.getAvgFG3());
            psmt.setLong(10, newPlayer.getAvgFT());
            System.out.println(query);
            psmt.executeUpdate();
			psmt.close();
            conn.close();
			return "OK";
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error inserting playerSTAT to DB.");
			return "ERROR INSERTING PLAYERSTAT";
        }
        
    }

	public String getPlayersStats(int playerId){
		try {
			System.out.println("Getting player statssql");
			psmt = conn.prepareStatement("select * from "+this.database+".playerStats where playerId = ?");
			psmt.setLong(1, playerId);
			rs = psmt.executeQuery();
			String playerStats = "";
			//ObjectMapper om = new ObjectMapper();
			//System.out.println("OM: "+om.writeValueAsString(rs));
			while(rs.next()){
				playerStats+="{ player_id: "+rs.getInt("playerId")+",";
				playerStats+=" avgP: "+rs.getInt("avgP")+",";
				playerStats+=" avgAST: "+rs.getInt("avgAST")+",";
				playerStats+=" avgBLK: "+rs.getInt("avgBLK")+",";
				playerStats+=" avgSTL: "+rs.getInt("avgSTL")+",";
				playerStats+=" avgTO: "+rs.getInt("avgTO")+",";
				playerStats+=" avgMin: "+rs.getInt("avgMin")+",";
				playerStats+=" avgFG: "+rs.getInt("avgFG")+",";
				playerStats+=" avgFG3: "+rs.getInt("avgFG3")+",";
				playerStats+=" avgFT: "+rs.getInt("avgFT")+" }";
			}
			System.out.println("JSON: "+playerStats);
			rs.close();
			psmt.close();
			conn.close();
			//System.out.println(teamId);
			return playerStats;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

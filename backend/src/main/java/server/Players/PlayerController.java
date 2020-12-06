package server.Players;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;

import java.io.IOException;
import java.util.ArrayList;
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
import java.sql.*;
import server.SQL.PlayerStatsSQL;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/player")
public class PlayerController {

    private String url;
	private Connection conn;
	Statement smt;
	PreparedStatement psmt;
	ResultSet rs;
	private String database;

    public PlayerController(){	
		url = "jdbc:mysql://35.238.205.120:3306/";
		try{
			conn = DriverManager.getConnection(url, "root", "root");//development
			smt = conn.createStatement();
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		database = "boiler_bets";

	}

    @Autowired
    PlayerRepository playerRepository;

	@GetMapping("/")
	public String base(){
		return "\"help\": \"me\"";
	}

    @PostMapping("/insertPlayer")
    public Player addPlayer(@RequestBody String playerInfo) throws JsonParseException, JsonMappingException, IOException {
		// Parse the arguments
		ObjectMapper om = new ObjectMapper();
		SimpleModule sm = new SimpleModule("PlayerDeserializer", new Version(1, 0, 0, null, null, null));
		sm.addDeserializer(Player.class, new PlayerDeserializer());
		om.registerModule(sm);

		Player newPlayer = om.readValue(playerInfo, Player.class);

		String result = "";
		try{
			psmt = conn.prepareStatement("select teamName from " +this.database+".teams where teamId = ?");
			psmt.setString(1, Long.toString(newPlayer.getTeamId()));
			rs = psmt.executeQuery();
			while (rs.next())
			{
                result += rs.getString("teamName");
			}
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		newPlayer.setTeam(newPlayer.getTeamId(), result);
		Player savedPlayer = playerRepository.save(newPlayer);

		Long newPlayerID = savedPlayer.getId();
		PlayerStatsSQL psSQL = new PlayerStatsSQL();

		psSQL.insertPlayerStats(newPlayerID, playerInfo);

		return savedPlayer;
    }

    @GetMapping("/getAllPlayers")
    public Iterable<Player> getPlayers() {
		System.out.println("HERE");
        return playerRepository.findAll();
	}
	
	@PostMapping("/getPlayerStats")
	public Player findPlayer(@RequestBody String player_id) {
		System.out.println("Getting player stats");
		System.out.println(player_id);
		
		int startIndex = player_id.indexOf(":");
		String player_num = player_id.substring(startIndex + 1, player_id.length()-1);//, startIndex + 2);
		System.out.println(player_num);
		PlayerStatsSQL pss = new PlayerStatsSQL();
		String t  = pss.getPlayersStats(Integer.parseInt(player_num));
		Iterable<Player> playerList = playerRepository.findAll();
		for (Player player : playerList ) {
			System.out.println(player.toString());
			if (player.getId() == Long.parseLong(player_num)) {
				//System.out.println(player.toString());
				player.playerStats = t;
				return player;
			}
		}

		System.out.println("returning null");
		return null;
	}
}

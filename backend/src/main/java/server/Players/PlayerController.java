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
		//url = "jdbc:mysql://35.238.205.120:3306/";
		try{
			//conn = DriverManager.getConnection(url, "root", "root");//development
			//smt = conn.createStatement();
			
			
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
    public Player addPlayer(@RequestBody Player player){
        return playerRepository.save(player);
    }

    @GetMapping("/getAllPlayers")
    public Iterable<Player> getPlayers() {
		System.out.println("HERE");
        return playerRepository.findAll();
    }
}

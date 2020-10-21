package server.User;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import server.SQL.UserSQL;
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


@RestController
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin //(origins = "https://fiveo-clocksomewhere.firebaseapp.com/", maxAge =  3600, allowedHeaders = "*")     //production
@RequestMapping(path="/user")
public class UserController {
	//make private and called in insert user or somewhere after testing
	byte[] g_salt = new byte[16];
	int count = 0;

	//@GetMapping("/hashPassword/{password}/{salty}")
	public String hashPass(String password, byte[] salt2, int flag)
		throws IOException{
		//flag = 1, insert User
		//flag = 0, login user
		MessageDigest ms;
		StringBuilder stringBuilder = new StringBuilder();
		String out = "";
		try{
			ms = MessageDigest.getInstance("SHA-256");

			SecureRandom r = new SecureRandom();
			byte[] salt = new byte[16];
			r.nextBytes(salt);

		
			//System.out.println("Salt array "+Arrays.toString(salt));
			//System.out.println("Salt concat array: "+Arrays.toString(salt)+"thisistestforconcatingation");
			String append = "";
			if(flag==1){//insert
				System.out.println("Salt is empty!");
				append = Arrays.toString(salt);
			}else{//login
				salt = salt2;//.getBytes();
				System.out.println("salt from parm "+new String(salt));
				append = new String(salt);
			}
			//System.out.println("Salt3: "+salt+" count: "+count);
			ms.update(append.getBytes());

			byte[] hashedPass = ms.digest(password.getBytes(StandardCharsets.UTF_8));

			
			for (byte b : hashedPass){
				stringBuilder.append(String.format("%02x", b));
			}
			System.out.println("Final salt: "+new String(salt));
			//String saltString = new String(salt);

			out = append+"."+stringBuilder.toString();
			//salt = null;
			//salt2 = null;
			//hashedPass = null;
		}catch(Exception e){
			e.printStackTrace();
			return "ERROr";
		}

		return out;
	}

	@PostMapping("/login")
	public String login(@RequestBody String userName)
		throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		SimpleModule sm = new SimpleModule("UserDeserializer", new Version(1, 0, 0, null, null, null));
		sm.addDeserializer(User.class, new UserDeserializer());
		om.registerModule(sm);
		User u = om.readValue(userName, User.class);

		UserSQL user = new UserSQL();
		String dbPass = user.login(u.userName, u.password);
		
		if(dbPass == "")
			return "{ \"status\" : \"Error: Login Failed.\"}";
		//byte[] saltSubstr = dbPass.substring(0, dbPass.indexOf(".")).getBytes();
		System.out.println("dbPass: "+dbPass+" vs attemptedPassword: "+u.password);
		if( dbPass.equals(u.password)){
			return "{ \"status\" : \"ok.\"}";
		} else {
			return "{ \"status\" : \"Error: Login Failed.\"}";
		}
	}

    @GetMapping("")
    public String findAll() {
        //find a single user
		UserSQL users = new UserSQL();
		ArrayList<User> list = users.getAllUsers();
		String out = "";
		for (User user : list) {
			out += user.userId + "\t";
			// out += user.darkMode + "\t";
			out += user.userName + "\t";
			out += user.password + "\t";
			out += user.name + "\t";
			out += user.email + "\t";
			
		}
		return out;
    }

    @GetMapping("/{name}")
    public String findUser(@PathVariable String name) 
			throws JsonParseException, JsonMappingException, IOException {
        //find a single user
		System.out.println("User: "+ name);
		UserSQL users = new UserSQL();

		ObjectMapper om2 = new ObjectMapper();
		SimpleModule sm2 = new SimpleModule("UserSerializer", new Version(1, 0, 0, null, null, null));
		sm2.addSerializer(User.class, new UserSerializer());
		om2.registerModule(sm2);

        return om2.writeValueAsString(users.getUser(name));
    }

	@GetMapping("/searchUsers")
	public String searchUsers(@RequestParam(name = "s") String request, @RequestParam(name = "u") String username) throws JsonProcessingException, IOException {
		System.out.println("SEARCHING USERS");

		UserSQL us = new UserSQL();
		User[] users = us.searchUsers(request);
		us = new UserSQL();
		//users = us.flagFollowedUsers(users, username);

		if(users == null) {
			return "{\"results\": \"DNE\"";
		}

		String out =  "{ \"results\": [ ";
		for (User user : users ) {
			out += new ObjectMapper().writeValueAsString(user) + ",";
			
		}

		out = out.substring(0, out.length()-1) + "] }";

		return out;
	}


    @PostMapping("/")
    public String insertUser(@RequestBody String username) 
			throws JsonParseException, JsonMappingException, IOException {
        //save a single user
		ObjectMapper om = new ObjectMapper();
		SimpleModule sm = new SimpleModule("UserDeserializer", new Version(1, 0, 0, null, null, null));
		sm.addDeserializer(User.class, new UserDeserializer());
		om.registerModule(sm);
		User u = om.readValue(username, User.class);
		//System.out.print(u.toString());
		String testHashedPass = hashPass(u.password, null, 1);

		UserSQL users = new UserSQL();
		String insert  = users.insertUser(u.userName, testHashedPass, u.name, u.email);	

	    return insert;
    }
}

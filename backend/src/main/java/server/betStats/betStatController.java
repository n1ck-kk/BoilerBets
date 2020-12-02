package server.betStats;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import server.SQL.betStatSQL;
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
@RequestMapping(path="/betStats")
public class betStatController {
    @GetMapping("/getAvailableBets")
    public String getAvailableBets() {
        betStatSQL bs = new betStatSQL();
        return bs.getAvailableBets();
    }
}

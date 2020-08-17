package com.unibe.whistleordie.controllers;

import com.unibe.whistleordie.dto.PlayerListDTO;
import com.unibe.whistleordie.model.Player;
import com.unibe.whistleordie.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping(value = "/addPlayer")
    public Player insert(@RequestBody Player player) {
        return playerService.insert(player);
    }
    @PostMapping(value = "/updateScore")
    public PlayerListDTO updateScore(@RequestBody Player player) {
        return playerService.updateFirst(player);
    }
    @GetMapping(value="fetchPlayer/{id}")
    public Player fetchPlayer(@PathVariable String id){
        return  playerService.getSinglePlayer(id);
    }

    @GetMapping(value="getBestScores/")
    public PlayerListDTO OrderByScoreAsc(){
        return  playerService.OrderByScoreAsc();
    }
    @PutMapping(value="/updatePlayer")
    public Player updatePlayer(@RequestBody Player player){
        return null;
    }
//    @DeleteMapping(value="deletePlayer")
//    public String deletePlayer(@RequestBody Player player){
//        return playerService.deletePlayer(player);
//    }

}

package com.unibe.whistleordie.services;

import com.mongodb.client.result.DeleteResult;
import com.unibe.whistleordie.dto.PlayerListDTO;
import com.unibe.whistleordie.model.Player;
import com.unibe.whistleordie.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Player insert(Player player) {
        return playerRepository.insert(player);
    }

    public PlayerListDTO updateFirst(Player player) {
        return playerRepository.updateFirst(player);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public PlayerListDTO OrderByScoreAsc() {
        return playerRepository.OrderByScoreAsc();
    }

    public Player getSinglePlayer(String id) {
        return playerRepository.findById(id);
    }

//    public String deletePlayer(Player player) {
//        DeleteResult deleteResult = playerRepository.deletePlayer(player);
//        if (deleteResult == null) {
//            return "User not found";
//        } else {
//            return "User deleted";
//        }
//    }
}

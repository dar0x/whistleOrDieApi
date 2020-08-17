package com.unibe.whistleordie.dal;

import java.util.List;

import com.mongodb.client.result.DeleteResult;
import com.unibe.whistleordie.dto.PlayerListDTO;
import com.unibe.whistleordie.model.Player;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;

public interface PlayerDAL {

    Player insert(Player player);

    PlayerListDTO updateFirst(Player player);

    List<Player> findAll();

//    @Query(sort = "{ score : -1 }")
    PlayerListDTO OrderByScoreAsc();

    Player findById(String id);

//    DeleteResult deletePlayer(Player id);
}

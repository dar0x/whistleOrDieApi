package com.unibe.whistleordie.repository;

import java.util.Comparator;
import java.util.List;

import com.mongodb.client.result.UpdateResult;
import com.unibe.whistleordie.dto.PlayerListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.unibe.whistleordie.dal.PlayerDAL;
import com.unibe.whistleordie.model.Player;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Repository
public class PlayerRepository implements PlayerDAL {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Player insert(Player player) {
        Query query = new Query(where("nombre").is(player.getNombre()));
        //if find player, create, otherwise return null
        List<Player> result = mongoTemplate.find(query, Player.class, "players");
        if (result.size() == 0) {
            return mongoTemplate.insert(player, "players");
        } else {
            return null;
        }
    }

    @Override
    public PlayerListDTO updateFirst(Player player) {
        Float newScore =player.getScore();
        String nombre =player.getNombre();
        Query queryFind = new Query(where("nombre").is(nombre).and("score").lt(newScore));
        Player result = mongoTemplate.findAndModify(queryFind,
                update("score", newScore), Player.class,"players");
        return OrderByScoreAsc();
    }

    @Override
    public List<Player> findAll() {
        List<Player> fetchAllPlayers = mongoTemplate.findAll(Player.class, "players");
        return fetchAllPlayers;
    }

    @Override
    public PlayerListDTO OrderByScoreAsc() {
        List<Player> fetchAllPlayers = mongoTemplate.findAll(Player.class, "players");
        //order by score
        Comparator<Player> playerComparator
                = Comparator.comparing(Player::getScore);
        Comparator<Player> playerComparatorReversed
                = playerComparator.reversed();
        fetchAllPlayers.sort(playerComparatorReversed);

        PlayerListDTO listDTO = new PlayerListDTO();
        listDTO.List = fetchAllPlayers;

        return listDTO;
    }

    @Override
    public Player findById(String id) {
        Player player = mongoTemplate.findById(id, Player.class, "player");
        return player;
    }
}

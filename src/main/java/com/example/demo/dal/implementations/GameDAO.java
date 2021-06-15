package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.IGameDAO;
import com.example.demo.exceptions.DaoException;
import com.example.demo.model.adm.Game;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class GameDAO implements IGameDAO {

    static final HashMap<Integer, Game> games = new HashMap<>();
    static private int gameIdCounter = 0;

    @Override
    public int createGame(Game game) throws DaoException {
        if (game != null) {
            Integer gameId = game.id;
            if (gameId != null) {
                return -1;

            } else {
                game.id = ++gameIdCounter;
                games.put(game.id, game);
                return game.id;
            }
        }
        return -1;
    }

    @Override
    public Game getGame(int gameId) {
//        if (games.containsKey(gameId)) {
//            return games.get(gameId);
//        }
        return games.get(gameId);
    }


    @Override
    public Collection<Game> getGames() {
        return games.values();
    }
}

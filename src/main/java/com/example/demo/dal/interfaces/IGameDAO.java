package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Board;
import com.example.demo.model.adm.Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IGameDAO {

    int createGame(Game game) throws DaoException;
    Game getGame(int gameId);

    Collection<Game> getGames();

}

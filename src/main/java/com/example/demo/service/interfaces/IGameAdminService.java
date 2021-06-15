package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.adm.Game;

import java.util.Collection;
import java.util.List;

public interface IGameAdminService {

    public Collection<Game> getGames() throws ServiceException, DaoException;

    int saveGame(Game game) throws ServiceException, DaoException;
}

package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.adm.Game;

import java.util.List;

public interface IGameAdminService {

    public List<Game> getGames() throws ServiceException, DaoException;
}

package com.example.demo.service.implementations;

import com.example.demo.dal.interfaces.IBoardDao;
import com.example.demo.dal.interfaces.IGameDAO;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.model.adm.Game;
import com.example.demo.model.adm.User;
import com.example.demo.service.interfaces.IGameAdminService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GameAdminService implements IGameAdminService {
    private final IBoardDao boardDao;

    private final IGameDAO gameDAO;

    public GameAdminService(IBoardDao boardDao, IGameDAO gameDAO) {
        this.boardDao = boardDao;
        this.gameDAO = gameDAO;

    }

    @Override

    public Collection<Game> getGames() throws ServiceException, DaoException {
        Collection<Game> result = gameDAO.getGames();

        for (Game game : result) {
            Board board = boardDao.getBoard(game.id);

            if (board != null && board.getPlayersNumber() != 0) {

                game.users.clear();
                for (int i = 0; i < board.getPlayersNumber(); i++) {

                    Player player = board.getPlayer(i);
                    User user = new User();
                    user.playerName = player.getName();
                    user.playerId = player.getPlayerId();

                    game.users.add(user);

                }

            }

        }


        return result;

//        for (Board board : boardDao.getBoards()) {
//            Game game = new Game();
//            game.name = board.boardName;
//            game.id = board.getGameId();
//            result.add(game);
//
//            int amountOfPlayers = board.getPlayersNumber();
//            game.started = amountOfPlayers > 1;
//            for (int i = 0; i < amountOfPlayers; i++) {
//                Player player = board.getPlayer(i);
//                User user = new User();
//                user.playerId = player.getPlayerId();
//                user.playerName = player.getName();
//                game.users.add(user);
//
//            }
//
//        }

    }

    @Override
    public int saveGame(Game game) throws ServiceException, DaoException {
        return gameDAO.createGame(game);

    }


}

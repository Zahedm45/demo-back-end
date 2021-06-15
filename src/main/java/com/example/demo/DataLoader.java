package com.example.demo;


import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.service.interfaces.IGameService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * The run method is executed upon startup, this can be used to do some data seeding.
 */
@Component
public class DataLoader implements ApplicationRunner {
    private final IGameService gameService;

    public DataLoader(IGameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run(ApplicationArguments args) throws ServiceException, DaoException {
        Board board = new Board(8, 8, "Board1");
        gameService.saveBoard(board);
        Player player = new Player(board, "blue", "Player1");
        gameService.addPlayer(board.getGameId(), player);
        gameService.setCurrentPlayer(board.getGameId(), player.getPlayerId());
        gameService.moveCurrentPlayer(board.getGameId(), 1, 1);
        player = new Player(board, "green", "Player2");
        gameService.addPlayer(board.getGameId(), player);
        gameService.movePlayer(board, 4, 4, player.getPlayerId());
        /*gameService.switchCurrentPlayer(board);*/




        Board board2 = new Board(6, 6, "Board2");
        gameService.saveBoard(board2);
        Player player2 = new Player(board2, "yellow", "Player1");
        gameService.addPlayer(board2.getGameId(), player2);
        gameService.setCurrentPlayer(board2.getGameId(), player2.getPlayerId());
        gameService.moveCurrentPlayer(board2.getGameId(), 1, 1);
        player2 = new Player(board2, "blue", "Player2");
        gameService.addPlayer(board2.getGameId(), player2);
        gameService.movePlayer(board2, 4, 4, player2.getPlayerId());

        player2 = new Player(board2, "red", "Player3");
        gameService.addPlayer(board2.getGameId(), player2);
        gameService.movePlayer(board2, 2, 4, player2.getPlayerId());






        Board board3 = new Board(6, 6, "Board3");
        gameService.saveBoard(board3);
        Player player3 = new Player(board3, "yellow", "Player1");
        gameService.addPlayer(board3.getGameId(), player3);
        gameService.setCurrentPlayer(board3.getGameId(), player3.getPlayerId());
        gameService.moveCurrentPlayer(board3.getGameId(), 1, 1);
        player3 = new Player(board3, "blue", "Player2");
        gameService.addPlayer(board3.getGameId(), player3);
        gameService.movePlayer(board3, 0, 0, player3.getPlayerId());




        Board board4 = new Board(6, 6, "Board4");
        gameService.saveBoard(board4);


    }
}

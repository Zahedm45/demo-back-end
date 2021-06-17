package com.example.demo.util.mapping;

import com.example.demo.controller.GameDTO;
import com.example.demo.controller.gameController.BoardDto;
import com.example.demo.controller.gameController.PlayerDto;
import com.example.demo.controller.gameController.SpaceDto;
import com.example.demo.dal.interfaces.IGameDAO;
import com.example.demo.exceptions.MappingException;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.model.Space;
import com.example.demo.model.adm.Game;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper implements IDtoMapper {


    private IGameDAO gameDAO;
    int num = 0;


    public DtoMapper(IGameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public PlayerDto convertToDto(Player player) throws MappingException {
        if(player == null){
            throw new MappingException("Player was null");
        }
        PlayerDto playerDto = new PlayerDto();
        playerDto.setBoardId(player.board.getGameId());
        playerDto.setPlayerId(player.getPlayerId());
        playerDto.setPlayerName(player.getName());
        playerDto.setPlayerColor(player.getColor());

        if (player.getSpace() != null) {
            playerDto.setX(player.getSpace().x);
            playerDto.setY(player.getSpace().y);
        }

        return playerDto;
    }


    public BoardDto convertToDto(Board board) throws MappingException {
        if(board == null){
            throw new MappingException("Board was null");
        }
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardId(board.getGameId());
        boardDto.setBoardName(board.boardName);
        boardDto.setHeight(board.height);
        boardDto.setWidth(board.width);

        if (board.getCurrentPlayer() != null) {
            boardDto.setCurrentPlayerDto(convertToDto(board.getCurrentPlayer()));
        }

        if (board.getPlayersNumber() > 0) {
            boardDto.setPlayerDtos(new PlayerDto[board.getPlayersNumber()]);
            for (int i = 0; i < board.getPlayersNumber(); i++) {
                boardDto.getPlayerDtos()[i] = convertToDto(board.getPlayer(i));
            }
        }

        if (board.getSpaces().length > 0 && board.getSpaces()[0].length > 0) {
            boardDto.setSpaceDtos(new SpaceDto[board.getSpaces().length][board.getSpaces()[0].length]);
            for (int i = 0; i < board.getSpaces().length; i++) {
                for (int j = 0; j < board.getSpaces()[i].length; j++) {
                    boardDto.getSpaceDtos()[i][j] = convertToDto(board.getSpace(i, j));
                }
            }
        }

        return boardDto;
    }

    public SpaceDto convertToDto(Space space) throws MappingException {
        if(space == null){
            throw new MappingException("Space was null");
        }
        SpaceDto spaceDto = new SpaceDto();
        spaceDto.setX(space.x);
        spaceDto.setY(space.y);
        if (space.getPlayer() != null) {
            spaceDto.setPlayerId(space.getPlayer().getPlayerId());
        }
        return spaceDto;
    }

    public Board convertToEntity(BoardDto boardDto) {
        Board board = new Board(boardDto.getWidth(), boardDto.getHeight(), boardDto.getBoardName());
        if (boardDto.getBoardId() != -1) {
            board.setGameId(boardDto.getBoardId());
        }
        return board;
    }

    public Space convertToEntity(SpaceDto spaceDto, Board board) {
        return new Space(board, spaceDto.getX(), spaceDto.getY());
    }

    public Player convertToEntity(PlayerDto playerDto, Board board) throws MappingException {
        if (playerDto.getBoardId() == null) { //We cant have a player without a board id
            throw new MappingException("PlayerDto did not contain a board id");
        }
        if (board == null) { //Incase the provided board id is invalid
            throw new MappingException("Board was null when trying to convert PlayerDto to Player");
        }
        if (playerDto.getPlayerId() == null) { //If we have not provided a player id, we are creating a new player

            num = board.getPlayersNumber();
            if (board.getPlayersNumber() >=6) {
                throw new MappingException("This board has already 6 players.");

            }
            if (playerDto.getPlayerName() == null) {
                playerDto.setPlayerName("player"+(num+1));
            }

            String[] color = {"red", "yellow", "green", "blue", "pink", "orange"};

            if (playerDto.getPlayerColor() == null) {
                playerDto.setPlayerColor(color[num]);
            }

            if (playerDto.getX() == null && playerDto.getY() == null) {
                int n = (num / board.width);

                playerDto.setX(n);
                playerDto.setY(num % board.width);
                //System.out.println(num % board.height);
            }

            num++;

            Player player = new Player(board, playerDto.getPlayerColor(), playerDto.getPlayerName());
            Space space = new Space(board, playerDto.getX(), playerDto.getY());
            player.setSpace(space);
            return player;
        }
        return null;
    }


    public Game convertToEntity(GameDTO gameDTO) {
        Game game;
        if (gameDTO.id == null) {
            game = new Game();
            game.name = gameDTO.name;

        } else {
            game = gameDAO.getGame(gameDTO.id);
            if (gameDTO.name != null) {
                game.name = gameDTO.name;
            }
        }
        return game;
    }
}




























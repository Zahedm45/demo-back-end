package com.example.demo.controller.gameAdmin;

import com.example.demo.controller.GameDTO;
import com.example.demo.controller.gameController.BoardDto;
import com.example.demo.dal.interfaces.IGameDAO;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.adm.Game;
import com.example.demo.service.interfaces.IGameAdminService;
import com.example.demo.util.mapping.IDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GameAdminController {

   private final IGameAdminService gameAdminService;
   private final IDtoMapper dtoMapper;

   public GameAdminController(IGameAdminService gameAdminService, IDtoMapper dtoMapper) {
      this.gameAdminService = gameAdminService;
      this.dtoMapper = dtoMapper;

   }

   ///______________________ casted
   @GetMapping("/game")
   public ResponseEntity<Collection<Game>> getGames() throws  MappingException, DaoException, ServiceException {
      Collection<Game> games =  gameAdminService.getGames();
      return new ResponseEntity<>(games, HttpStatus.OK);
   }

   // TODO there should be services for creating new games, adding players, and updating.
   // the game (e.g. name, the board size,) the players (name, color)

   @PostMapping("/game")
   public ResponseEntity<Integer> createGame(@RequestBody GameDTO gameDTO) throws ServiceException, DaoException {
      if (gameDTO.id != null) {
         return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
      }

      Game game = dtoMapper.convertToEntity(gameDTO);
      gameAdminService.saveGame(game);

      return new ResponseEntity<>(game.id, HttpStatus.CREATED);
   }
}

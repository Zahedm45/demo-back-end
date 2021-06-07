package com.example.demo.controller.gameAdmin;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.adm.Game;
import com.example.demo.service.interfaces.IGameAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GameAdminController {

   private final IGameAdminService gameAdminService;

   public GameAdminController(IGameAdminService gameAdminService) {
      this.gameAdminService = gameAdminService;
   }

   @GetMapping("/game")
   public ResponseEntity<List<Game>> getGames() throws  MappingException, DaoException, ServiceException {
      List<Game> games = gameAdminService.getGames();

      return new ResponseEntity<>(games, HttpStatus.OK);
   }

   // TODO there should be services for creating new games, adding players, and updating.
   // the game (e.g. name, the board size,) the players (name, color)
}

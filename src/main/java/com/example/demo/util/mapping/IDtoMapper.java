package com.example.demo.util.mapping;

import com.example.demo.controller.GameDTO;
import com.example.demo.controller.gameController.BoardDto;
import com.example.demo.controller.gameController.PlayerDto;
import com.example.demo.controller.gameController.SpaceDto;
import com.example.demo.exceptions.MappingException;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.model.Space;
import com.example.demo.model.adm.Game;

public interface IDtoMapper {
    PlayerDto convertToDto(Player player) throws MappingException;

    BoardDto convertToDto(Board board) throws MappingException;

    SpaceDto convertToDto(Space space) throws MappingException;

    Board convertToEntity(BoardDto boardDto);

    Space convertToEntity(SpaceDto spaceDto, Board board);

    Player convertToEntity(PlayerDto playerDto, Board board) throws MappingException;

    public Game convertToEntity(GameDTO gameDTO);
}

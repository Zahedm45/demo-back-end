package com.example.demo.model.adm;

import com.example.demo.model.Board;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public String name;
    public Integer id;
    public boolean started;
    public List<User> users = new ArrayList<>();
    public Board board;

}

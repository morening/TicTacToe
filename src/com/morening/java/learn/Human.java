package com.morening.java.learn;

import java.util.Scanner;

public class Human implements IPlayer {

    private Scanner sc = null;
    private char MARK = 'H';
    private String NAME = "人类";
    private char ENEMY_MARK = ChessBoard.MARK;

    public Human(Scanner sc){
        this.sc = sc;
    }

    @Override
    public void setEnemyMark(char MARK) {
        this.ENEMY_MARK = MARK;
    }

    @Override
    public String getPlayerName() {
        return NAME;
    }

    @Override
    public void setPlayerName(String name) {
        NAME = name;
    }

    @Override
    public char getPlayerMark() {
        return MARK;
    }

    @Override
    public void setPlayerMark(char mark) {
        MARK = mark;
    }

    @Override
    public boolean makeDecision(char[][] map) {
        int row = sc.nextInt();
        int col = sc.nextInt();
        if ((row < 1 || row > ChessBoard.MAX_MAP_SIZE)
                || (col < 1 || col > ChessBoard.MAX_MAP_SIZE)
                || (map[row][col] != ChessBoard.MARK)){
            return false;
        }
        map[row][col] = MARK;
        return true;
    }
}

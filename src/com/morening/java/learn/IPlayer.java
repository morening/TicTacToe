package com.morening.java.learn;

public interface IPlayer {

    void setEnemyMark(char MARK);

    String getPlayerName();

    void setPlayerName(String name);

    char getPlayerMark();

    void setPlayerMark(char mark);

    boolean makeDecision(char[][] map);
}

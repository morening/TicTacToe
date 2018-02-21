package com.morening.java.learn;

import java.util.Arrays;

public class ChessBoard {

    public static final int MAX_MAP_SIZE = 3;
    public static final char MARK = '_';

    private char[][] map = null;

    private IPlayer player1 = null;
    private IPlayer player2 = null;

    public void init() {
        map = new char[MAX_MAP_SIZE+1][MAX_MAP_SIZE+1];
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            Arrays.fill(map[i], MARK);
        }
        player1 = null;
        player2 = null;
    }

    public void setPlayer1(IPlayer player1) {
        this.player1 = player1;
    }

    public void setPlayer2(IPlayer player2) {
        this.player2 = player2;
    }

    public void start() {
        if (player1 == null || player2 == null){
            System.out.println("请双方棋手入场就坐，谢谢~");
            return;
        }
        if (player1 instanceof Human && player2 instanceof Human){
            player2.setPlayerMark('M');
            player2.setPlayerName("新人类");

        }
        if (player1 instanceof Computer && player2 instanceof Computer){
            player2.setPlayerMark('E');
            player2.setPlayerName("电脑");
        }

        player1.setEnemyMark(player2.getPlayerMark());
        player2.setEnemyMark(player1.getPlayerMark());

        int count = 0;
        System.out.println(String.format("井字棋比赛开始 %s VS %s", player1.getPlayerName(), player2.getPlayerName()));
        while (true){
            System.out.println(String.format("请 %s 选择落子位置", player1.getPlayerName()));
            while (!player1.makeDecision(map)){
                System.out.println("落子位置无效，请重新选择！");
            }
            count++;
            showDecision(map);
            if (Util.isGameOver(map, player1.getPlayerMark(), player2.getPlayerMark(), MAX_MAP_SIZE)){
                System.out.println(String.format("%s 先手获胜", player1.getPlayerName()));
                break;
            }
            if (count >= MAX_MAP_SIZE*MAX_MAP_SIZE){
                System.out.println("平局！");
                break;
            }
            System.out.println(String.format("请 %s 选择落子位置", player2.getPlayerName()));
            while (!player2.makeDecision(map)){
                System.out.println("落子位置无效，请重新选择！");
            }
            count++;
            showDecision(map);
            if (Util.isGameOver(map, player1.getPlayerMark(), player2.getPlayerMark(), MAX_MAP_SIZE)){
                System.out.println(String.format("%s 后手获胜", player2.getPlayerName()));
                break;
            }
        }
    }

    private void showDecision(char[][] map) {
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            for (int j=1; j<=MAX_MAP_SIZE; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

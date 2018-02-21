package com.morening.java.learn;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        ChessBoard board = new ChessBoard();
        board.init();
        System.out.println("==========================");
        System.out.println("==      井字棋 V1.0      ==");
        System.out.println("==========================");
        System.out.println("请选择游戏模式：");
        System.out.println("[1] Human    vs Computer");
        System.out.println("[2] Computer vs Human   ");
        System.out.println("[3] Human    vs Human   ");
        System.out.println("[4] Computer vs Computer");
        Scanner sc = new Scanner(System.in);
        while (true){
            int mode = sc.nextInt();
            switch (mode){
                case 1:
                    board.setPlayer1(new Human(sc));
                    board.setPlayer2(new Computer());
                    break;
                case 2:
                    board.setPlayer1(new Computer());
                    board.setPlayer2(new Human(sc));
                    break;
                case 3:
                    board.setPlayer1(new Human(sc));
                    board.setPlayer2(new Human(sc));
                    break;
                case 4:
                    board.setPlayer1(new Computer());
                    board.setPlayer2(new Computer());
                    break;
                default:
                    System.out.println("输入无效，请重新选择游戏模式！");
            }
            if (mode >= 1 && mode <= 4){
                break;
            }
        }
        board.start();
        sc.close();
    }
}

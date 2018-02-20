package com.morening.java.learn;

public class TicTacToe {

    public static void main(String[] args) {

        ChessBoard board = new ChessBoard();
        board.init();
        board.setPlayer1(new Human());
        board.setPlayer2(new Computer());
        board.start();
    }
}

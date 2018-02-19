package com.morening.java.learn;

import java.util.Scanner;

public class TicTacToe {

    private static final int MAX_MAP_SIZE = 3;
    private static char[][] map = null;
    private static Scanner sc = null;
    private static final char BLANK_MARK = 'B';
    private static final char COMPUTER_MARK = 'C';
    private static final char HUMAN_MARK = 'H';
    private static final char DRAW_MARK = 'D';
    private static final int MAX_MAKE_DECISION_DEPTH = 6;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        map = new char[MAX_MAP_SIZE+1][MAX_MAP_SIZE+1];
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            for (int j=1; j<=MAX_MAP_SIZE; j++){
                map[i][j] = BLANK_MARK;
            }
        }
        while (true){
            if (inputHumanDecision()) continue;
            printMap(map);
            char winner = checkGameOver(map);
            if (winner != BLANK_MARK) {
                if (winner == DRAW_MARK){
                    System.out.println("平局");
                } else {
                    System.out.println(String.format("%s 获胜！", winner == COMPUTER_MARK ? "计算机": "人类"));
                }
                break;
            }
            makeComputerDecision();
            printMap(map);
            winner = checkGameOver(map);
            if (winner != BLANK_MARK) {
                if (winner == DRAW_MARK){
                    System.out.println("平局");
                } else {
                    System.out.println(String.format("%s 获胜！", winner == COMPUTER_MARK ? "计算机": "人类"));
                }
                break;
            }
        }
    }

    private static char checkGameOver(char[][] map) {
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            char winner = map[i][1];
            if (winner != COMPUTER_MARK && winner != HUMAN_MARK){
                continue;
            }
            boolean isGameOver = true;
            for (int j=2; j<=MAX_MAP_SIZE; j++){
                if (map[i][1] != map[i][j]) {
                    isGameOver = false;
                    break;
                }
            }
            if (isGameOver){
                return winner;
            }
        }

        for (int j=1; j<=MAX_MAP_SIZE; j++){
            char winner = map[1][j];
            if (winner != COMPUTER_MARK && winner != HUMAN_MARK){
                continue;
            }
            boolean isGameOver = true;
            for (int i=2; i<=MAX_MAP_SIZE; i++){
                if (map[1][j] != map[i][j]) {
                    isGameOver = false;
                    break;
                }
            }
            if (isGameOver){
                return winner;
            }
        }

        if ((map[2][2] == COMPUTER_MARK || map[2][2] == HUMAN_MARK) && map[2][2] == map[1][1] && map[2][2] == map[3][3]){
            return map[2][2];
        }

        if ((map[2][2] == COMPUTER_MARK || map[2][2] == HUMAN_MARK) && map[2][2] == map[1][3] && map[2][2] == map[3][1]) {
            return map[2][2];
        }

        for (int i=1; i<=MAX_MAP_SIZE; i++){
            for (int j=1; j<=MAX_MAP_SIZE; j++){
                if (map[i][j] == BLANK_MARK){
                    return BLANK_MARK;
                }
            }
        }

        return DRAW_MARK;
    }

    private static void makeComputerDecision() {
        System.out.println("计算机决定落子位置...");
        if (doComputerAttack(map)){
            return;
        }
        if (doComputerDefense(map)) {
            return;
        }
        if (doComputerCenter(map)){
            return;
        }
        Node root = new Node(map, HUMAN_MARK, 1);
        createGameTree(root);
        alphabetaPruning(root);
        inputComputerDecision(root);
    }

    private static boolean doComputerCenter(char[][] map) {
        if (map[2][2] == BLANK_MARK){
            map[2][2] = COMPUTER_MARK;
            return true;
        }
        return false;
    }

    private static boolean doComputerAttack(char[][] map) {
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            if (map[i][1] == COMPUTER_MARK && map[i][2] == COMPUTER_MARK && map[i][3] == BLANK_MARK){
                map[i][3] = COMPUTER_MARK;
                return true;
            }
            if (map[i][1] == BLANK_MARK && map[i][2] == COMPUTER_MARK && map[i][3] == COMPUTER_MARK){
                map[i][1] = COMPUTER_MARK;
                return true;
            }
            if (map[i][1] == COMPUTER_MARK && map[i][2] == BLANK_MARK && map[i][3] == COMPUTER_MARK){
                map[i][2] = COMPUTER_MARK;
                return true;
            }
        }
        for (int j=1; j<=MAX_MAP_SIZE; j++){
            if (map[1][j] == COMPUTER_MARK && map[2][j] == COMPUTER_MARK && map[3][j] == BLANK_MARK){
                map[3][j] = COMPUTER_MARK;
                return true;
            }
            if (map[1][j] == BLANK_MARK && map[2][j] == COMPUTER_MARK && map[3][j] == COMPUTER_MARK){
                map[1][j] = COMPUTER_MARK;
                return true;
            }
            if (map[1][j] == COMPUTER_MARK && map[2][j] == BLANK_MARK && map[3][j] == COMPUTER_MARK){
                map[2][j] = COMPUTER_MARK;
                return true;
            }
        }
        if (map[1][1] == COMPUTER_MARK && map[2][2] == COMPUTER_MARK && map[3][3] == BLANK_MARK){
            map[3][3] = COMPUTER_MARK;
            return true;
        }
        if (map[1][1] == BLANK_MARK && map[2][2] == COMPUTER_MARK && map[3][3] == COMPUTER_MARK){
            map[1][1] = COMPUTER_MARK;
            return true;
        }
        if (map[1][1] == COMPUTER_MARK && map[2][2] == BLANK_MARK && map[3][3] == COMPUTER_MARK){
            map[2][2] = COMPUTER_MARK;
            return true;
        }
        if (map[1][3] == COMPUTER_MARK && map[2][2] == COMPUTER_MARK && map[3][1] == BLANK_MARK){
            map[3][1] = COMPUTER_MARK;
            return true;
        }
        if (map[1][3] == BLANK_MARK && map[2][2] == COMPUTER_MARK && map[3][1] == COMPUTER_MARK){
            map[1][3] = COMPUTER_MARK;
            return true;
        }
        if (map[1][3] == COMPUTER_MARK && map[2][2] == BLANK_MARK && map[3][1] == COMPUTER_MARK){
            map[2][2] = COMPUTER_MARK;
            return true;
        }
        return false;
    }

    private static boolean doComputerDefense(char[][] map) {
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            if (map[i][1] == HUMAN_MARK && map[i][2] == HUMAN_MARK && map[i][3] == BLANK_MARK){
                map[i][3] = COMPUTER_MARK;
                return true;
            }
            if (map[i][1] == BLANK_MARK && map[i][2] == HUMAN_MARK && map[i][3] == HUMAN_MARK){
                map[i][1] = COMPUTER_MARK;
                return true;
            }
            if (map[i][1] == HUMAN_MARK && map[i][2] == BLANK_MARK && map[i][3] == HUMAN_MARK){
                map[i][2] = COMPUTER_MARK;
                return true;
            }
        }
        for (int j=1; j<=MAX_MAP_SIZE; j++){
            if (map[1][j] == HUMAN_MARK && map[2][j] == HUMAN_MARK && map[3][j] == BLANK_MARK){
                map[3][j] = COMPUTER_MARK;
                return true;
            }
            if (map[1][j] == BLANK_MARK && map[2][j] == HUMAN_MARK && map[3][j] == HUMAN_MARK){
                map[1][j] = COMPUTER_MARK;
                return true;
            }
            if (map[1][j] == HUMAN_MARK && map[2][j] == BLANK_MARK && map[3][j] == HUMAN_MARK){
                map[2][j] = COMPUTER_MARK;
                return true;
            }
        }
        if (map[1][1] == HUMAN_MARK && map[2][2] == HUMAN_MARK && map[3][3] == BLANK_MARK){
            map[3][3] = COMPUTER_MARK;
            return true;
        }
        if (map[1][1] == BLANK_MARK && map[2][2] == HUMAN_MARK && map[3][3] == HUMAN_MARK){
            map[1][1] = COMPUTER_MARK;
            return true;
        }
        if (map[1][1] == HUMAN_MARK && map[2][2] == BLANK_MARK && map[3][3] == HUMAN_MARK){
            map[2][2] = COMPUTER_MARK;
            return true;
        }
        if (map[1][3] == HUMAN_MARK && map[2][2] == HUMAN_MARK && map[3][1] == BLANK_MARK){
            map[3][1] = COMPUTER_MARK;
            return true;
        }
        if (map[1][3] == BLANK_MARK && map[2][2] == HUMAN_MARK && map[3][1] == HUMAN_MARK){
            map[1][3] = COMPUTER_MARK;
            return true;
        }
        if (map[1][3] == HUMAN_MARK && map[2][2] == BLANK_MARK && map[3][1] == HUMAN_MARK){
            map[2][2] = COMPUTER_MARK;
            return true;
        }
        return false;
    }

    private static void inputComputerDecision(Node root) {
        Node next_move = null;
        int maxVal = Integer.MIN_VALUE;
        Node cur = root.child;
        while (cur != null){
            if (cur.fVal > maxVal){
                maxVal = cur.fVal;
                next_move = cur;
            }
            cur = cur.brother;
        }

        if (next_move != null){
            copyMap(next_move.map, map);
        }
    }

    private static void alphabetaPruning(Node root) {
        Node child = root.child;
        if (child == null){
            root.fVal = evaluate(root.map);
//            printMap(root.map);
            return;
        }
        Node parent = root.parent;
        root.fVal = root.type == COMPUTER_MARK ? Integer.MIN_VALUE: Integer.MAX_VALUE;
        while (child != null){
            alphabetaPruning(child);
            if (root.type == COMPUTER_MARK){
                root.fVal = Math.max(root.fVal, child.fVal);
                if (parent != null && parent.type == HUMAN_MARK){
                    if (parent.fVal <= root.fVal){
                        return;
                    }
                }
            } else if (root.type == HUMAN_MARK){
                root.fVal = Math.min(root.fVal, child.fVal);
                if (parent != null && parent.type == COMPUTER_MARK){
                    if (parent.fVal >= root.fVal){
                        return;
                    }
                }
            }
            child = child.brother;
        }

        if (parent != null && parent.type == COMPUTER_MARK){
            parent.fVal = Math.max(parent.fVal, root.fVal);
        } else if (parent != null && parent.type == HUMAN_MARK){
            parent.fVal = Math.min(parent.fVal, root.fVal);
        }
    }

    /*private static int[][] score = {{}, {0,3,2,3}, {0, 2, 4, 2}, {0, 3, 2, 3}};
    private static int evaluate(char[][] map){
        int computerScore = 0;
        int humanScore = 0;
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            for (int j=1; j<=MAX_MAP_SIZE; j++){
                if (map[i][j] == COMPUTER_MARK){
                    computerScore += score[i][j];
                } else if (map[i][j] == HUMAN_MARK){
                    humanScore += score[i][j];
                }
            }
        }

        int[] computerCnt = new int[8];
        int[] humanCnt = new int[8];
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            for (int j=1; j<=MAX_MAP_SIZE; j++){
                if (map[i][j] == COMPUTER_MARK){
                    computerCnt[i-1]++;
                } else  if (map[i][j] == HUMAN_MARK){
                    humanCnt[i-1]++;
                }
            }
        }
        for (int j=1; j<=MAX_MAP_SIZE; j++){
            for (int i=1; i<=MAX_MAP_SIZE; i++){
                if (map[i][j] == COMPUTER_MARK){
                    computerCnt[j+3-1]++;
                } else if (map[i][j] == HUMAN_MARK){
                    humanCnt[j+3-1]++;
                }
            }
        }
        if (map[1][1] == COMPUTER_MARK){
            computerCnt[6]++;
        }
        if (map[2][2] == COMPUTER_MARK){
            computerCnt[6]++;
        }
        if (map[3][3] == COMPUTER_MARK){
            computerCnt[6]++;
        }
        if (map[1][1] == HUMAN_MARK){
            humanCnt[6]++;
        }
        if (map[2][2] == HUMAN_MARK){
            humanCnt[6]++;
        }
        if (map[3][3] == HUMAN_MARK){
            humanCnt[6]++;
        }
        if (map[1][3] == COMPUTER_MARK){
            computerCnt[7]++;
        }
        if (map[2][2] == COMPUTER_MARK){
            computerCnt[7]++;
        }
        if (map[3][1] == COMPUTER_MARK){
            computerCnt[7]++;
        }
        if (map[1][3] == HUMAN_MARK){
            humanCnt[7]++;
        }
        if (map[2][2] == HUMAN_MARK){
            humanCnt[7]++;
        }
        if (map[3][1] == HUMAN_MARK){
            humanCnt[7]++;
        }
        for (int k=0; k<8; k++){
            if (computerCnt[k] == 2 && humanCnt[k] == 1){
                computerScore += 5;
            }
            if (humanCnt[k] == 2 && computerCnt[k] == 1){
                humanScore += 5;
            }
        }

        return computerScore - humanScore;
    }*/

    private static int evaluate(char[][] map) {
        char winner = checkGameOver(map);
        if (winner == COMPUTER_MARK){
            return 10;
        } else if (winner == HUMAN_MARK){
            return -10;
        }
        boolean[] computerFlag = new boolean[8];
        boolean[] humanFlag = new boolean[8];
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            for (int j=1; j<=MAX_MAP_SIZE; j++){
                if (map[i][j] == COMPUTER_MARK){
                    computerFlag[i-1] = true;
                } else if (map[i][j] == HUMAN_MARK){
                    humanFlag[i-1] = true;
                }
            }
        }
        for (int j=1; j<=MAX_MAP_SIZE; j++){
            for (int i=1; i<=MAX_MAP_SIZE; i++){
                if (map[i][j] == COMPUTER_MARK){
                    computerFlag[j+3-1] = true;
                } else if (map[i][j] == HUMAN_MARK){
                    humanFlag[j+3-1] = true;
                }
            }
        }
        if (map[1][1] == COMPUTER_MARK || map[2][2] == COMPUTER_MARK || map[3][3] == COMPUTER_MARK){
            computerFlag[6] = true;
        }
        if (map[1][1] == HUMAN_MARK || map[2][2] == HUMAN_MARK || map[3][3] == HUMAN_MARK){
            humanFlag[6] = true;
        }
        if (map[1][3] == COMPUTER_MARK || map[2][2] == COMPUTER_MARK || map[3][1] == COMPUTER_MARK){
            computerFlag[7] = true;
        }
        if (map[1][3] == HUMAN_MARK || map[2][2] == HUMAN_MARK || map[3][1] == HUMAN_MARK){
            humanFlag[7] = true;
        }

        int computerCnt = 0;
        int humanCnt = 0;
        for (int k=0; k<8; k++){
            if (computerFlag[k]){
                computerCnt++;
            }
            if (humanFlag[k]){
                humanCnt++;
            }
        }

        return computerCnt - humanCnt;
    }

    private static void createGameTree(Node parent) {
        if (parent.depth >= MAX_MAKE_DECISION_DEPTH){
            return;
        }
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            for (int j=1; j<=MAX_MAP_SIZE; j++){
                if (parent.map[i][j] == BLANK_MARK){
                    Node temp = new Node(parent.map, parent.type == COMPUTER_MARK ? HUMAN_MARK: COMPUTER_MARK, parent.depth+1);
                    temp.map[i][j] = temp.type;
//                    printMap(temp.map);
                    insertNode(parent, temp);
                    if (checkGameOver(temp.map) != BLANK_MARK){
                        continue;
                    }
                    createGameTree(temp);
                }
            }
        }
    }

    private static void insertNode(Node parent, Node temp) {
        temp.brother = parent.child;
        parent.child = temp;

        temp.parent = parent;
    }

    private static boolean inputHumanDecision() {
        System.out.println("请输入落子位置：");
        int r = sc.nextInt();
        int c = sc.nextInt();
        if (r <= 0 || r > MAX_MAP_SIZE || c <= 0 || c > MAX_MAP_SIZE){
            System.out.println("落子位置无效，请重新输入！");
            return true;
        }
        map[r][c] = HUMAN_MARK;
        return false;
    }

    private static void printMap(char[][] map) {
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            for (int j=1; j<=MAX_MAP_SIZE; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void copyMap(char[][] src, char[][] dst){
        for (int i=1; i<=MAX_MAP_SIZE; i++){
            for (int j=1; j<=MAX_MAP_SIZE; j++){
                dst[i][j] = src[i][j];
            }
        }
    }

    static class Node{
        char[][] map = new char[MAX_MAP_SIZE+1][MAX_MAP_SIZE+1];

        int depth = 0;
        int fVal = 0;
        char type = COMPUTER_MARK;

        Node parent = null;
        Node child = null;
        Node brother = null;

        public Node(char[][] map, char type, int depth){
            copyMap(map, this.map);
            this.type = type;
            this.depth = depth;
        }
    }

}

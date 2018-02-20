package com.morening.java.learn;


public class Computer implements IPlayer{

    private char MARK = 'C';
    private String NAME = "计算机";
    private static final int MAX_DECISION_DEPTH = 5;

    private char ENEMY_MARK = ChessBoard.MARK;

    public void setEnemyMark(char mark){
        this.ENEMY_MARK = mark;
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
        if (willWin(map)){
            return true;
        }
        if (needDefend(map)) {
            return true;
        }
        if (centerFirst(map)){
            return true;
        }
        Node root = new Node(map, ENEMY_MARK, 0);
        createGameTree(root);
        alphabetaPruning(root);
        outputDecision(root, map);

        return true;
    }

    private boolean centerFirst(char[][] map) {
        if (map[2][2] == ChessBoard.MARK){
            map[2][2] = MARK;
            return true;
        }
        return false;
    }

    private boolean willWin(char[][] map) {
        for (int i=1; i<=ChessBoard.MAX_MAP_SIZE; i++){
            if (map[i][1] == MARK && map[i][2] == MARK && map[i][3] == ChessBoard.MARK){
                map[i][3] = MARK;
                return true;
            }
            if (map[i][1] == ChessBoard.MARK && map[i][2] == MARK && map[i][3] == MARK){
                map[i][1] = MARK;
                return true;
            }
            if (map[i][1] == MARK && map[i][2] == ChessBoard.MARK && map[i][3] == MARK){
                map[i][2] = MARK;
                return true;
            }
        }
        for (int j=1; j<=ChessBoard.MAX_MAP_SIZE; j++){
            if (map[1][j] == MARK && map[2][j] == MARK && map[3][j] == ChessBoard.MARK){
                map[3][j] = MARK;
                return true;
            }
            if (map[1][j] == ChessBoard.MARK && map[2][j] == MARK && map[3][j] == MARK){
                map[1][j] = MARK;
                return true;
            }
            if (map[1][j] == MARK && map[2][j] == ChessBoard.MARK && map[3][j] == MARK){
                map[2][j] = MARK;
                return true;
            }
        }
        if (map[1][1] == MARK && map[2][2] == MARK && map[3][3] == ChessBoard.MARK){
            map[3][3] = MARK;
            return true;
        }
        if (map[1][1] == ChessBoard.MARK && map[2][2] == MARK && map[3][3] == MARK){
            map[1][1] = MARK;
            return true;
        }
        if (map[1][1] == MARK && map[2][2] == ChessBoard.MARK && map[3][3] == MARK){
            map[2][2] = MARK;
            return true;
        }
        if (map[1][3] == MARK && map[2][2] == MARK && map[3][1] == ChessBoard.MARK){
            map[3][1] = MARK;
            return true;
        }
        if (map[1][3] == ChessBoard.MARK && map[2][2] == MARK && map[3][1] == MARK){
            map[1][3] = MARK;
            return true;
        }
        if (map[1][3] == MARK && map[2][2] == ChessBoard.MARK && map[3][1] == MARK){
            map[2][2] = MARK;
            return true;
        }
        return false;
    }

    private boolean needDefend(char[][] map) {
        for (int i=1; i<=ChessBoard.MAX_MAP_SIZE; i++){
            if (map[i][1] == ENEMY_MARK && map[i][2] == ENEMY_MARK && map[i][3] == ChessBoard.MARK){
                map[i][3] = MARK;
                return true;
            }
            if (map[i][1] == ChessBoard.MARK && map[i][2] == ENEMY_MARK && map[i][3] == ENEMY_MARK){
                map[i][1] = MARK;
                return true;
            }
            if (map[i][1] == ENEMY_MARK && map[i][2] == ChessBoard.MARK && map[i][3] == ENEMY_MARK){
                map[i][2] = MARK;
                return true;
            }
        }
        for (int j=1; j<=ChessBoard.MAX_MAP_SIZE; j++){
            if (map[1][j] == ENEMY_MARK && map[2][j] == ENEMY_MARK && map[3][j] == ChessBoard.MARK){
                map[3][j] = MARK;
                return true;
            }
            if (map[1][j] == ChessBoard.MARK && map[2][j] == ENEMY_MARK && map[3][j] == ENEMY_MARK){
                map[1][j] = MARK;
                return true;
            }
            if (map[1][j] == ENEMY_MARK && map[2][j] == ChessBoard.MARK && map[3][j] == ENEMY_MARK){
                map[2][j] = MARK;
                return true;
            }
        }
        if (map[1][1] == ENEMY_MARK && map[2][2] == ENEMY_MARK && map[3][3] == ChessBoard.MARK){
            map[3][3] = MARK;
            return true;
        }
        if (map[1][1] == ChessBoard.MARK && map[2][2] == ENEMY_MARK && map[3][3] == ENEMY_MARK){
            map[1][1] = MARK;
            return true;
        }
        if (map[1][1] == ENEMY_MARK && map[2][2] == ChessBoard.MARK && map[3][3] == ENEMY_MARK){
            map[2][2] = MARK;
            return true;
        }
        if (map[1][3] == ENEMY_MARK && map[2][2] == ENEMY_MARK && map[3][1] == ChessBoard.MARK){
            map[3][1] = MARK;
            return true;
        }
        if (map[1][3] == ChessBoard.MARK && map[2][2] == ENEMY_MARK && map[3][1] == ENEMY_MARK){
            map[1][3] = MARK;
            return true;
        }
        if (map[1][3] == ENEMY_MARK && map[2][2] == ChessBoard.MARK && map[3][1] == ENEMY_MARK){
            map[2][2] = MARK;
            return true;
        }
        return false;
    }

    private void outputDecision(Node root,char[][] map) {
        Node next = null;
        int maxVal = Integer.MIN_VALUE;
        Node cur = root.child;
        while (cur != null){
            if (cur.fVal > maxVal){
                maxVal = cur.fVal;
                next = cur;
            }
            cur = cur.brother;
        }

        if (next != null){
            Util.copyMap(next.map, map, ChessBoard.MAX_MAP_SIZE);
        }
    }

    private void alphabetaPruning(Node root) {
        Node child = root.child;
        if (child == null){
            root.fVal = evaluate(root.map);
            return;
        }
        Node parent = root.parent;
        root.fVal = root.type == MARK ? Integer.MIN_VALUE: Integer.MAX_VALUE;
        while (child != null){
            alphabetaPruning(child);
            if (root.type == MARK){
                root.fVal = Math.max(root.fVal, child.fVal);
                if (parent != null && parent.type == ENEMY_MARK){
                    if (parent.fVal <= root.fVal){
                        return;
                    }
                }
            } else if (root.type == ENEMY_MARK){
                root.fVal = Math.min(root.fVal, child.fVal);
                if (parent != null && parent.type == MARK){
                    if (parent.fVal >= root.fVal){
                        return;
                    }
                }
            }
            child = child.brother;
        }

        if (parent != null && parent.type == MARK){
            parent.fVal = Math.max(parent.fVal, root.fVal);
        } else if (parent != null && parent.type == ENEMY_MARK){
            parent.fVal = Math.min(parent.fVal, root.fVal);
        }
    }

    private static final int[][] locateScore = {{0,}, {0, 3, 2, 3}, {0, 2, 4, 2}, {0, 3, 2, 3}};
    private static final int LINE_SCORE = 10;
    private int evaluate(char[][] map) {
        int score = calcLocateScore(map, MARK);
        int enemyScore = calcLocateScore(map, ENEMY_MARK);

        int[] count = new int[8];
        int[] enemyCount = new int[8];

        flatten(map, MARK, count);
        flatten(map, ENEMY_MARK, enemyCount);

        score += calcLineScore(count);
        enemyScore += calcLineScore(enemyCount);

        return score - enemyScore;
    }

    private int calcLineScore(int[] count) {
        int score = 0;
        for (int k=0; k<8; k++){
            if (count[k] == 2){
                score += LINE_SCORE;
            }
        }

        return score;
    }

    private void flatten(char[][] map, char mark, int[] count) {
        for (int i=1; i<=ChessBoard.MAX_MAP_SIZE; i++){
            for (int j=1; j<=ChessBoard.MAX_MAP_SIZE; j++){
                if (map[i][j] == mark){
                    count[i-1]++;
                }
            }
        }
        for (int j=1; j<=ChessBoard.MAX_MAP_SIZE; j++){
            for (int i=1; i<=ChessBoard.MAX_MAP_SIZE; i++){
                if (map[i][j] == mark){
                    count[j+3-1]++;
                }
            }
        }
        if (map[1][1] == mark){
            count[6]++;
        }
        if (map[2][2] == mark){
            count[6]++;
        }
        if (map[3][3] == mark){
            count[6]++;
        }
        if (map[1][3] == mark){
            count[7]++;
        }
        if (map[2][2] == mark){
            count[7]++;
        }
        if (map[3][1] == mark){
            count[7]++;
        }
    }

    private int calcLocateScore(char[][] map, char mark) {
        int score = 0;
        for (int i=1; i<=ChessBoard.MAX_MAP_SIZE; i++){
            for (int j=1; j<=ChessBoard.MAX_MAP_SIZE; j++){
                if (map[i][j] == mark){
                    score += locateScore[i][j];
                }
            }
        }

        return score;
    }

    private void createGameTree(Node parent) {
        if (parent.depth >= MAX_DECISION_DEPTH){
            return;
        }
        for (int i=1; i<=ChessBoard.MAX_MAP_SIZE; i++){
            for (int j=1; j<=ChessBoard.MAX_MAP_SIZE; j++){
                if (parent.map[i][j] == ChessBoard.MARK){
                    Node temp = new Node(parent.map, parent.type == MARK ? ENEMY_MARK : MARK, parent.depth+1);
                    temp.map[i][j] = temp.type;
                    insertNode(parent, temp);
                    if (Util.isGameOver(temp.map, MARK, ENEMY_MARK, ChessBoard.MAX_MAP_SIZE)){
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

    private class Node{
        char[][] map = new char[ChessBoard.MAX_MAP_SIZE+1][ChessBoard.MAX_MAP_SIZE+1];

        int depth = 0;
        int fVal = 0;
        char type = MARK;

        Node parent = null;
        Node child = null;
        Node brother = null;

        public Node(char[][] map, char type, int depth){
            Util.copyMap(map, this.map, ChessBoard.MAX_MAP_SIZE);
            this.type = type;
            this.depth = depth;
        }
    }
}

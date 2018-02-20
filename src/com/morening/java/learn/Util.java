package com.morening.java.learn;

public class Util {

    public static boolean isGameOver(char[][] map, char mark1, char mark2, int size){
        int[] sums = new int[8];
        for (int i=1; i<=size; i++){
            for (int j=1; j<=size; j++){
                sums[i-1] += map[i][j];
                sums[j+3-1] += map[i][j];
            }
        }
        sums[6] = map[1][1] + map[2][2] + map[3][3];
        sums[7] = map[1][3] + map[2][2] + map[3][1];
        for (int k=0; k<8; k++){
            if (sums[k]/3.0f == mark1 || sums[k]/3.0f == mark2){
                return true;
            }
        }
        return false;
    }

    public static void copyMap(char[][] src, char[][] dst, int size){
        for (int i=1; i<=size; i++){
            for (int j=1; j<=size; j++){
                dst[i][j] = src[i][j];
            }
        }
    }
}

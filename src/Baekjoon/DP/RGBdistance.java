package Baekjoon.DP;

import java.util.Scanner;

public class RGBdistance {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] cost = new int[N][3];
        int[][] result = new int[N][3];

        for(int i = 0; i<N; i++){
            for(int j = 0; j<3; j++){
                cost[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i<3; i++){
            result[0][i] = cost[0][i];
        }

        for(int i = 0; i<N-1; i++){
            result[i+1][0] = Math.min(result[i][1],result[i][2]) + cost[i+1][0];
            result[i+1][1] = Math.min(result[i][0],result[i][2]) + cost[i+1][1];
            result[i+1][2] = Math.min(result[i][0],result[i][1]) + cost[i+1][2];
        }

        int answer = Math.min(Math.min(result[N-1][0],result[N-1][1]),result[N-1][2]);

        System.out.println(answer);

    }
}

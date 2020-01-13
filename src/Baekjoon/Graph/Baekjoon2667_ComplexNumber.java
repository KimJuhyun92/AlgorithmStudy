package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon2667_ComplexNumber {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<House> houseNumber = new ArrayList<>();
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int number = 0;
    static int count = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < N; j ++){
                map[i][j] = line.charAt(j) - '0';
            }
        }

        //큐에 1인 좌표를 넣어두고 while을 돌려서 bfs 안에 dfs로 돌려도 가능

        for(int i = 0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    number++;
                    visited[i][j] = true;
                    houseNumber.add(new House(count));
                    dfs(i, j);
                }
            }
        }

        System.out.println(houseNumber.size());

        Collections.sort(houseNumber, new Comparator<House>() {
            @Override
            public int compare(House o1, House o2) {
                return o1.number - o2.number;
            }
        });

        for(int i = 0; i<houseNumber.size(); i++){
            System.out.println(houseNumber.get(i).number);
        }

    }

    public static void dfs(int i, int j){

        map[i][j] = number;
        houseNumber.get(number-1).number += 1;

        for(int dir = 0; dir < 4; dir++){
            int r = i + dr[dir];
            int c = j + dc[dir];

            if(r >= 0 && c >= 0 && r < N && c < N){
                if(map[r][c] ==1 && !visited[r][c]){
                    visited[r][c] = true;
                    dfs(r,c);
                }
            }
        }
    }

    static class House {
        int number;

        House(int number){
            this.number = number;
        }
    }
}

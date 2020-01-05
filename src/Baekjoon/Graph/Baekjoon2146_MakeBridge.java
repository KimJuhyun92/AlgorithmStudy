package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1. DFS를 통해 각 섬의 범위 번호를 메김
2. BFS를 통해 각 섬에서 다른 섬으로 가는 길이를 모두 구함
3. 구한 길이 중 최소값을 출력
 */

public class Baekjoon2146_MakeBridge {

    static int N;
    static int[][] map;
    static int[][] bridge;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int number = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        bridge = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                //1인 경우를 모두 q에 push
                if (map[i][j] == 1)
                    q.offer(new int[]{i, j});
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    number++;
                    visited[i][j] = true;
                    dfs_Numbering(i, j);
                }
            }
        }

        bfs_Checking();

        searchMinValue();

        System.out.println(min);


    }

    public static void dfs_Numbering(int i, int j) {
        map[i][j] = number;

        for (int dir = 0; dir < 4; dir++) {
            int r = i + dr[dir];
            int c = j + dc[dir];

            if (r >= 0 && c >= 0 && r < N && c < N) {
                if (map[r][c] == 1 && !visited[r][c]) {
                    visited[r][c] = true;
                    dfs_Numbering(r, c);
                }
            }
        }
    }

    public static void bfs_Checking() {
        while (!q.isEmpty()) {
            int[] location = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int r = location[0] + dr[dir];
                int c = location[1] + dc[dir];

                if (r >= 0 && c >= 0 && r < N && c < N) {
                    //bridge는 다음 좌표가 0이 아닌곳은 섬이므로 그 외의 곳에 다리를 설치해야함
                    if (map[r][c] == 0 && bridge[r][c] == 0) {
                        map[r][c] = map[location[0]][location[1]];
                        bridge[r][c] = bridge[location[0]][location[1]] + 1;
                        q.offer(new int[]{r, c});
                    }
                }
            }
        }
    }

    public static void searchMinValue(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int dir = 0; dir<4; dir++){
                    int r = i + dr[dir];
                    int c = j + dc[dir];

                    if(r >= 0 && c >= 0 && r < N && c < N){
                        if(map[r][c] != map[i][j]){
                            min = Math.min(min,bridge[i][j]+bridge[r][c]);
                        }
                    }


                }
            }
        }
    }

    public static void printMap(){
        System.out.println("Map");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("------------------------");
        System.out.println("Bridge");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(bridge[i]));
        }
    }
}

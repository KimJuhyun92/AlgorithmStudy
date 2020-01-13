package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2468_SafeArea {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int maxHeight = Integer.MIN_VALUE;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int maxArea = 1;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight,map[i][j]);
            }
        }

        for(int depth = 1; depth < maxHeight; depth++){
            visited = new boolean[N][N];
            int count = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j ++){
                    if(map[i][j] > depth && !visited[i][j]){
                        dfs(i,j,depth);
                        count ++;
                    }
                }
            }
            maxArea = Math.max(maxArea,count);
        }

        System.out.println(maxArea);


    }

    public static void dfs(int i, int j, int depth){
        if(visited[i][j])
            return;

        visited[i][j] = true;

        for(int dir = 0; dir<4; dir++){
            int r = i + dr[dir];
            int c = j + dc[dir];

            if( r>=0 && c>=0 && r<N && c<N){
                if(map[r][c] > depth && !visited[r][c]){
                    dfs(r,c,depth);
                }
            }
        }
    }
}

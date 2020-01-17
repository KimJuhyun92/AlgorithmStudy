package Baekjoon.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato {

    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> q;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static boolean flag = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        q = new LinkedList<>();

        for(int i = 0; i<N; N++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    flag = true;
                else if(map[i][j] == 1){
                    q.offer(new int[] {i,j});
                }
            }
        }

        if(!flag){
            System.out.println(0);
            System.exit(0);
        }
        bfs();
    }

    public static void bfs(){
        int[] loc = q.poll();


    }

}

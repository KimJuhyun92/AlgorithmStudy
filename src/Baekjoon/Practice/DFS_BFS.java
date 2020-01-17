package Baekjoon.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFS_BFS {

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++){
            map.add(new ArrayList<>());
        }

        visited = new boolean[N+1];

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map.get(x).add(y);
            map.get(y).add(x);
        }

        dfs(V);
        visited = new boolean[N+1];
        System.out.println();
        bfs(V);
    }

    public static void dfs(int v){
        visited[v] = true;
        System.out.print(v + " ");
        for(int value : map.get(v)){
            if(!visited[value]){
                dfs(value);
            }
        }
    }

    public static void bfs(int v){
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;

        while(!q.isEmpty()){
            int value = q.poll();
            System.out.print(value + " ");

            for(int e : map.get(value)){
                if(!visited[e]){
                    visited[e] = true;
                    q.offer(e);
                }
            }
        }
    }

    public static void printEdge(){
        for(int i = 0; i<map.size(); i++){
            for(int j = 0; j<map.get(i).size(); j++){
                System.out.print(map.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

}
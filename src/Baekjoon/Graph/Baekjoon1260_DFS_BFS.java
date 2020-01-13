package Baekjoon.Graph;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1260_DFS_BFS {

    static ArrayList<Integer>[] map;
    static boolean[] visited;
    static Queue<Integer> q;


    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        map = (ArrayList<Integer>[]) new ArrayList[N+1];

        for(int i = 0; i <= N; i++){
            map[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x].add(y);
            map[y].add(x);
        }
        for(int i = 0; i<=N; i++)
            Collections.sort(map[i]);

        DFS(visited, V);
        Arrays.fill(visited, false);
        BFS(visited, V);
    }

    static public void DFS(boolean[] visited, int v) {
        visited[v] = true;
        System.out.print(v + " ");

        for(int e : map[v]) {
            if(!visited[e]) {
                DFS(visited, e);
            }
        }
    }

    static public void BFS(boolean[] visited, int v){
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;

        while(!q.isEmpty()){
            v = q.poll();
            System.out.print(v + " ");

            for(int e : map[v]) {
                if(!visited[e]) {
                    q.add(e);
                    visited[e] = true;
                }
            }
        }
    }


}

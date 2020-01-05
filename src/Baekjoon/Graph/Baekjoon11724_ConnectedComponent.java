package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon11724_ConnectedComponent {
    static int count = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N+1];
        ArrayList<Integer>[] map = (ArrayList<Integer>[]) new ArrayList[N+1];

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

//        for(int i = 1; i<=N; i++){
//            System.out.println(Arrays.toString(new ArrayList[]{map[i]}));
//        }

        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
//                DFS(map, visited, i);
                BFS(map, visited, i);
                count++;
            }
        }

        System.out.println(count);

    }

    static public void DFS(ArrayList<Integer>[] map, boolean[] visited, int v) {
        visited[v] = true;

        for(int e : map[v]) {
            if(!visited[e]) {
                DFS(map, visited, e);
            }
        }
    }
    static public void BFS(ArrayList<Integer>[] map, boolean[] visited, int v){
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;

        while(!q.isEmpty()){
            v = q.poll();
//            System.out.print(v + " ");

            for(int e : map[v]) {
                if(!visited[e]) {
                    q.add(e);
                    visited[e] = true;
                }
            }
        }
    }
}

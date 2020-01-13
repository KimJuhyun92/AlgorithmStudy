package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1167_TreeDiameter {

    static int V;
    static ArrayList<ArrayList<Edge>> map;
    static int[] dist;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();

        for(int i = 0; i<=V; i++)
            map.add(new ArrayList<>());

        for(int i = 0; i<V; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            while(true){
                int y = Integer.parseInt(st.nextToken());
                if(y == -1)
                    break;
                int weight = Integer.parseInt(st.nextToken());
                map.get(x).add(new Edge(y,weight));
            }
        }

        bfs(1);
        int start = 1;
        for(int i = 2; i<dist.length; i++){
            if(dist[start] < dist[i])
                start = i;
        }

        bfs(start);
        Arrays.sort(dist);
        System.out.println(dist[V]);

    }

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        dist = new int[V+1];
        boolean[] visited = new boolean[V+1];

        visited[start] = true;
        q.offer(start);

        while(!q.isEmpty()){
            int v = q.poll();

            for(Edge e : map.get(v)){
                int nextNode = e.y;
                int distance = e.distance;
                if(!visited[nextNode]){
                    visited[nextNode] = true;
                    q.offer(nextNode);
                    dist[nextNode] = dist[v] + distance;
                }
            }
        }
    }

    public static class Edge {
        int y;
        int distance;

        public Edge(int y, int distance){
            this.y = y;
            this.distance = distance;
        }
    }
}

package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1197_MST_Prim {

    static int V,E;
    static boolean[] visited;
    static ArrayList<ArrayList<Edge>> map;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        visited = new boolean[V + 1];
        map = new ArrayList<>();

        for (int i = 0; i <= V; i++)
            map.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map.get(x).add(new Edge(y, weight));
            map.get(y).add(new Edge(x, weight));
        }

        PriorityQueue<Edge> q = new PriorityQueue<>();
        visited[1] = true;
        for (Edge v : map.get(1)) {
            q.offer(v);
        }

        System.out.println(prim(q));
    }

    public static int prim(PriorityQueue<Edge> q){
        int count = 0;
        int answer = 0;
        while(!q.isEmpty()) {
            Edge vertex = q.poll();
            int nextNode = vertex.y;
            if(visited[nextNode])
                continue;
            int weight = vertex.weight;
            answer += weight;
            visited[nextNode] = true;
            count++;
            if(count == V+1)
                break;

            for(Edge nextVertex : map.get(nextNode)){
                q.offer(nextVertex);
            }
        }

        return answer;
    }

    public static class Edge implements Comparable<Edge>{
        int y;
        int weight;

        Edge(int y, int weight){
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight > o.weight) return 1;
            else if (this.weight == o.weight) return 0;
            else return -1;
        }
    }
}

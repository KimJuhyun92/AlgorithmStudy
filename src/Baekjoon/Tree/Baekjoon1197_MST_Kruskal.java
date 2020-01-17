package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1197_MST_Kruskal {

    static int V,E;
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];

        PriorityQueue<Edge> q = new PriorityQueue<>();

        for( int i = 0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            q.offer(new Edge(x, y, weight));
        }

        init();

        System.out.println(kruskal(q));
    }

    public static int kruskal(PriorityQueue<Edge> q) {
        int count = 0;
        int answer = 0;

        while(!q.isEmpty()){
            if(count == V)
                break;

            Edge vertex = q.poll();

            if(find(vertex.x) != find(vertex.y)){
                union(vertex.x, vertex.y);
                answer += vertex.weight;
                count++;
            }
        }
        return answer;
    }

    public static void init(){
        for(int i = 0; i<=V; i ++){
            parent[i] = i;
        }
    }

    public static int find(int x){
        if(parent[x] == x)
            return x;
        else{
            int p = find(parent[x]);
            parent[x] = p;
            return p;
        }
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y)
            parent[y] = x;
    }

    public static class Edge implements Comparable<Edge> {
        int x;
        int y;
        int weight;

        Edge(int x, int y, int weight) {
            this.x = x;
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

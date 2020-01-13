package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1916_ShortestDistance {

    static int N,M;
    static int start, end;
    static ArrayList<ArrayList<Edge>> map;
    static int[] dist;
    public static int INF = 1000000001;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new ArrayList<>();
        dist = new int[N+1];
        for(int i = 0; i<=N; i++){
            map.add(new ArrayList<Edge>());
            dist[i] = INF;
        }

        StringTokenizer st;
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map.get(x).add(new Edge(y,weight));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        System.out.println(dist[end]);

    }

    public static void dijkstra(int start){
        PriorityQueue<Edge> q = new PriorityQueue<>();
        dist[start] = 0;
        q.offer(new Edge(start,dist[start]));

        while(!q.isEmpty()){
            Edge v = q.poll();
            int nextNode = v.y;
            int distance = v.distance;
            for(Edge e : map.get(nextNode)){
                if(dist[e.y]  > dist[nextNode] + e.distance){
                    dist[e.y] = dist[nextNode] + e.distance;
                    q.offer(e);
                }
            }
        }
    }
    public static void printMap(){
        for(int i = 1; i<=N; i++){
            for(int j = 0; j < map.get(i).size(); j++){
                System.out.print("edge : " + map.get(i).get(j).y + " ");
                System.out.print("dist : " + map.get(i).get(j).distance + " ");
            }
            System.out.println();
        }
        System.out.println("start : " + start);
        System.out.println("end : " + end);
    }

    public static class Edge implements Comparable<Edge>{
        int y;
        int distance;

        public Edge(int y, int distance){
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            // TODO Auto-generated method stub
            if(this.distance > o.distance) return 1;
            else if(this.distance == o.distance) return 0;
            else return -1;
        }
    }
}

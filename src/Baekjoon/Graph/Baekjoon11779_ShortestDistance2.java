package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon11779_ShortestDistance2 {

    static int N, M;
    static int start, end;
    static ArrayList<ArrayList<Edge>> map;
    static ArrayList<ArrayList<Integer>> trace;
    static int[] dist, via;
    static boolean[] visited;
    public static int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new ArrayList<>();
        trace = new ArrayList<>();
        dist = new int[N+1];
        via = new int[N+1];
        visited = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<Edge>());
            trace.add(new ArrayList<Integer>());
            dist[i] = INF;
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int weight = sc.nextInt();

            map.get(x).add(new Edge(y, weight));
        }

        start = sc.nextInt();
        end = sc.nextInt();

        dijkstra(start);
        System.out.println(dist[end]);
        System.out.println(trace.get(end).size() + 1);

        for (int i = 0; i < trace.get(end).size(); i++) {
            System.out.print(trace.get(end).get(i) + " ");
        }
        System.out.print(end);

//        ArrayList<Integer> ret = new ArrayList<Integer>();
//        while(end!=start){
//            ret.add(end);
//            end = via[end];
//        }
//        ret.add(start);
//        System.out.println(ret.size());
//        Collections.reverse(ret);
//        for(int i=0;i<ret.size();i++)
//            System.out.print(ret.get(i)+" ");



    }

    public static void dijkstra(int start) {
        PriorityQueue<Edge> q = new PriorityQueue<>();
        dist[start] = 0;
        q.offer(new Edge(start, dist[start]));

        while(!q.isEmpty()){
            Edge v = q.poll();
            int node = v.y;
            int distance = v.distance;
            for(Edge e : map.get(node)){
                if(dist[e.y]  > dist[node] + e.distance){
                    dist[e.y] = dist[node] + e.distance;
                    trace.get(e.y).add(node);
                    q.offer(e);
                }
            }
        }

//        while (!q.isEmpty()) {
//            Edge v = q.poll();
//            int node = v.y;
//            int distance = v.distance;
//            if(dist[node] < distance) continue;
//            for (Edge e : map.get(node)) {
//                int nextNode = e.y;
//                int nextDistance = e.distance + distance;
//                if (dist[nextNode] > nextDistance ) {
//                    dist[nextNode] = nextDistance;
////                    trace.get(nextNode).add(node);
//                    via[nextNode] = node;
//                    q.offer(new Edge(nextNode, dist[nextNode]));
//                }
//            }
//        }
    }

    public static class Edge implements Comparable<Edge> {
        int y;
        int distance;

        public Edge(int y, int distance) {
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.distance > o.distance) return 1;
            else if (this.distance == o.distance) return 0;
            else return -1;
        }
    }
}

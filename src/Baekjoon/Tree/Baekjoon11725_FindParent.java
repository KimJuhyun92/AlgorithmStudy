package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon11725_FindParent {

    static int N;
    static ArrayList<ArrayList<Integer>> map;
    static int[] parent;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        parent = new int[N+1];
        visited = new boolean[N+1];

        for(int i = 0; i<=N; i++){
            map.add(new ArrayList<>());
        }

        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map.get(x).add(y);
            map.get(y).add(x);
        }

        dfs(1);

        for(int i = 2; i< parent.length; i++){
            System.out.println(parent[i]);
        }

    }
    static void dfs(int v){
        visited[v] = true;

        for(int value : map.get(v)){
            if(!visited[value]){
                parent[value] = v;
                dfs(value);
            }
        }


    }


    static void printMap(){
        for(int i = 1; i<=N; i++){
            for(int j = 0; j<map.get(i).size(); j++){
                System.out.print(map.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}

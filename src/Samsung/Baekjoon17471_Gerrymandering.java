package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon17471_Gerrymandering {

    static int N;
    static int[] population;
    static ArrayList<ArrayList<Integer>> map;
    static boolean[] visited;
    static boolean[] visited_A, visited_B;
    static int min = Integer.MAX_VALUE;
    static int a_sum, b_sum;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        population = new int[N + 1];
        map = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            map.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            population[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                int y = Integer.parseInt(st.nextToken());
                map.get(i).add(y);
            }
        }

        //조합으로 경우의 수 찾기
        int range = N / 2;
        for (int i = 1; i <= range; i++) {
            doCombination(new int[i], 0, N, i, 0);
        }

        if (min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }

    static public int bfs(int v, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();

        int sum = 0;
        q.offer(v);
        visited[v] = true;

        sum += population[v];

        while (!q.isEmpty()) {
            v = q.poll();
            for (int e : map.get(v)) {
                if (!visited[e]) {
                    q.offer(e);
                    sum += population[e];
                    visited[e] = true;
                }
            }
        }

        return sum;
    }

    public static void doCombination(int[] list, int index, int n, int r, int target) {
        if (r == 0) {
            ArrayList<Integer> team_A = new ArrayList<>();
            ArrayList<Integer> team_B = new ArrayList<>();
            visited = new boolean[N + 1];
            visited_A = new boolean[N + 1];
            visited_B = new boolean[N + 1];

            a_sum = 0;
            b_sum = 0;

            Arrays.fill(visited_A,true);
            for (int i = 0; i < index; i++) {
                team_A.add(list[i] + 1);
                //team A에서 골라진 조합을 제외한 나머지는 False
                visited[team_A.get(i)] = true;
                visited_A[team_A.get(i)] = false;
            }
            //bfs() 후 visited 체크
            a_sum = bfs(team_A.get(0), visited_A);

            Arrays.fill(visited_B, true);
            //False인 index는 team B로 add
            for (int i = 1; i <= N; i++) {
                if(!team_A.contains(i)){
                    team_B.add(i);
                    visited_B[i] = false;
                }
            }
            //bfs() 후 visited 체크
            b_sum = bfs(team_B.get(0), visited_B);

            if(isConnected(visited_A) && isConnected(visited_B))
                min = Math.min(Math.abs(a_sum - b_sum), min);

        } else if (target == n)
            return;
        else {
            list[index] = target;
            doCombination(list, index + 1, n, r - 1, target + 1);
            doCombination(list, index, n, r, target + 1);
        }
    }

    public static boolean isConnected(boolean[] visited){
        for(int i = 1; i<=N; i++){
            if(!visited[i])
                return false;
        }
        return true;
    }

//    public static void printCombination() {
//        System.out.println("A team");
//        for (int i = 0; i < team_A.size(); i++) {
//            for (int j = 0; j < team_A.get(i).size(); j++) {
//                System.out.print(team_A.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("------------------------");
//        System.out.println("B team");
//        for (int i = 0; i < team_B.size(); i++) {
//            for (int j = 0; j < team_B.get(i).size(); j++) {
//                System.out.print(team_B.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
//    }
}

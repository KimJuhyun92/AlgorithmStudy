package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Kruskal Algorithm 활용
1. 섬 들을 찾아서 구분
2. 각 섬을 이어주는 최소 직선거리를 찾기
3. Kruskal 알고리즘으로 섬 - 1개의 간선으로 연결
3. 모든섬이 연결됬는지 확인
4. 최솟값 출력

** visited[][] 안써도 무방

각 섬마다 쌍으로 queue에 넣어서 4방진을 한방향으로 직진
다른 섬에 도착할 시 start, end, weight을 저장
모든 가능한 간선을 저장 후 크루스칼 알고리즘 실행
 */

public class Baekjoon17472_MakeBridge2 {

    static int N, M;
    static int[][] map;
    static int[] parent;
    static boolean[][] visited;
    static ArrayList<ArrayList<int[]>> islandList;
    static PriorityQueue<Edge> edgeList = new PriorityQueue<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int island_Num = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        islandList = new ArrayList<>();

        //Set Island's Number
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    //각 섬의 좌표 저장
                    islandList.add(new ArrayList<>());
                    searchIsland(i, j, ++island_Num);
                }
            }
        }

        //섬의 숫자 만큼 돌며 각 섬에서 출발하여 직선 거리로 갔을 때 섬이 있을 경우 edgdList에 간선 추가.
        for (int islandNum = 1; islandNum <= islandList.size(); islandNum++) {
            visited = new boolean[N][M];
            searchBridge(islandNum);
        }

        parent = new int[island_Num+1];
        init();
        min = kruskal(edgeList);

        if (min == 0)
            System.out.println(-1);
        else
            System.out.println(min);
    }

    public static int kruskal(PriorityQueue<Edge> q) {
        int count = 0;
        int answer = 0;

        while(!q.isEmpty()){
            if(count == N)
                break;

            Edge vertex = q.poll();

            if(find(vertex.x) != find(vertex.y)){
                union(vertex.x, vertex.y);
                answer += vertex.weight;
                count++;
            }
        }

        if(count != island_Num-1)
            return 0;
        return answer;
    }


    public static void searchBridge(int islandNum) {
        //섬의 갯수만큼 돌며 큐에 넣고 간선 찾기 시작
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < islandList.get(islandNum - 1).size(); i++)
            q.offer(islandList.get(islandNum - 1).get(i));

        while (!q.isEmpty()) {
            int[] loc = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int weight = 0;
                int x = loc[0];
                int y = loc[1];
                visited = new boolean[N][M];
                while (true) {
                    int r = x + dr[dir];
                    int c = y + dc[dir];

                    if (r < 0 || c < 0 || r >= N || c >= M)
                        break;

                    if (map[r][c] == islandNum)
                        break;

                    //다리를 놓을 수 있는 경우
                    if (map[r][c] == 0 && !visited[r][c]) {
//                    if (map[r][c] == 0) {
                        visited[r][c] = true;
                        x = r;
                        y = c;
                        weight ++;
                        continue;
                    }
                    //다른 섬을 만날 경우
                    if (map[r][c] != islandNum && map[r][c] != 0) {
                        if(weight > 1){
                            edgeList.offer(new Edge(islandNum, map[r][c], weight));
                        }
                        break;
                    }
                }
            }
        }
    }


    public static void searchIsland(int i, int j, int island_Num) {
        visited[i][j] = true;
        map[i][j] = island_Num;
        islandList.get(island_Num - 1).add(new int[]{i, j});

        for (int dir = 0; dir < 4; dir++) {
            int r = i + dr[dir];
            int c = j + dc[dir];

            if (r >= 0 && c >= 0 && r < N && c < M) {
                if (map[r][c] == 1 && !visited[r][c]) {
                    searchIsland(r, c, island_Num);
                }
            }
        }
    }

    public static void init(){
        for(int i = 0; i<=island_Num; i ++){
            parent[i] = i;
        }
    }

    public static int find(int x) {
        if (parent[x] == x)
            return x;
        else {
            int p = find(parent[x]);
            parent[x] = p;
            return p;
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y)
            parent[y] = x;
    }

    public static class Edge implements Comparable<Edge> {
        int x;
        int y;
        int weight;

        public Edge(int x, int y, int weight) {
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

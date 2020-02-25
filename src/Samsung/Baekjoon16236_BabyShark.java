package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16236_BabyShark {

    static int N;
    static int sharkSize[] = {2,0}; // 0번째 -> 현재 사이즈, 1번째 -> 먹은 물고기의 수
    static int[][] fishMap;
    static int[][] sharkMap;
    static boolean[][] visited;
    static int[] dr = {-1,0,0,1};
    static int[] dc = {0,-1,1,0};
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        fishMap = new int[N][N];
        sharkMap = new int[N][N];
        visited = new boolean[N][N];

        int[] sharkLocation = new int[2]; //초기 상어의 위치

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                int fish = Integer.parseInt(st.nextToken());
                if(fish != 9)
                    fishMap[i][j] = fish;
                else{
                    sharkLocation[0] = i;
                    sharkLocation[1] = j;
                }
            }
        }
        searchFish(sharkLocation[0],sharkLocation[1]);
        System.out.println(count);
    }

    public static void searchFish(int i , int j){
        Queue<int[]> q = new LinkedList<>();
        PriorityQueue<Point> nextLoc = new PriorityQueue<>();
        q.offer(new int[] {i,j});

        while(!q.isEmpty()){
            int size = q.size();

            for(int k = 0; k<size; k++){
                int[] loc = q.poll();
                visited[loc[0]][loc[1]] = true;;

                for(int dir = 0; dir<4; dir++){
                    int r = loc[0] + dr[dir];
                    int c = loc[1] + dc[dir];

                    if(r >= 0 && c >= 0 && r < N && c < N){
                        //다음 갈 위치의 물고기가 상어보다 작거나 같다면
                        if(sharkSize[0] >= fishMap[r][c] && !visited[r][c]){
                            // 위치를 이동
                            visited[r][c] = true;
                            sharkMap[r][c] = sharkMap[loc[0]][loc[1]] + 1;

                            //상어보다 작은 물고기라면 먹기
                            if(sharkSize[0] > fishMap[r][c] && fishMap[r][c]!=0)
                                nextLoc.offer(new Point(r,c));
                            else
                                q.offer(new int[] {r,c});
                        }
                    }
                }
            }

            if(nextLoc.size() != 0){
                Point location = nextLoc.poll();
                int x = location.x;
                int y = location.y;
                nextLoc.clear();

                //count 증가 후 초기화
                count += sharkMap[x][y];
                visited = new boolean[N][N];
                sharkMap = new int[N][N];
                q.clear();
                q.offer(new int[] {x, y});
                fishMap[x][y] = 0;

                //상어 크기와 먹은 수가 같다면 크기 증가
                sharkSize[1]++;
                if(sharkSize[0] == sharkSize[1]){
                    sharkSize[0]++;
                    sharkSize[1] = 0;
                }
            }
        }
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                if (this.y < o.y) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (this.x < o.x) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}

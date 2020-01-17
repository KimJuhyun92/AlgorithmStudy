package Kakao2017;

import java.util.LinkedList;
import java.util.Queue;

public class KakaoFriends_ColoringBook {
    public static void main(String argsp[]){
        int m = 6;
        int n = 4;
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        solution(m,n,picture);

    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static int max;

    public static int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        max = Integer.MIN_VALUE;
        int area = 0;

        visited = new boolean[m][n];

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(picture[i][j] != 0 && !visited[i][j]){
                    int areaNum = picture[i][j];
                    visited[i][j] = true;
                    max = Math.max(max, bfs(i,j,m,n,picture,areaNum));
                    area ++;
                }
            }
        }

        answer[0] = area;
        answer[1] = max;

        return answer;
    }

    public static int bfs(int i, int j, int m, int n, int[][] picture, int areaNum){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i,j});

        int count = 1;

        while(!q.isEmpty()){
            int[] loc = q.poll();
            int x = loc[0];
            int y = loc[1];

            for(int dir = 0; dir<4; dir++){
                int r = x + dr[dir];
                int c = y + dc[dir];

                if(r >= 0 && c >= 0 && r < m && c < n){
                    if(picture[r][c] == areaNum && !visited[r][c]){
                        visited[r][c] = true;
                        count ++;
                        q.offer(new int[] {r,c});
                    }
                }
            }
        }
        return count;
    }
}

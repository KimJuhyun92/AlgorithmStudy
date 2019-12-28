package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
규칙
1.  번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다.
    di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향.

2.  인접하면서 수가 같은 것을 모두 찾는다
 - 있는 경우 원판에서 인접하면서 같은 수를 모두 지운다.
 - 없는 경우 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
 */

/*
1. k번 회전
2. 원판 수정
3. T번 만큼 수행
4. 원판 숫자 합산
 */

public class TurnDisk {

    static int N,M,T;
    static int[][] disk;
    static int x, d, k;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static boolean[][] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        disk = new int[N+1][M];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                disk[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            rotate(x, d, k);
            if(!check()){
                adjustDisk();
            }
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += disk[i][j];
            }
        }

        System.out.println(sum);
        br.close();

    }

    //로테이션
    private static void rotate(int x, int d, int k) {
        for (int i = x; i <= N; i += x) { //배수인 원판을 돌려야 하므로, x부터 배수로 증가하여 총 원판수를 넘기 전까지 회전
            int cnt = k;
            while (cnt-- != 0) {// k칸 회전
                int[] tmp = disk[i].clone();
                switch (d) {
                    case 0: // 시계방향
                        for (int j = 0; j < M-1; j++) {
                            disk[i][j + 1] = tmp[j];
                        }
                        disk[i][0] = tmp[M - 1];
                        break;

                    case 1: // 반시계방향
                        for (int j = 0; j < M-1; j++) {
                            disk[i][j] = tmp[j + 1];
                        }
                        disk[i][M - 1] = tmp[0];
                        break;
                }
            }
        }
    }


    //평균 구하여 조정
    static void adjustDisk(){
        int sum = 0, cnt = 0;
        double avg = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (disk[i][j] != 0) {
                    cnt++;
                    sum += disk[i][j];
                }
            }
        }

        if(cnt == 0)
            return;

        avg = (double) sum / cnt;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (disk[i][j] != 0) {
                    if(disk[i][j] > avg)
                        disk[i][j]--;
                    else if(disk[i][j] < avg)
                        disk[i][j]++;
                }
            }
        }
    }

    //체크하여 인접 시 제거
    static boolean check() {
        boolean flag = false;

        for(int i = 1; i<=N; i++){
            for(int j = 0; j<M; j++){
                if(disk[i][j] != 0){
                    visited = new boolean[N+1][M];
                    if(BFS(i,j,disk[i][j])){
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }


        static boolean BFS(int i, int j, int num){
        boolean flag = false;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i,j});
        visited[i][j] = true;

        while(!queue.isEmpty()){
            int[] index = queue.poll();
            for(int k = 0; k < 4; k++){
                int r = index[0] + dr[k];
                int c = index[1] + dc[k];

                if(c == -1){
                    c = M - 1;
                }
                else if(c == M){
                    c = 0;
                }
                if(1 <= r && r <= N){
                    if(!visited[r][c]) {
                        if(disk[r][c] == num && disk[r][c] != 0) {
                            queue.offer(new int[] {r,c});
                            visited[r][c] = true;
                            if(disk[index[0]][index[1]] != 0) {
                                disk[index[0]][index[1]] = 0;
                            }
                            disk[r][c] = 0;
                            flag = true;
                        }
                    }
                }
            }
        }
        return flag;
    }
}

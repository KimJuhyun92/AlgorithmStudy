package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon15683_Watch {

    static int N, M;
    static int[][] map;
    static int[][] copyMap;
    static ArrayList<CCTV> cctvList;

    //좌 상 우 하
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0 && map[i][j] != 6) {
                    cctvList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        searchWatchArea(0, map);

        System.out.println(min);

    }

    public static void searchWatchArea(int depth, int[][] copyMap) {
        if (depth >= cctvList.size()) {
            int count = 0;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    if (copyMap[i][j] == 0)
                        count++;

            min = Math.min(min, count);
            return;
        }

        int type = cctvList.get(depth).type;

        switch (type) {
            case 1:
                for (int i = 0; i < 4; i++){

                    int[][] temp = initCopyMap(copyMap);

                    int nx = cctvList.get(depth).x;
                    int ny = cctvList.get(depth).y;
                    while(true){
                        nx = nx + dr[i];
                        ny = ny + dc[i];
                        if(nx >= N || ny >= M || nx < 0 || ny < 0 || temp[nx][ny] == 6)
                            break;
                        if(temp[nx][ny] == 0)
                            temp[nx][ny] = -1;
                    }
                    searchWatchArea(depth+1, temp);
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++){
                    int[][] temp = initCopyMap(copyMap);

                    for(int j = i; j < 4; j += 2){
                        int nx = cctvList.get(depth).x;
                        int ny = cctvList.get(depth).y;
                        while(true){
                            nx = nx + dr[j];
                            ny = ny + dc[j];
                            if(nx >= N || ny >= M || nx < 0 || ny < 0 || temp[nx][ny] == 6)
                                break;
                            if(temp[nx][ny] == 0)
                                temp[nx][ny] = -1;
                        }
                    }
                    searchWatchArea(depth+1, temp);
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++){
                    int[][] temp = initCopyMap(copyMap);

                    int cnt = 0;

                    for(int j = i; ; j++){
                        int nx = cctvList.get(depth).x;
                        int ny = cctvList.get(depth).y;
                        if(cnt == 2)
                            break;
                        int d = j % 4;
                        while(true){
                            nx = nx + dr[d];
                            ny = ny + dc[d];
                            if(nx >= N || ny >= M || nx < 0 || ny < 0 || temp[nx][ny] == 6)
                                break;
                            if(temp[nx][ny] == 0){
                                temp[nx][ny] = -1;
                            }
                        }
                        cnt ++;
                    }
                    searchWatchArea(depth+1, temp);
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++){
                    int[][] temp = initCopyMap(copyMap);

                    int cnt = 0;
                    for(int j = i; ; j++){
                        int nx = cctvList.get(depth).x;
                        int ny = cctvList.get(depth).y;
                        if(cnt == 3)
                            break;
                        int d = j % 4;
                        while(true){
                            nx = nx + dr[d];
                            ny = ny + dc[d];
                            if(nx >= N || ny >= M || nx < 0 || ny < 0 || temp[nx][ny] == 6)
                                break;
                            if(temp[nx][ny] == 0){
                                temp[nx][ny] = -1;
                            }
                        }
                        cnt ++;
                    }
                    searchWatchArea(depth+1, temp);
                }
                break;

            case 5:
                int[][] temp = initCopyMap(copyMap);
                for (int i = 0; i < 4; i++){
                    int nx = cctvList.get(depth).x;
                    int ny = cctvList.get(depth).y;
                    while(true){
                        nx = nx + dr[i];
                        ny = ny + dc[i];
                        if(nx >= N || ny >= M || nx < 0 || ny < 0 || temp[nx][ny] == 6)
                            break;
                        if(temp[nx][ny] == 0){
                            temp[nx][ny] = -1;
                        }
                    }
                }
                searchWatchArea(depth+1, temp);
                break;
        }
    }

    public static int[][] initCopyMap(int[][] originMap) {
        int[][] copyMap = new int[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                copyMap[i][j] = originMap[i][j];

        return copyMap;
    }

    public static class CCTV {
        int x;
        int y;
        int type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

}

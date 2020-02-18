package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. 미세먼지 확산
 - 확산 먼지 계산
 - 사방진으로 3차원 배열에 더할 값 저장
 - 한 사이클이 끝날 때 3차원 배열에 저장된 값 모두 더함

2. 공기청정기 가동
 - 윗바람과 아랫바람을 나누어 진행
 */

public class Baekjoon17144_MicroDust {

    static int R, C, T;
    static int[][][] map;
    static int[][][] copyMap;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static ArrayList<Integer> cleanerLocation = new ArrayList();
    static int[] changeUp = {2,0,3,1};
    static int[] changeDown = {2,1,3,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C][2];
        copyMap = new int[R][C][2];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if(map[i][j][0] == -1){
                    cleanerLocation.add(i);
                }
            }
        }

        for (int t = 0; t < T; t++) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    //먼지 확산
                    if (map[i][j][0] != 0 && map[i][j][0] != -1) {
                        spreadDust(i, j, map[i][j][0]);
                    }
                }
            }
            //확산된 먼지 가중치 더하기
            sumDust();

            for(int i=0; i<map.length; i++){
                for(int j = 0; j<map[i].length; j++)
                    copyMap[i][j] = map[i][j].clone();
            }

            //공기청정기 가동
            opAirCleaner(cleanerLocation.get(0), 0, changeUp);
            opAirCleaner(cleanerLocation.get(1), 0, changeDown);
        }

        System.out.println(remainingDust());
    }

    public static void opAirCleaner(int x, int y, int[] direction){

        //첫번째 위치는 공기청정기 오른쪽 칸으로 가야하므로 y += 1
        y += 1;
        //첫 공기청정기 오른쪽칸은 0으로 초기화
        map[x][y][0] = 0;
        for(int dir = 0; dir<4; dir++){
            while(true){
                int r = x + dr[direction[dir]];
                int c = y + dc[direction[dir]];

                if(r < 0 || c < 0 || r >= R || c >= C)
                    break;

                if(map[r][c][0] == -1)
                    break;

                map[r][c][0] = copyMap[x][y][0];

                x = r;
                y = c;
            }
        }
    }

    public static int remainingDust(){
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j][0] > 0) {
                    result += map[i][j][0];
                }
            }
        }

        return result;
    }

    public static void sumDust(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j][1] != 0) {
                    map[i][j][0] += map[i][j][1];
                    map[i][j][1] = 0;
                }
            }
        }
    }

    public static void spreadDust(int i, int j, int dustValue) {
        int areaCount = 0;
        int spreadValue = dustValue / 5;

        for (int dir = 0; dir < 4; dir++) {
            int r = i + dr[dir];
            int c = j + dc[dir];

            //범위를 벗어나거나 공기청정기를 만난다면 continue
            if (r < 0 || c < 0 || r >= R || c >= C || map[r][c][0] == -1)
                continue;
            else {
                areaCount++;
                //기존에 가중치 값이 존재할 수 있으므로 +=으로 합쳐주기
                map[r][c][1] += spreadValue;
            }
        }
        map[i][j][0] -= (spreadValue * areaCount);

    }

    public static void printMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(Arrays.toString(map[i][j]));
            }
            System.out.println();
        }
    }

}

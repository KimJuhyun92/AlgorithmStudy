package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. 바이러스 위치들을 모두 virusLocation 리스트로 저장
2. 해당 바이러스 위치에 대해 M개의 조합을 구하여 candidate에 저장
3. 상향식 for문으로 candidate size만큼 돌면서 virusLocation의 조합순번을 불러와 q에 넣고 토마토 진행하여 값을 min에 저장
4. min 출력
 */

public class Baejoon17142_Lab3 {
    static int N, M;
    static int[][] map;
    static Queue<int[]> q = new LinkedList<>();
    static ArrayList<Virus> virusLocation = new ArrayList<>();
    static ArrayList<int[]> candidate = new ArrayList<>();
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int flag = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusLocation.add(new Virus(i, j));
                } else if (map[i][j] == 0) {
                    flag = 1;
                }
            }
        }

        //감염될게 없을 시
        if (flag == 0) {
            System.out.println(0);
            System.exit(0);
        }

        doCombination(new int[virusLocation.size()], 0, virusLocation.size(), M, 0);


        for (int i = 0; i < candidate.size(); i++) {
            for (int num : candidate.get(i)) {
                q.offer(new int[]{virusLocation.get(num).x, virusLocation.get(num).y});
            }
            bfs();
        }

        if (min == Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(min);

    }

    /*
    virus : 바이러스 위치 조합을 넣을 빈 배열
    n : 조합을 만들기 위한 전체 대상 숫자
    r : 몇 개를 추출 할 것인지를 나타내는 수
    target : 0 ~ n으로 나열되어있는 원소들의 집합안에 어떤 숫자를 타겟으로 해서 배열에 집어넣을지를 고를 때
     */

    public static void bfs() {
        int count = 0;
        int temp = 0;

        //맵 복사
        int[][] tempMap = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, map[i].length);
        }

        while (!q.isEmpty()) {
            boolean spreadFlag = false;
            int size = q.size();

            for(int i = 0; i<size; i++){
                int[] location = q.poll();
                visited[location[0]][location[1]] = true;

                for (int dir = 0; dir < 4; dir++) {
                    int r = location[0] + dr[dir];
                    int c = location[1] + dc[dir];

                    if (r >= 0 && c >= 0 && r < N && c < N) {
                        if(!visited[r][c]){
                            if (tempMap[r][c] == 0) {
                                spreadFlag = true;
                                q.offer(new int[]{r, c});
                                tempMap[r][c] = 3;
                            }
                            else if(tempMap[r][c] == 2){
                                q.offer(new int[]{r, c});
                            }
                        }
                    }
                }
            }

            if(!spreadFlag){
                temp++;
            }
            else{
                count += ++temp;
                temp = 0;
            }

            if(checkMap(tempMap)){
                min = Math.min(min,count);
            }

        }



    }
    public static void doCombination(int[] virus, int index, int n, int r, int target) {
        if (r == 0) {
            int[] activeVirus = new int[M];
            for (int i = 0; i < index; i++) {
                activeVirus[i] = virus[i];
            }
            candidate.add(activeVirus);
        } else if (target == n)
            return;
        else {
            virus[index] = target;
            doCombination(virus, index + 1, n, r - 1, target + 1);
            doCombination(virus, index, n, r, target + 1);
        }
    }

    public static boolean checkMap(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printMap(int[][] map) {
        for (int i = 0; i < N; i++)
            System.out.println(Arrays.toString(map[i]));
    }

    public static void printComb() {
        for (int i = 0; i < candidate.size(); i++) {
            System.out.println(Arrays.toString(candidate.get(i)));
        }
    }

    public static class Virus {
        int x;
        int y;

        Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

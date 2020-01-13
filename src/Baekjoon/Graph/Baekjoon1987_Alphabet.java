package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baekjoon1987_Alphabet {

    static int R, C;
    static String[][] map;
    static boolean[][] visited;
    static HashMap<String, Boolean> alphabetList = new HashMap<>();
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int count = 1;
    static int max = 1;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new String[R][C];
        visited = new boolean[R][C];

//        for(int i = 0; i < R; i ++){
//            map[i] = br.readLine().split("");
//        }

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
                alphabetList.put(map[i][j], false);
            }
        }

        alphabetList.put(map[0][0], true);

        dfs(0, 0);

        System.out.println(max);
    }

    public static void dfs(int i, int j) {
        visited[i][j] = true;

        for (int dir = 0; dir < 4; dir++) {
            int r = i + dr[dir];
            int c = j + dc[dir];

            if (r >= 0 && c >= 0 && r < R && c < C) {
                if (!visited[r][c] && !alphabetList.get(map[r][c])) {
                    alphabetList.put(map[r][c], true);
                    max = Math.max(max, ++count);
                    dfs(r, c);
                }
            }
        }
        visited[i][j] = false;
        alphabetList.put(map[i][j], false);
        count--;
    }
}

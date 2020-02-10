package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
1. 체스판 맵 그리기
2. 체스판에 올라가있는 말을 체크하기 위한 맵 그리기
3. 파랑/흰색/빨강 일 경우의 분기점 나누기
4. 1000까지 루프 돌리며 한 칸에 말이 4개일 경우를 체크
 */

public class Baekjoon17837_NewGame2 {

    static int N, K;
    static int[][] map; // 체스판 맵
    static ArrayList<Integer>[][] pieceMap; // 말의 이동 경로에 따른 스택을 체크 할 맵
    static ArrayList<Piece> pieceList; // 말들의 위치와 방향 정보들을 담아 둘 리스트
    static int[] change = {1,0,3,2}; // 방향 전환 시 이용 할 배열
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        pieceMap = new ArrayList[N][N];
        pieceList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                pieceMap[i][j] = new ArrayList();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken()) - 1;

            pieceList.add(new Piece(x, y, direction));
            pieceMap[x][y].add(i);
        }

        while (count <= 1000) {
            for (int i = 0; i < K; i++) {
                int old_r = pieceList.get(i).x;
                int old_c = pieceList.get(i).y;
                int direction = pieceList.get(i).direction;
                int r = old_r + dr[direction];
                int c = old_c + dc[direction];

                //파랑색 or 범위 밖 일 경우
                //map[r][c] 에서 r 이나 c 가 범위를 넘겨 오류 발생 할 수 있으므로 map[r][c]는 마지막에 위치
                if (r >= N || c >= N || r < 0 || c < 0 || map[r][c] == 2) {
                    direction = change[direction];

                    pieceList.get(i).direction = direction;

                    r = old_r + dr[direction];
                    c = old_c + dc[direction];

                    //다음 칸이 파란색이라 바뀐 다음 방향이 범위 밖인 경우에는 그냥 for문을 한번 돌린다.
                    if (r >= N || c >= N || r < 0 || c < 0)
                        continue;
                }

                // 흰색일 경우
                if (map[r][c] == 0) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int p = pieceMap[old_r][old_c].size() - 1; p >= 0; p--) {
                        int piece = pieceMap[old_r][old_c].remove(p);
                        pieceList.get(piece).x = r;
                        pieceList.get(piece).y = c;
                        temp.add(piece);
                        if (piece == i) {
                            for (int p2 = temp.size() - 1; p2 >= 0; p2--) {
                                int piece2 = temp.get(p2);
                                pieceMap[r][c].add(piece2);
                            }
                            break;
                        }
                    }
                }

                // 빨강색일 경우
                else if (map[r][c] == 1) {
                    for (int p = pieceMap[old_r][old_c].size() - 1; p >= 0; p--) {
                        int piece = pieceMap[old_r][old_c].remove(p);
                        pieceList.get(piece).x = r;
                        pieceList.get(piece).y = c;
                        pieceMap[r][c].add(piece);
                        if (piece == i) {
                            break;
                        }
                    }
                }

                // 장기말 4개가 겹쳐 있을 시 종료 조건
                if (pieceMap[r][c].size() >= 4) {
                    System.out.println(++count);
                    return;
                }
            }

            // 종료 조건이 아닐 시 count ++
            count++;
        }

        System.out.println(-1);
    }


    public static class Piece {
        int x;
        int y;
        int direction;

        public Piece(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}

package Kakao2018_CodeFestival;

import java.util.Arrays;

public class Friends4Block {
    public static void main(String[] args) {
//        int m = 4;
//        int n = 5;
//        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

        int m = 6;
        int n= 6;
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

        System.out.println(solution(m,n, board));

    }

    static int height, weight;
    static int[] dr = {0, 1, 1};
    static int[] dc = {1,0,1};
    static String[][] map;
    static int[][] bomberMap;
    static int count = 1;
    static int sum = 0;
    static boolean flag = false;

    public static int solution(int m, int n, String[] board) {
        int answer = 0;

        height = m;
        weight = n;
        map = new String[m][n];

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                String piece = String.valueOf(board[i].charAt(j));
                map[i][j] = piece;
            }
        }

        while(true){
            bomberMap = new int[m][n];
            flag = false;

            for(int i = 0; i < m-1; i++){
                for(int j = 0; j < n-1; j++){
                    if(map[i][j].equals("0"))
                        continue;
                    searchSquare(i, j);
                }
            }
            removeSquare();
            adjustSquare();
            if(!flag)
                break;
        }
        answer = sum;

        return answer;
    }

    public static void adjustSquare(){
        for(int i = height-1; i > 0; i--){
            for(int j = 0; j < weight; j++){
                if(map[i][j].equals("0")){
                    for(int k = i; k > 0; k--){
                        if(map[k-1][j] == "0")
                            continue;
                        else{
                            map[i][j] = map[k-1][j];
                            map[k-1][j] = "0";
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void removeSquare(){
        for(int i = 0; i<height; i++){
            for(int j = 0; j<weight; j++){
                if(bomberMap[i][j] == 1){
                    map[i][j] = "0";
                    sum++;
                    flag = true;
                }
            }
        }
    }

    public static void searchSquare(int i, int j){
        String piece = map[i][j];

        for(int dir = 0; dir < 3; dir++){
            int r = i + dr[dir];
            int c = j + dc[dir];

            if(r < 0 || c < 0 || r >= height || c >= weight || map[r][c].equals("0")){
                break;
            }

            if(map[r][c].equals(piece)){
                count++;
            }
            else
                break;
        }
        //박스를 찾았다면 0으로 제거
        if(count == 4){
            bomberMap[i][j] = 1;
            for(int dir = 0; dir<3; dir++){
                int r = i + dr[dir];
                int c = j + dc[dir];
                bomberMap[r][c] = 1;
            }
        }
        count = 1;
    }

    static public void printMap(){
        for(int i = 0; i<height; i++)
            System.out.println(Arrays.toString(map[i]));
    }
}

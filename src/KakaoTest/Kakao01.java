package KakaoTest;

import java.util.ArrayList;
import java.util.Stack;

public class Kakao01 {
    public static void main(String args[]) {
//        int board[][] = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int board[][] = {{1, 1,1, 4, 5}, {5, 4, 3, 2, 1},{0,0,0,0,0},{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}};
//        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
//        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        int[] moves = {1,2,3};

        System.out.println("result " + new Kakao01().solution(board, moves));
    }

    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        ArrayList<Integer> result = new ArrayList<>();

        Stack<Integer> test = new Stack<>();

        int count = 0;

        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][moves[i] - 1] != 0) {
//                    result.add(board[j][moves[i] - 1]);
                    test.push(board[j][moves[i] - 1]);
                    board[j][moves[i] - 1] = 0;
                    break;
                }
            }
        }

//        for (int i = 0; i < result.size() - 1; i++) {
//            if (result.get(i) == result.get(i + 1)) {
//                result.remove(i);
//                result.remove(i);
//                i = i - 2;
//                count = count + 2;
//            }
//        }

        for (int i = 0; i < test.size() - 1; i++) {
            if (test.elementAt(i) == test.elementAt(i + 1)) {
                test.remove(i);
                test.remove(i);
                i = i - 2;
                if(i == -2){
                    i = -1;
                }
                count = count + 2;
            }
        }

        answer = count;

        return answer;
    }
}
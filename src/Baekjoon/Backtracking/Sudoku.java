package Baekjoon.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class EmptyPoint{
    int x, y;

    EmptyPoint(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Sudoku {

    static int[][] sudoku = new int[9][9];
    static ArrayList<EmptyPoint> empty = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i<9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<9; j++){
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if(sudoku[i][j] == 0){
                    empty.add(new EmptyPoint(j,i));
                }
            }
        }

        backTraking(0);

    }

    public static void backTraking(int index){
        if(index == empty.size()){
            for(int i = 0; i<9; i++){
                for(int j = 0; j<9; j++){
                    System.out.print(sudoku[i][j]+" ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        int x = empty.get(index).x;
        int y = empty.get(index).y;

        for(int i = 1; i<=9; i++){
            if(checkCol(x,i) && checkRow(y,i) && checkBox(x,y,i)){
                sudoku[y][x] = i;
                backTraking(index+1);
                sudoku[y][x] = 0;
            }
        }
    }

    public static boolean checkCol(int x, int num){
        for(int i = 0; i<9; i++){
            if(sudoku[i][x] == num)
                return false;
        }
        return true;
    }

    public static boolean checkRow(int y, int num){
        for(int i = 0; i<9; i++){
            if(sudoku[y][i] == num)
                return false;
        }
        return true;
    }

    public static boolean checkBox(int x, int y, int num){
        for(int i = (y/3)*3; i<(y/3)*3+3; i++){
            for(int j = (x/3)*3; j<(x/3)*3+3; j++){
                if(sudoku[i][j] == num)
                    return false;
            }
        }
        return true;
    }
}

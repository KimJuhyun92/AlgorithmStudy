package Programmers;

import java.util.Scanner;

public class N_Queen{
    static int N;
    static int cols[];
    static int count;

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        cols = new int[N];
        back_tracking(0);
        System.out.println(count);

    }

    public static void back_tracking(int dept){
        //모든 행에 퀸이 채워졌을경우 출력
        if(dept == N){
            count++;
        }
        else{
            for(int i=0;i<N;i++){
                cols[dept]=i;
                if(isPossible(dept)){
                    back_tracking(dept+1);
                }
            }
        }
    }

    public static boolean isPossible(int dept){
        for(int i=0;i<dept;i++){
            if(cols[i]==cols[dept] || Math.abs(dept-i)== Math.abs(cols[dept]-cols[i])){
                return false;
            }
        }
        return true;
    }
}
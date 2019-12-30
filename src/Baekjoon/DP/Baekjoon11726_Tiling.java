package Baekjoon.DP;

import java.util.Scanner;

public class Baekjoon11726_Tiling {
//    static public void main(String args[]){
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//
//        int[] d = new int[n+1];
//
//        d[0] = 1;
//        d[1] = 1;
//
//        //나머지
//        if(n>=2){
//            for(int i = 2; i <= n; i++){
//                d[i] = (d[i-1] + d[i-2]) % 10007;
//            }
//        }
//        System.out.println(d[n]);
//
//    }

    //저장할 수 있는 수의 범위가 없다면 마지막이 아닌 중간에 %10007을 하는 것과 마지막에 %10007을 하는 것의 결과가 똑같음을 증명할 수 있습니다. 여기서는 수가 int 범위까지만 갈 수 있기 때문에 중간에 %10007을 안 하면 오히려 잘못된 값으로 오버플로우하게 됩니다.
    static int[] d;
    static public void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        d = new int[n+1];

        d[0] = 1;
        d[1] = 1;

        System.out.println(tiling(n));
    }
    static public int tiling(int n){
        if(d[n] > 0)
            return d[n];
        else{
            d[n] = (tiling(n-1) + tiling(n-2)) % 10007;
            return d[n];
        }
    }
}

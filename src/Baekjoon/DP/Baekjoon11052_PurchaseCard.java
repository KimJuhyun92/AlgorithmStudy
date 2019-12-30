package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Baekjoon11052_PurchaseCard {
    static int card[];
    static int d[];
    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        card = new int[10001];
        d = new int[N+1];

        st = new StringTokenizer(br.readLine());
        int index = 1;
        while(st.hasMoreTokens()){
            card[index++] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=i; j++){
                d[i] = Math.max(d[i], d[i-j]+card[j]);
            }
        }

        System.out.println(d[N]);
//        System.out.println(maxValue(N));
    }

//    //Top-down 방식
//    static int maxValue(int n) {
//
//        if(d[n] > 0)
//            return d[n];
//
//        for(int i = 1; i<=n; i++){
//            d[n] = Math.max(d[n], maxValue(n-i) + card[i]);
//        }
//
//        return d[n];
//    }
}

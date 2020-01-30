package Baekjoon.DivideConquer;

import java.io.*;

public class HanoiTower {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
//        System.out.println((int)Math.pow(2,N) - 1);
        bw.write((1<<N)-1 + "\n");
        solve(N,1,3);
//        num(N);
    }

    static public void solve(int n, int x, int y) throws IOException {
        if (n == 0) return;
        solve(n - 1, x, 6 - x - y);
        bw.write(x + " " + y + "\n");
//        System.out.print(x+" ");
//        System.out.println(y);
        bw.flush();
        solve(n - 1, 6 - x - y, y);
    }

//    public static void num(int n){
//        if(n == 0) return;
//        num(n-1);
//        System.out.println(n);
//    }
}


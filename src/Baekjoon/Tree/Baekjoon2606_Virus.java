package Baekjoon.Tree;

import java.util.Scanner;

public class Baekjoon2606_Virus {

    static int[] parent;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        parent = new int[n + 1];

        init(n);

        for(int i = 0; i<m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            union(a,b);
        }

        int count = 0;
        for(int i = 2; i<=n; i++){
            //1의 루트가 존재 할 수 있기 때문에 1과 비교가 아닌 반드시 find(1)과 비교해야함
            if(find(i) == find(1)){
                count ++;
            }
        }

        System.out.println(count);
    }

    public static void init(int n) {
        for (int i = 0; i <= n; i++)
            parent[i] = i;
    }

    public static int find(int x) {
        if (parent[x] == x)
            return x;
        else {
            int p = find(parent[x]);
            parent[x] = p;
            return p;
        }
    }

    public static void union(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x != y)
            parent[y] = x;
    }

}
package Baekjoon.Tree;

import java.util.Scanner;

public class Baekjoon1717_ExpressionOfSet {

    static int[] parent;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        parent = new int[n+1];

        init(n);

        for(int i = 0 ; i < m; i++){
            int op = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(op == 1){
                int parent_a = Find(a);
                int parent_b = Find(b);
                if(parent_a == parent_b)
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
            else{
                Union(a,b);
            }
        }
    }

    public static void init(int n){
        for(int i = 0; i <= n; i++)
            parent[i] = i;
    }

    public static int Find(int x){
        if(parent[x] == x)
            return x;
        else{
            int p = Find(parent[x]);
            parent[x] = p;
            return p;
        }
    }

    public static void Union(int x, int y){
        x = Find(x);
        y = Find(y);

        if(x != y){
            parent[y] = x;
        }
    }

}

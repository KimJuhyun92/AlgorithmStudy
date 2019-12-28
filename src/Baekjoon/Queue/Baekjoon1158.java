package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1158 {
    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i = 1; i<=N; i++)
            q.offer(i);

        System.out.print("<");

        while(!q.isEmpty()) {
            if (q.size() == 1) {
                System.out.print(q.poll() + ">");
                break;
            }

            for (int i = 1; i < K; i++) {
                q.offer(q.peek());
                q.poll();
            }
            System.out.print(q.poll() + ", ");
        }

//        int count = 0;
//        while (q.size() != 0) {
//            count++;
//            if (count == K) {
//                if(q.size() == 1)
//                    System.out.print(q.poll() + ">");
//
//                else
//                    System.out.print(q.poll() + ", ");
//                count = 0;
//            }
//            else {
//                q.offer(q.poll());
//            }
//        }
    }
}

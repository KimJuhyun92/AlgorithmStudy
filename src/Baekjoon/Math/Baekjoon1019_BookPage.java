package Baekjoon.Math;

import java.util.Scanner;

public class Baekjoon1019_BookPage {

    static int[] num = new int[10];
    static int point = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int start = 1;
        int end = N;

        while(start <= end){

            while(start % 10 != 0 && start <= end) {
                cal(start);
                start++;
            }

            if(start > end)
                break;

            while(end % 10 != 9 && start <= end) {
                cal(end);
                end--;
            }

            start /= 10;
            end /= 10;

            for(int i = 0; i<10; i++){
                num[i] += (end - start + 1) * point;
            }

            point *= 10;
        }

        for(int i = 0; i<10; i++) {
            System.out.print(num[i] + " ");
        }

    }

    public static void cal(int n){
        while (n > 0) {
            num[n % 10] += point;
            n /= 10;
        }
    }
}

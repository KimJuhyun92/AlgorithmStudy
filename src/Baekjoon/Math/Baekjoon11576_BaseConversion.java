package Baekjoon.Math;

import java.util.Scanner;
import java.util.Stack;

public class Baekjoon11576_BaseConversion {

    static Stack<Integer> stack = new Stack<>();

    static public void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int base_A = sc.nextInt();
        int base_B = sc.nextInt();
        int index = sc.nextInt();

        int decimalNum = 0;

        //Pow 함수를 쓰지 않는 방법
//        for (int i = 0; i<index; i++) {
//            int x = sc.nextInt();
//            decimalNum = decimalNum * base_A + x;
//        }

        for(int i = index-1; i>=0; i--){
            int x = sc.nextInt();
            decimalNum += x * Math.pow(base_A, i);
        }

        convertBase(decimalNum, base_B);

        int size = stack.size();
        for(int i = 0; i<size; i++){
            if(i == size-1)
                System.out.print(stack.pop());
            else
                System.out.print(stack.pop() + " ");
        }
    }

    static void convertBase(int decimalNum, int base) {
        int num = decimalNum;

        while (num != 0) {
            int r = num % base;
            num /= base;
            stack.push(r);
        }
    }
}

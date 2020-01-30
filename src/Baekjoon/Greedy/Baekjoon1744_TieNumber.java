package Baekjoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1744_TieNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer> positiveNum = new Stack<>();
        Stack<Integer> negativeNum = new Stack<>();
        int zero = 0;
        int one = 0;
        int sum = 0;

        String number = "";
        while(!(number = br.readLine()).equals("")){
            if(number.charAt(0) == '-')
                negativeNum.push(Integer.parseInt(number));
            else if(number.equals("0"))
                zero++;
            else
                positiveNum.push(Integer.parseInt(number));
        }

        Collections.sort(negativeNum, Comparator.reverseOrder());
        Collections.sort(positiveNum);

        while(negativeNum.size() == 0 && positiveNum.size() == 0){

        }
    }
}

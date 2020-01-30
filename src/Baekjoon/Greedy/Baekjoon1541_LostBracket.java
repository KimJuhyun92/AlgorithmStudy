package Baekjoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1541_LostBracket {

    static boolean minusFlag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String op = br.readLine();

        int min = 0;

        String num = "";

        for(int i = 0; i<op.length(); i++){

            if(op.charAt(i) == '-'){
                if(!minusFlag){
                    minusFlag = true;
                    min += Integer.parseInt(num);
                    num = "";
                }
                else{
                    min -= Integer.parseInt(num);
                    num = "";
                }
            }
            else if(op.charAt(i) == '+'){
                if(!minusFlag){
                    min += Integer.parseInt(num);
                    num = "";
                }
                else{
                    min -= Integer.parseInt(num);
                    num = "";
                }
            }
            else{
                num += String.valueOf(op.charAt(i));
            }
        }

        if(minusFlag)
            min -= Integer.parseInt(num);
        else
            min += Integer.parseInt(num);

        System.out.println(min);
    }
}

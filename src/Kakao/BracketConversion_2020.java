package Kakao;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

import java.util.Stack;

public class BracketConversion_2020 {

    public static void main(String args[]){
        String p1 = "(()())()";
        String p2 = "()";
        String p3 = "()(())()";
        String p4 = "(()";
        String p5 = "(()))(";

        String case2 = ")(";
        String case3 = "()))((()";
        String p8 = "";

        System.out.println(solution(case3));

    }

    public static String solution(String p) {
        String answer = "";
        String temp = "";

        String w[] = new String[2];

        //빈 문자열일 경우 반환
        if(p.isEmpty())
            return p;

        //옳바른 문자열일 경우 반환
        if(isCorrect(p))
            return p;

        //그렇지 않을 경우
        else{
            w = seperateStr(p);

            if(isCorrect(w[0])) {
                answer = w[0] + solution(w[1]);
            }

            else{
                answer += "(";
                answer += solution(w[1]);
                answer += ")";

                System.out.println("answer + v = " + answer);

                int lenth = w[0].length();

                if(lenth == 2)
                    w[0] = "";
                else
                    w[0] = w[0].substring(1,lenth-1);

                System.out.println("u = " + w[0]);

                for(int i = 0; i<w[0].length(); i++){
                    if(w[0].charAt(i) == '(')
                        temp += ")";
                    else
                        temp += "(";
                }
                System.out.println("replaced u = " + temp);
                answer += temp;
                System.out.println("answer + temp = " + (answer += temp));
            }
        }

        return answer;
    }

    public static String[] seperateStr(String p){

        String[] w = {"",""};
        int cnt1 = 0;
        int cnt2 = 0;

        for(int i = 0; i<p.length(); i++){
            char temp = p.charAt(i);

            if(temp == '('){
                w[0] += temp;
                cnt1++;
            }
            else{
                w[0] += temp;
                cnt2++;
            }

            if(cnt1 == cnt2) break;
        }
        w[1] = p.substring(cnt1+1,p.length());

        return w;
    }

    public static boolean isCorrect(String p){

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i<p.length(); i++){
            char temp = p.charAt(i);
            if(temp == '(')
                stack.push(temp);
            else {
                if (!stack.isEmpty())
                    stack.pop();
            }
        }

        if(!stack.isEmpty())
            return false;

        return true;
    }
}

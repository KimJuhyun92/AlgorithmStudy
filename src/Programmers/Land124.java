package Programmers;

public class Land124 {
    class Solution {
        public String solution(int n) {
            String answer = "";

            while(n>0){

                if(n%3 == 0){
                    answer = "4"+answer;
                    n = n/3;
                    n-=1;
                } else{
                    answer = n%3 + answer;
                    n = n/3;
                }

            }

            return answer;
        }
    }
}

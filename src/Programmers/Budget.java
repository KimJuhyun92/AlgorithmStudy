package Programmers;
import java.util.Arrays;

public class Budget {
    class Solution {
        public int solution(int[] budgets, int M) {
            int answer = 0;

            Arrays.sort(budgets);

            for(int i=0;i<budgets.length;i++){
                if(budgets[i]>M/(budgets.length-i)){
                    answer = M/(budgets.length-i);
                    break;
                }
                M -= budgets[i];
            }
            if(answer==0){
                answer=budgets[budgets.length-1];
            }


            return answer;
        }
    }
}

import java.util.*;
public class Test {
    class Solution {
        public int solution(int[][] routes) {
            Arrays.sort(routes, new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    if(o1[0] <= o2[0]){
                        return -1;
                    }
                    return 1;
                }
            });

            int answer = 0;
            int last = routes[0][1];
            for(int i=1; i<routes.length; i++){

                if(routes[i][0] <= last){
                    if(last >= routes[i][1]){
                        last = routes[i][1];
                    }
                }else{
                    answer += 1;
                    last = routes[i][1];
                }

            }
            answer += 1;

            return answer;
        }
    }
}

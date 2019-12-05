package Programmers;

import java.util.Arrays;
import java.util.Comparator;

public class TrafficCamera {
    public static void main(String args[]){

        int[][] routes = {
                {-20,15},
                {-14,-5},
                {-18,-13},
                {-5,-3}
        };

        int[][] test1 = {{-7,0},{0,10}};
        int[][] test2 = {{-7,0}};
        int[][] test3 = {{-2,-1},{-1,2},{2,3}};

        System.out.println("result = " + solution(test3));

    }

    public static int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                final int temp1 = o1[0];
                final int temp2 = o2[0];
                return Integer.compare(temp1,temp2);
            }
        });

        int pivot = routes[0][1];

        for(int i = 0; i<routes.length-1; i++){
            if(routes[i+1][0] <= pivot && routes[i+1][1] <= pivot){
                pivot = routes[i+1][1];
            }
            else if(routes[i+1][0] > pivot){
                pivot = routes[i+1][1];
                answer+=1;
            }
        }

        return answer;
    }
}

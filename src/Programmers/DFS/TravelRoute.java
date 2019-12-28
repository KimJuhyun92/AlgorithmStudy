package Programmers.DFS;

import java.util.ArrayList;
import java.util.Arrays;

public class TravelRoute {
    static ArrayList<String> route = new ArrayList<>();

    public static void main(String args[]){
        String[][] ticket1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] ticket2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};

        System.out.println(Arrays.toString(solution(ticket1)));
    }

    static public String[] solution(String[][] tickets) {
        String[] answer = {};
        boolean[] visit = new boolean[tickets.length];



        String destination = "";

        for(int i = 0; i<tickets.length; i++){

        }

        return answer;
    }

    static public void DFS(int index){

    }


}

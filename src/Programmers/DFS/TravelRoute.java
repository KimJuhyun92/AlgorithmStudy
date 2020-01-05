package Programmers.DFS;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TravelRoute {
    static ArrayList<Route> route;
    static boolean[] visited;
    static ArrayList<String> result;

    public static void main(String args[]) {
        String[][] ticket1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] ticket2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};

        System.out.println(Arrays.toString(solution(ticket1)));
    }

    static public String[] solution(String[][] tickets) {
        String[] answer = {};
        route = new ArrayList<>();
        visited = new boolean[tickets.length];
        result = new ArrayList<>();

        for (int i = 0; i < tickets.length; i++)
            route.add(new Route(tickets[i][0], tickets[i][1]));

        //출발점이 같다면 도착점을 기준으로 정렬, 아닌 경우 출발점 기준 정렬
        Collections.sort(route, new Comparator<Route>() {
            @Override
            public int compare(Route o1, Route o2) {
                if(o1.start.equals(o2.start))
                    return o1.end.compareTo(o2.end);
                else
                    return o1.start.compareTo(o2.start);
            }
        });

        result.add("ICN");

        for(int i = 0; i<tickets.length; i++){
            if(route.get(i).start.equals("ICN")){
                visited[i] = true;
                result.add(route.get(i).end);
                DFS(1);
                visited[i] = false;
            }
        }

        for(int i = 0; i< result.size(); i++)
            System.out.println(result.get(i));

        return answer;
    }


    static public void DFS(int cnt) {
        if(cnt == visited.length){
            return;
        }
        for(int i = 0; i<route.size(); i++){
            if(route.get(i).start.equals(result.get(result.size() - 1)) && !visited[i]){
                visited[i] = true;
                result.add(route.get(i).start);
                printResult();
                DFS(++cnt);
                visited[i] = false;
                result.remove(i);
            }
        }
    }

    public static void printResult(){
        System.out.println("test " + result);
        System.out.println("-----------------------");
    }

    public static class Route {
        private String start;
        private String end;

        public Route(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }
}

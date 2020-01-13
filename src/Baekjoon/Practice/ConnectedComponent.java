    package Baekjoon.Practice;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.StringTokenizer;

    public class ConnectedComponent {

        static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        static boolean visited[];
        static int count = 0;

        public static void main(String args[]) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for(int i = 0; i<=N; i++){
                map.add(new ArrayList<>());
            }

            visited = new boolean[N+1];

            for(int i = 0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map.get(x).add(y);
                map.get(y).add(x);
            }

            for(int i = 1; i <= N; i++){
                if(!visited[i]){
                    dfs(i);
                    count ++;
                }
            }

            System.out.println(count);

        }

        public static void dfs(int v){
            visited[v] = true;

            for(int value : map.get(v)){
                if(!visited[value]){
                    dfs(value);
                }
            }
        }
    }

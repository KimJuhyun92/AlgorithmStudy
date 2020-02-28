package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon15686_ChickenDelivery {

    static int N, M;
    static ArrayList<int[]> house = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> candidate = new ArrayList<>();
    static int result = Integer.MAX_VALUE;;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1)
                    house.add(new int[]{i, j});
                else if (num == 2)
                    chicken.add(new int[]{i, j});
            }
        }

        doCombination(new int[chicken.size()], 0, chicken.size(), M, 0);

        //치킨집 후보 사이즈 만큼 순회
        for (int i = 0; i < candidate.size(); i++) {
            int sum = 0;
            //집 갯수 만큼 순회
            for (int j = 0; j < house.size(); j++) {
                int r1 = house.get(j)[0];
                int c1 = house.get(j)[1];
                int temp = Integer.MAX_VALUE;
                //현재 집과 비교하여 치킨집 후보 중 어느곳이 더 가까운지 확인
                for(int num : candidate.get(i)){
                    int r2 = chicken.get(num)[0];
                    int c2 = chicken.get(num)[1];
                    int distance = Math.abs(r1 - r2) + Math.abs(c1 - c2);
                    if(distance < temp){
                        temp = distance;
                    }
                }
                sum += temp;
            }
            if(sum < result)
                result = sum;
        }

        System.out.println(result);

    }

    public static void doCombination(int[] chicken, int index, int n, int r, int target) {
        if (r == 0) {
            int[] aliveChicken = new int[M];
            for (int i = 0; i < index; i++) {
                aliveChicken[i] = chicken[i];
            }
            candidate.add(aliveChicken);
        } else if (target == n)
            return;
        else {
            chicken[index] = target;
            doCombination(chicken, index + 1, n, r - 1, target + 1);
            doCombination(chicken, index, n, r, target + 1);
        }
    }
}

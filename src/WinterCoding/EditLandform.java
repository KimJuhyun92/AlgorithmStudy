package WinterCoding;

public class EditLandform {
    public static void main(String[] args) {
        int[][] land1 = {{1, 2}, {2, 3}};
        int P1 = 3;
        int Q1 = 2;

        int[][] land2 = {{4, 4, 3}, {3, 2, 2}, { 2, 1, 0 }};
        int P2 = 5;
        int Q2 = 3;

        System.out.println(solution(land1,P1,Q1));

    }

    public static long solution(int[][] land, int P, int Q) {
        long answer = -1;

        long max = Integer.MIN_VALUE;
        long min = Integer.MAX_VALUE;

        for(int i = 0; i<land.length; i++){
            for(int j = 0; j<land[0].length; j++){
                max = Math.max(max, land[i][j]);
                min = Math.min(min, land[i][j]);
            }
        }

        long mid = 0;

        while(max >= min){
            mid = (max + min)/ 2;

            if(max == min)
                break;

            long leftCost = calCost(land,P,Q,mid);
            long rightCost = calCost(land,P,Q,mid+1);

            if(leftCost == rightCost)
                break;

            if(leftCost > rightCost)
                min = mid+1;
            else
                max = mid;
        }

        answer = calCost(land,P,Q,mid);

        return answer;
    }

    public static long calCost(int[][] land, int P, int Q, long mid){
        long cost = 0;

        for(int i = 0; i<land.length; i++){
            for(int j = 0; j<land[0].length; j++){
                if(land[i][j] < mid)
                    cost += (mid - land[i][j]) * P;
                else if(land[i][j] > mid)
                    cost += (land[i][j] - mid) * Q;
            }
        }
        return cost;
    }
}

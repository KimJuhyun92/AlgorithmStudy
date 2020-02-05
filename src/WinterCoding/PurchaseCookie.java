package WinterCoding;

public class PurchaseCookie {
    public static void main(String[] args) {
        int[] cookie1 = {1,1,2,3};
        int[] cookie2 = {1,2,4,5};

        System.out.println(solution(cookie1));
    }

    public static int solution(int[] cookie) {
        int answer = -1;

        int[] sum = new int[cookie.length];
        int frontSum, endSum;

        sum[0] = cookie[0];

        for(int i = 0; i<cookie.length-1; i++){
            sum[i+1] = sum[i] + cookie[i+1];
        }

        for(int m = 0; m < cookie.length; m++){
            frontSum = sum[m];
            endSum = 0;
            for(int r = m+1; r < cookie.length; r++){
                endSum += cookie[r];
                if(frontSum < endSum || answer >= endSum){
                    continue;
                }
                if (frontSum == endSum) {
                    answer = Math.max(answer, endSum);
                    break;
                }
                for(int l = 0; l < m; l++) {
                    if (frontSum - sum[l] == endSum) {
                        answer = Math.max(answer, endSum);
                        break;
                    }
                }
            }
        }

        if(answer == -1){
            answer = 0;
        }

        return answer;
    }
}

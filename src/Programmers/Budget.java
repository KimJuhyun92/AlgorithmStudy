package Programmers;

import java.util.Arrays;

public class Budget {
    public int solution(int[] budgets, long M) {

        int answer = 0;

        Arrays.sort(budgets);

        long sum = 0;

        for (int i = 0; i < budgets.length; i++) {
            sum += budgets[i];
        }

        int start = 0;
        int end = budgets[budgets.length - 1];
        int mid = (start + end) / 2;

        while (!(start > mid)) {
            sum = 0;

            for (int i = 0; i < budgets.length; i++) {
                if (mid < budgets[i])
                    sum += mid;
                else
                    sum += budgets[i];
            }

            if (sum == M) {
                answer = mid;
                break;
            } else if (sum > M) {
                end = mid - 1;
            } else {
                start = mid + 1;
                answer = mid;
            }

            mid = (end + start) / 2;
        }

        return answer;
    }

}

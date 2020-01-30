package WinterCoding;

import java.util.Arrays;

public class NumberGame {
    public static void main(String[] args) {

//        int[] a = {5, 1, 3, 7};
//        int[] b = {2, 2, 6, 8};

        int[] a = {2, 2, 2, 2};
        int[] b = {1, 1, 1, 1};

        System.out.println(solution(a, b));

    }

    public static int solution(int[] a, int[] b) {
        int answer = 0;

        int a_length = a.length;
        int index = b.length-1;

        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = a_length - 1; i >= 0; i--) {
            if (a[i] < b[index]) {
                answer++;
                index--;
            }
        }

        return answer;
    }
}

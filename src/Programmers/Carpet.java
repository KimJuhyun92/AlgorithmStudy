package Programmers;

public class Carpet {

    public static void main(String args[]) {

        int brown = 24;
        int red = 24;

        System.out.println("result = " + new Carpet().solution(brown,red)[0]);
        System.out.println("result = " + new Carpet().solution(brown,red)[1]);

    }

    public int[] solution(int brown, int red) {
        int[] answer = new int[2];

        int x = 0;
        int y = 0;

        for (int i = 1; i <= red; i++) {
            if (red % i == 0) {
                x = red / i;
                y = i;
                if ((2 * (x + 2) + 2 * y) == brown) {
                    answer[0] = i + 2;
                    answer[1] = x + 2;
                    break;
                }
            }
        }
        return answer;
    }
}

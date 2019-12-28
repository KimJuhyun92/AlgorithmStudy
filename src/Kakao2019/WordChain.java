package Kakao2019;

import java.util.Arrays;

public class WordChain {

    /*
    - 탈락하는 경우
    1. 같은 단어를 말 할 경우
    2. 시작 단어가 이전의 마지막 단어와 다를 경우

    - 모든 단어가 다를 경우
    [0,0] 일 확률 있음
    예외사항 : 탈락 2번일 경우

    - 예외 사항
    한 글자인 단어는 인정되지 않음
     */

    public static void main(String args[]){
        int n = 0;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};

        System.out.println(solution(n,words));
    }

    static public int[] solution(int n, String[] words) {
        int[] answer = {};

        String[][] word = new String[n][1];

        System.out.println(Arrays.toString(answer));
        return answer;
    }
}

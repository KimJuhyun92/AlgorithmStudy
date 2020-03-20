package Kakao;

import java.util.ArrayList;

public class Kakao2018_NewsClustering {
    public static void main(String[] args) {
//        String str1 = "FRANCE";
//        String str2 = "french";

//        String str1 = "E=M*C^2";
//        String str2 = "e=m*c^2";

        String str1 = "aa1+aa2";
        String str2 = "AAAA12";

        System.out.println(solution(str1, str2));
    }

    public static int solution(String str1, String str2) {
        double answer = 0;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        //다중 집합을 저장하기 위한 ArrayList
        ArrayList<String> set1 = new ArrayList<>();
        ArrayList<String> set2 = new ArrayList<>();

        set1 = disjoin(set1, str1, str1.length());
        set2 = disjoin(set2, str2, str2.length());

        if(set1.size() == 0 && set2.size() == 0){
            return 65536;
        }

        int min = 0, max = 0;

        loop:
        for (int i = 0; i < set1.size(); i++) {
            for (int j = 0; j < set2.size(); j++) {
                if (set1.get(i).equals(set2.get(j))) {
                    set2.remove(j);
                    min++;
                    max++;
                    continue loop;
                }
                if(j == set2.size()-1){
                    max++;
                }
            }
        }

        max += set2.size();
//
//        System.out.println(min);
//        System.out.println(max);

        answer = (double)min/max * 65536;


        return (int)answer;
    }

    //문자열 str1, str2를 다중집합으로 분리시키는 함수
    static ArrayList<String> disjoin(ArrayList<String> set, String str, int size) {
        int count = 0;

        while (count != size - 1) {
            char first = str.charAt(count);
            char second = str.charAt(count + 1);

            if ((97 <= first && first <= 122) && (97 <= second && second <= 122)) {
                String word = String.valueOf(str.charAt(count)) + String.valueOf(str.charAt(count + 1));
                set.add(word);
            }
            count++;
        }

        return set;
    }

}

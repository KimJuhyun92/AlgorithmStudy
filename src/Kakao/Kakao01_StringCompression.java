package Kakao;

public class Kakao01_StringCompression {
    public static void main(String[] arg){
        String s = "ababcdcdababcdcd";
        System.out.println("result = "+ new Kakao01_StringCompression().solution(s));
    }

    public int solution(String s){
        int answer = 0;

        if(s.length() == 1) return 1;

        String pivot = "";

        int min = 0;
        int count = 0;

        for(int i = 1; i <= s.length()/2; i++) {

            int size = s.length();
            pivot = s.substring(0, i);
            int index = i;

            while ((index + i) <= s.length()) {

                //비교할 대상
                String temp = s.substring(index, index + i);

                if (pivot.equals(temp)) {
                    size = size - i;
                    index += i;
                    count++;
                } else {
                    pivot = s.substring(index, index + i);
                    index += i;

                    if (count != 0) {
                        if(count < 9) size ++;
                        else if(count < 99) size += 2;
                        else if(count < 999) size += 3;
                        else size += 4;
                        count = 0;
                    }
                }
            }

            if (count != 0) {
                if(count < 9) size ++;
                else if(count < 99) size += 2;
                else if(count < 999) size += 3;
                else size += 4;
                count = 0;
            }

            if (i == 1) {
                min = size;
            } else {
                if (min > size) {
                    min = size;
                }
            }
        }

        answer = min;

        return answer;
    }
}


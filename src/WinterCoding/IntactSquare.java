package WinterCoding;

/*
격자점을 기준으로 규칙
1. 격자점이 있는 경우
    지워지는 격자 = 가로+세로-1
2. 격자점이 없는 경우
    지워지는 격자 = 최대공약수 * (긴)세로/(짧은)가로
 */

import java.util.StringTokenizer;

public class IntactSquare {
    public static void main(String args[]){
        int w = 8;
        int h = 12;
        long result = 0;

        result = solution(w,h);

        System.out.println(result);
    }

    public static long solution(int w,int h) {
        long answer = 1;
        long a = w;
        long b = h;
        long area = a * b;

        answer = area - ((w + h) - gcd(a,b));

        return answer;
    }

//    public static int gcd(int a, int b)
//    {
//        if (b==0)
//            return a;
//        return gcd(b, a%b);
//    }

    public static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }


}
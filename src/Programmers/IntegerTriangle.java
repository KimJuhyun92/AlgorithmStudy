package Programmers;

import java.util.Arrays;

public class IntegerTriangle {
    public static void main(String argsp[]){
        int[][] triangle = {
                    {7},
                   {3,8},
                  {8,1,0},
                 {2,7,4,4},
                {4,5,2,6,5}
        };

        System.out.println(solution(triangle));
    }

    public static int solution(int[][] triangle) {
        int answer = 0;

        int depth = triangle.length;

        if(depth == 1){
            return triangle[0][0];
        }

        /*
        i는 depth를 의미
        j는 각 depth의 모든 배열값을 들어가기 위한 변수를 의미
         */

        for(int i = 1; i < depth; i++){
            for(int j = 0; j <= i; j++){

                //배열의 첫번째 원소일 경우
                if(j == 0)
                    triangle[i][j] += triangle[i-1][j];

                //배열의 마지막 원소일 경우
                else if(j == i)
                    triangle[i][j] += triangle[i-1][j-1];

                //배열의 중간 원소들일 경우 값 비교
                else{
                    int temp1 = triangle[i][j];
                    int temp2 = triangle[i][j];
                    if( (temp1 += triangle[i-1][j-1]) > ( temp2 += triangle[i-1][j]) )
                        triangle[i][j] = temp1;
                    else
                        triangle[i][j] = temp2;
                }
            }
        }


        Arrays.sort(triangle[depth-1]);


        return triangle[depth-1][depth-1];
    }
}

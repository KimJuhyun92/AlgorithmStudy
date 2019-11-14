package Kakao;

public class Kakao05_PillarAndBeam {
    public void main(String args[]){

        // x,y,a,b
        // a -> 0 기둥, 1 보
        // b -> 0 삭제, 1 설치

        int[][] build_frame = {
                {1,0,0,1},{1,1,1,1},
                {2,1,0,1},{2,2,1,1},
                {5,0,0,1},{5,1,0,1},
                {4,2,1,1},{3,2,1,1}
        };

//        System.out.println(solution(5,build_frame));
        System.out.println("result " + build_frame.length);

    }

    public int[][] solution(int n, int[][] build_frame) {

        int[][] answer = {};
        int[][] structure = new int[n][n];



        return answer;
    }
}

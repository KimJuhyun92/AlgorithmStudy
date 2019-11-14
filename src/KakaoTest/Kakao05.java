//package KakaoTest;
//
//public class Kakao05 {
//    public static void main(String args[]){
//
//        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
//        int k = 3;
//
//        System.out.println("result = " + new Kakao05().solution(stones,k));
//
//    }
//
//    public static int solution(int[] stones, int k) {
//        int number = 0;
//
//        boolean onGoing = true;
//
//        while(onGoing){
//            int count = 1;
//            for(int i = 0; i < stones.length; i++){
//                --stones[i];
//            }
//            for(int i = 0; i<stones.length; i++){
//
//            }
//            number ++;
//        }
//
////        while(onGoing){
////            int count = 1;
////            for(int i=0; i<stones.length; i++){
////                if(stones[i]!=0){
////                    stones[i]--;
////                    count=1;
////                }
////                else{
////                    count++;
////                    if(count>k){
////                        onGoing = false;
////                        break;
////                    }
////                    else {
////                        continue;
////                    }
////                }
////            }
////            if(onGoing)
////            {
////                number++;
////            }
////        }
////        return number;
//    }
//}

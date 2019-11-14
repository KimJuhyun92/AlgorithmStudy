package KakaoTest;

public class Kakao04 {

    public static void main(String args[]){
        long k = 10;
        long[] room_number = {1,3,4,1,3,1};


        for(int i = 0; i<room_number.length; i++){
            System.out.println("result = " + new Kakao04().solution(k, room_number)[i]);

        }

    }

    public long[] solution(long k, long[] room_number){

        boolean[] roomIsEmpty = new boolean[(int)k];
        long[] result = new long[room_number.length];

        for (int i = 0; i < k; i++) {
            roomIsEmpty[i] = false;
        }

        for (int i = 0; i < room_number.length; i++) {
            if (roomIsEmpty[(int)room_number[i] - 1] == false) {
                roomIsEmpty[(int)room_number[i] - 1] = true;
                result[i] = room_number[i];
            }
            else{
                for (int j = (int)room_number[i]; j < k; j++) {
                    if (roomIsEmpty[j] == false) {
                        roomIsEmpty[j] = true;
                        result[i] = j+1;
                        break;
                    }
                }
            }
        }

        return result;
    }
}

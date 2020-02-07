package WinterCoding;

public class CollectSticker2 {

    public static void main(String[] args) {
        int[] sticker1 = {14, 6, 5, 11, 3, 9, 2, 10};
        int[] sticker2 = {1, 3, 2, 5, 4};

        System.out.println(solution(sticker1));
    }

    public static int solution(int sticker[]) {
        int answer = 0;

        if(sticker.length < 3){
            if(sticker.length == 1){
                return sticker[0];
            }
            else{
                return sticker[0] > sticker[1] ? sticker[0] : sticker[1];
            }
        }

        int[] dp1 = new int[sticker.length];
        int[] dp2 = new int[sticker.length];

        // 0번째를 떼는 경우
        dp1[0] = sticker[0];
        dp1[1] = sticker[0]; //0번쨰를 떼는 경우이기 떄문에 1번째는 지워지므로 1번째의 최대값은 0번째의 값.

        for(int i = 2; i<sticker.length-1; i++){
            dp1[i] = Math.max(dp1[i-2] + sticker[i], dp1[i-1]);
        }

        answer = dp1[dp1.length-2];

        //0번째를 안 뗄 경우
        dp2[0] = 0;
        dp2[1] = sticker[1];

        for(int i = 2; i<sticker.length; i++ ){
            dp2[i] = Math.max(dp2[i-2] + sticker[i], dp2[i-1]);
        }

        answer = Math.max(answer, dp2[sticker.length-1]);

        return answer;
    }
}

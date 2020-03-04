package WinterCoding;

public class JumpAndTeleportation {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        int ans = 0;

        while(n != 0){
            //순간이동
            if(n % 2 == 0){
                n /= 2;
            }
            //건전지 사용
            else{
                n -= 1;
                ans++;
            }
        }

        return ans;
    }
}

package WinterCoding;

public class Winter02 {

    public static void main(String args[]) {

        int n = 6;

        for(int i = 0; i<Math.pow(2,6)-1; i++){
            System.out.println("result = "+ new Winter02().solution(n)[i]);
        }

        System.out.println("size = "+ new Winter02().solution(n).length);


    }

    public int[] solution(int n) {
        int[] answer = {};

        int length = 0;
        int middle = 0;

        length = (int)Math.pow(2,n) - 1;

        answer = new int[length];

        for(int i = 1; i<=n; i++) {
            int size = (int)Math.pow(2,i) - 1;
            for(int j = 1; j < size; j++){
                if(i==1){
                    answer[0] = 0;
                }
                else{
                    middle = (size+1)/2 - 1;
                    answer[middle] = 0;
                    if(middle - j >= 0 && answer[middle - j ] == 0){
                        answer[middle+j] = 1;
                    }
                    else if (middle -j >= 0 && answer[middle-j]!=0)
                        answer[middle+j] = 0;
                }
            }
        }
        return answer;
    }
}

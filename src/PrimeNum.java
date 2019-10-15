import java.util.HashSet;
import java.util.Set;

public class PrimeNum {
    public static void main(String[] ar){
        String numbers = "17";
        System.out.println("result = "+ new PrimeNum().solution(numbers));
    }

    public int solution(String numbers) {
        int answer = 0;
        int max = 1000000;

        //소수를 저장할 배열
        boolean primeNumSet[] = new boolean[max];

        //numbers를 하나씩 쪼개어 넣어 둘 배열
        int[] seperatedNum = new int[numbers.length()];

        //순열을 통해 만들어 낼 숫자 리스트
        Set<Integer> permList = new HashSet<>();

        int length = numbers.length();

        //입력받은 숫자를 하나씩 쪼개어 넣는 과정
        for(int i =0; i<numbers.length(); i++){
            seperatedNum[i] = numbers.charAt(i) - '0';
        }

        //조합순열을 통해 숫자 리스트 생성 구현해야함
        for(int i =1; i < length; i++){
            perm(seperatedNum, 0, length, i, permList);
        }

        //소수를 찾아 배열에 저장
        findPrimeNum(primeNumSet);

        //소수배열과 숫자배열 비교 (로직 수정 필요할듯)
        for(int i = 2; i<max; i++){
            if(primeNumSet[i] && permList.contains(i))
                answer++;
        }
        return answer;
    }

    public void perm(int[] arr, int depth, int n, int k,Set permList) {
        if (depth == k) { // 한번 depth 가 k로 도달하면 사이클이 돌았음. 출력함.
            StringBuilder a = new StringBuilder();
            for (int i = 0; i < k; i++) {
//                System.out.print(arr[i]);
                a.append(arr[i]);
            }
            permList.add(a);
            return;
        }
        for (int i = depth; i < n; i++) {
            swap(arr, i, depth);
            perm(arr, depth + 1, n, k, permList);
            swap(arr, i, depth);
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }


    public void findPrimeNum(boolean primeNumSet[]){
        int max = 1000000;

        for(int i =2; i<max; i++)
            primeNumSet[i] = true;

        for(int i =2; i<(int)Math.sqrt(max); i++){
            if(primeNumSet[i] == true){
                for(int j = i; i*j < max; j++)
                    primeNumSet[i*j] = false;
            }
        }
    }

}
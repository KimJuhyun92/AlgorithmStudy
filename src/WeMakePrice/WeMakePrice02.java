package WeMakePrice;

import java.util.*;

public class WeMakePrice02 {
    public static void main(String args[]){

        System.out.println(solution(9999));
    }


    public static int solution(int n){
        int[] arr = new int[10000];
        List<Integer> list = lexicalOrder(n);
        for(int i=0;i<list.size();i++){
            arr[list.get(i)]=i;
        }


        return arr[n];
    }

    public static List<Integer> lexicalOrder(int n) {
        List<String> list = new ArrayList<>();
        for(int i=1;i<=n;i++){
            list.add(String.valueOf(i));
        }

        Collections.sort(list, new Comparator<String>(){
            public int compare(String a, String b){
                int i=0;
                while(i<a.length()&&i<b.length()){
                    if(a.charAt(i)!=b.charAt(i)){
                        return a.charAt(i)-b.charAt(i);
                    }
                    i++;
                }

                if(i>=a.length()){
                    return -1;
                }
                return 1;
            }
        });

        List<Integer> result = new ArrayList<>();
        for(String s: list){
            if(s.contains("0")){
                continue;
            }else{
                result.add(Integer.parseInt(s));
            }
        }

        return result;
    }
}
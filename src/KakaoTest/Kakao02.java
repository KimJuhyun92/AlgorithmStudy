package KakaoTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Kakao02 {
    class Solution {
        public class Point {
            List list;
            Point (List list) {
                this.list = list;
            }
        }
        public int[] solution(String s) {
            boolean state = false;
            List<List<Integer>> list = new LinkedList<>();
            List<Integer> l = null;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < s.length() - 1; i++) {

                char c = s.charAt(i);
                if (c == '{') {
                    state = true;
                    l = new LinkedList<>();
                } else if (c == '}') {
                    state = false;
                    if (sb.toString().length() != 0) {
                        l.add(Integer.valueOf(sb.toString()));
                    }
                    list.add(l);
                } else if (c == ',') {
                    if (!state) {
                        sb = new StringBuilder();
                        continue;
                    }
                    l.add(Integer.valueOf(sb.toString()));
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }

            }
            Point p[] = new Point[list.size()+1];

            for (int i = 0; i < list.size(); i++) {
                List<Integer> ll = list.get(i);

                p[ll.size()] = new Point(ll);
            }
            int resultstate[] = new int[100001];
            List<Integer> last = p[p.length-1].list;
            for (int i = 0 ; i < last.size(); i++) {
                resultstate[last.get(i)] += 1;
            }

            int result[] = new int[p[p.length-1].list.size()];
            int index = 0;
            for (int i = 1; i < p.length; i++) {
                List<Integer> ll = p[i].list;
                for (int j = 0; j < ll.size(); j++) {
                    if (resultstate[ll.get(j)] != 0) {
                        result[index++] = ll.get(j);
                        resultstate[ll.get(j)] -= 1;
                    }

                }

            }
            return result;
        }
    }

    public static void main(String args[]) {
//        char a = '1';
//        int b = a;
//        System.out.println("a : " + b);

        new Kakao02().new Solution().solution("{{4,2,3},{3},{2,3,4,1},{2,3}}");

    }

}
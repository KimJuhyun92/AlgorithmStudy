package Kakao2019;

import javax.xml.bind.SchemaOutputResolver;

public class CandidateKey {
    static public void main (String args[]){
        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},
                {"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};

        System.out.println(solution(relation));
    }

    static public int solution(String[][] relation) {
        int answer = 0;

        int col = relation.length;
        int row = relation[0].length;

        if(col == 1){
            return 1;
        }

        for(int i = 0; i<col; i++){
            for(int j = 0; j<row; j++){
                if(relation[i][j] == relation[i][j+1]){
                    break;
                }
                else
                    answer++;
            }
        }

        return row;
    }
}

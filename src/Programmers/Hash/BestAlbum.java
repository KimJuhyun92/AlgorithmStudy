package Programmers.Hash;

import java.util.*;

public class BestAlbum {
    static HashMap<String, Integer> extractedGenre;
    static ArrayList<Song> songs;
    static List<String> maxSortedGenre;
    //결과 값을 담을 리스트
    static ArrayList<Integer> result;
    //장르별 각 객체들의 정보를 담아 둘 리스트의 리스트
    static List<ArrayList<Song>> sortedSongs;

    public static class Song implements Comparable<Song> {
        String genre = "";
        int playCount = 0;
        int code = 0;

        public Song(String genre, int playCount, int code) {
            this.genre = genre;
            this.playCount = playCount;
            this.code = code;
        }

        //        @Override
//        public int compareTo(Song song) {
//            return song.playCount - this.playCount;
//        }
        @Override
        public int compareTo(Song o) {
            if (this.playCount == o.playCount) {
                if (this.code > o.code) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (this.playCount < o.playCount) {
                return 1;
            } else {
                return -1;
            }
        }
    }


    public static void main(String args[]) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        int[] result = new int[4];

        System.out.println(Arrays.toString(solution(genres, plays)));

    }

    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        extractedGenre = new HashMap<>();
        songs = new ArrayList<>();
        //결과값을 넣을 곳
        result = new ArrayList<>();
        sortedSongs = new ArrayList<>();

        //장르별 max값 추출 및 각 노래 정보 저장
        for (int i = 0; i < genres.length; i++) {
            if (extractedGenre.containsKey(genres[i]))
                extractedGenre.put(genres[i], extractedGenre.get(genres[i]) + plays[i]);
            else
                extractedGenre.put(genres[i], plays[i]);

            Song song = new Song(genres[i], plays[i], i);
            songs.add(song);
        }

        //내림차순으로 장르 sort
        maxSortedGenre = new ArrayList<>(extractedGenre.keySet());
//        Collections.sort(maxSortedGenre, (o1, o2) -> (extractedGenre.get(o2).compareTo(extractedGenre.get(o1))));
        Collections.sort(maxSortedGenre, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return extractedGenre.get(o2).compareTo(extractedGenre.get(o1));
            }
        });

        for (int i = 0; i < maxSortedGenre.size(); i++) {
            //장르가 추가되면서 리스트 객체도 하나 생성
            sortedSongs.add(new ArrayList<>());
            for (int j = 0; j < songs.size(); j++) {
                if (maxSortedGenre.get(i).equals(songs.get(j).genre)) {
                    sortedSongs.get(i).add(songs.get(j));
                }
            }
        }

        //각 장르별 array 내림차순 정렬
        for (int i = 0; i < sortedSongs.size(); i++) {
            Collections.sort(sortedSongs.get(i));
        }

        for (int i = 0; i < sortedSongs.size(); i++) {
            if (sortedSongs.get(i).size() >= 2) {
                result.add(sortedSongs.get(i).get(0).code);
                result.add(sortedSongs.get(i).get(1).code);
            } else
                result.add(sortedSongs.get(i).get(0).code);
        }

        answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

}

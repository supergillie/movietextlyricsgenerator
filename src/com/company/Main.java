package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1 sug upp texten alla rader
        List<String> rows = null;
        try {
            rows = Files.readAllLines(new File("resources/movie.txt").toPath(), Charset.defaultCharset() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        //printWithInfo(rows, "ListaInnanJustering");

        // 2 ta bort tomma rader, rader som startar med en siffra,  <i>... och <i>
        removeCrapRowsFromList(rows);
        //System.out.println("efter remove: " + rows.size());
        //printWithInfo(rows, "ListaInnanJustering");


        // 3 remove crap from beginning of rows
        removeStartCrap(rows);
        //printWithInfo(rows, "Lista efter crap tagits bort");

        // multiplewordsrows to separate rows
        List<String> oneWordList;
        oneWordList = separateLinesToWordsAndRemoveCrap(rows);
        //printWithInfo(oneWordList, "lista med ett ord på varje rad");


        List<String> song = new ArrayList<String>();
        //song = listToSong(oneWordList, 7, 12);

        song = listToSongPairIdea(oneWordList, 8, 3);


        printWithInfo(song, getRandomWordFromList(oneWordList) + " ");

        // save first song oak_cleaf_cluster.txt
        // for us nothing changes
    }

    private static List<String> listToSongPairIdea(List<String> oneWordList, int rows, int wordsOnEachLine) {
        List<String> song = new ArrayList<>();
        String songLine= "";

        for(int row=0; row< rows; row++){
            for(int word = 0; word < wordsOnEachLine; word++){
                songLine = songLine + " " + getRandomWordPairFromList(oneWordList);
            }
            song.add(songLine);
            songLine = "";
        }

        return(song);

    }

    private static List<String> listToSong(List<String> oneWordList, int wordsOnEachLine, int rows) {
        List<String> song = new ArrayList<>();
        String songLine= "";

        for(int row=0; row< rows; row++){
            for(int word = 0; word < wordsOnEachLine; word++){
                songLine = songLine + " " + getRandomWordFromList(oneWordList);
            }
            song.add(songLine);
            songLine = "";
        }

        return(song);
    }

    private static String getRandomWordPairFromList(List<String> oneWordList) {
        int random = Double.valueOf(Math.random()*( oneWordList.size() )).intValue();
        return oneWordList.get(random) + " " + oneWordList.get(random +1 ) ;
    }

    private static String getRandomWordFromList(List<String> oneWordList) {
        int random = Double.valueOf(Math.random()*( oneWordList.size() )).intValue();
        return oneWordList.get(random);
    }

    private static List<String> separateLinesToWordsAndRemoveCrap(List<String> rows) {
        List<String> separated = new ArrayList<>();
        try {
            for(String s : rows)
            {
                String[] arr = s.split(" ");
                for ( String ss : arr) {
                    separated.add(ss
                            .replace("</i>","")
                            .replace("?","")
                            .replace(".","")
                            .replace(",","")
                            .replace("\"","")
                            .replace("a ","")
                            .replace("the ","")
                            .replace("I","")
                            .replace("!","")

                    );
                }
            }
        } catch (Exception e) {
        }
        return(separated);
    }

    private static void removeStartCrap(List<String> rows) {
        for (int i = 0; i < rows.size(); i++) {
            if ((rows.get(i).startsWith("- ")) || (rows.get(i).startsWith("..."))) {
                //System.out.println("crap detected: " + rows.get(i));
               // rows.set(i, rows.get(i).replaceAll("...", ""));
                rows.set(i, rows.get(i).replaceAll("- ", ""));
               // rows.set(i, rows.get(i).replaceAll(".", ""));
                //rows.set(i, rows.get(i).replaceAll("</i>", ""));
            }
        }
    }

    private static void removeCrapRowsFromList(List<String> rows) {

        for(int i = 0; i<rows.size(); i++){
            if ((rows.get(i).length() == 0)
                    || rows.get(i).startsWith("<")
                    || Character.isDigit(rows.get(i).charAt(0)))
            {
              // System.out.println(" rows.remove(value); " + rows.get(i));
               rows.remove(i);
               i--;
            }
        }

    }

    private static void printWithInfo(List<String> rows, String info) {
        System.out.println(info + " beginning.");
        for(String s : rows){
            System.out.println(s);
        }

       // System.out.println(info + " end. Number of rows: " + rows.size());
    }
}

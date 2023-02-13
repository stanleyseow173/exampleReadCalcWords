package sg.edu.nus.iss.app;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException{

        Path p = Paths.get(args[0]);
        File f = p.toFile();

        Map<String, Integer> wordFreq = new HashMap<>();
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> valuelist = new ArrayList<>();
        
        //open a file as input stream
        InputStream is = new FileInputStream(f);
        //convert the input stream to a reader byte -> char (2 byte)
        InputStreamReader isr = new InputStreamReader(is);
        //read whole lines
        BufferedReader br = new BufferedReader(isr); //just need to memorise this process

        String line;
        int numWords = 0;
        String editedLine;
        while ((line = br.readLine()) != null){
            System.out.printf("> %s\n", line.toUpperCase());
            editedLine = line.replace(",","");
            editedLine = editedLine.replaceAll("[^a-zA-Z0-9 _-]", "");
            // editedLine = editedLine.replace(".","");
            // editedLine = editedLine.replace("!","");
            // editedLine = editedLine.replace(":","");
            // editedLine = editedLine.replace(";","");
            // editedLine = editedLine.replace("[","");
            // editedLine = editedLine.replace("]","");
            // editedLine = editedLine.replace("(","");
            // editedLine = editedLine.replace(")","");
            // editedLine = editedLine.replace("{","");
            // editedLine = editedLine.replace("}","");
            // editedLine = editedLine.replace("?","");
            // editedLine = editedLine.replace("\"","");
            // editedLine = editedLine.replace("\'","");
            // editedLine = editedLine.replace("}","");
            String[] words = editedLine.split(" ");
            numWords += words.length;
            //Iterate all the words in the line
            for (String w: words){
                String t = w.trim().toLowerCase();
                if (t.length()<=0){
                    continue;
                }
                if (!wordFreq.containsKey(t)){
                    //if word is not in map, initalize the word with frequency of 1
                    wordFreq.put(t,1);
                } else{
                    //if word is in map, then increment the count
                    int c = wordFreq.get(t);
                    wordFreq.put(t,c+1);
                }
            }
        }

        //Get a list of all the words
        Set<String> words = wordFreq.keySet();
        for (String w: words){
            int count = wordFreq.get(w);
            System.out.printf(": %s = %d\n",w,count);
        }

        for (Map.Entry<String, Integer> entry : wordFreq.entrySet()) {
            valuelist.add(entry.getValue());
        }

        Collections.sort(valuelist, Collections.reverseOrder());
        for (Integer int2 : valuelist) {
            for (Map.Entry<String, Integer> entry : wordFreq.entrySet()) {
                if (entry.getValue().equals(int2)) {
                    sortedMap.put(entry.getKey(), int2);
                }
            }
        }

        System.out.printf("Number of words: %d\n", numWords);
        System.out.printf("Number of unique words: %d\n", words.size());
        System.out.printf("Percentage of unique words: %f\n", words.size()/(float)numWords);

        System.out.println("After sorting...");
        System.out.println(sortedMap);
        br.close();
        isr.close();
        is.close();

    }
}


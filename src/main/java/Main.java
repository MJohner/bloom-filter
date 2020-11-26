import com.google.common.collect.SortedMultiset;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.SortedSet;

public class Main {

    public static void main(String[] args) throws IOException {
        doTheTest();
    }

    public static void doTheTest() throws FileNotFoundException{
        BloomFilter b = new BloomFilter(60000, 0.5);
        File wordsInFilter = new File("src/main/resources/englishOnlyWords.txt");
        Scanner s = new Scanner(wordsInFilter);
        while(s.hasNextLine()){
            b.add(s.nextLine());
        }
        s.close();
        File fileWordsNotInFilter = new File("src/main/resources/germanOnlyWords.txt");
        s = new Scanner(fileWordsNotInFilter);
        int total = 0;
        double contains = 0;
        String currentString;
        while(s.hasNextLine()){
            currentString = s.nextLine();
            if(b.contains(currentString)){
                contains++;
            }
            total++;
        }
        b.printSpecs();
        System.out.println("Total checked words: " + total);
        System.out.println("Total false positive: " + contains);
        System.out.println("False positive rate: " + (contains / total) );
    }

    private static void makeDiffOfFiles() throws IOException {
        HashSet<String> s1 = new HashSet<>();
        File wordsInFilter = new File("src/main/resources/englishOnlyWords.txt");
        Scanner s = new Scanner(wordsInFilter);
        while(s.hasNextLine()){
            s1.add(s.nextLine());
        }
        File fileWordsNotInFilter = new File("src/main/resources/german_words.txt");
        s = new Scanner(fileWordsNotInFilter);
        String currentString;
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/germanOnlyWords.txt"));
        while(s.hasNextLine()){
            currentString = s.nextLine();
            if(!s1.contains(currentString)) {
                writer.write(currentString);
                writer.write("\n");
            }
        }
        writer.close();
    }
}

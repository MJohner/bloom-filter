import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BloomFilerTest {

    private BloomFilter b;

    @Test
    public void addWord(){
        b = new BloomFilter(10,0.1);
        b.add("Test");

        assertTrue(b.contains("Test"));
        assertFalse(b.contains("Kest"));
    }

    @Test
    public void testFalsePositive() throws FileNotFoundException {
        BloomFilter b = new BloomFilter(60000, 0.5);
        File wordsInFilter = new File("src/main/resources/englishOnlyWords.txt");
        File fileWordsNotInFilter = new File("src/main/resources/germanOnlyWords.txt");
        assertTrue(falsePositive(b, wordsInFilter, fileWordsNotInFilter) < 0.5);
        b = new BloomFilter(60000, 0.1);
        assertTrue(falsePositive(b, wordsInFilter, fileWordsNotInFilter) < 0.1);
        b = new BloomFilter(60000, 0.01);
        assertTrue(falsePositive(b, wordsInFilter, fileWordsNotInFilter) < 0.01);
        b = new BloomFilter(60000, 1.0);
        assertEquals(1, falsePositive(b, wordsInFilter, wordsInFilter));
    }

    private static double falsePositive(BloomFilter b, File f1, File f2) throws FileNotFoundException {
        Scanner s = new Scanner(f1);
        while(s.hasNextLine()){
            b.add(s.nextLine());
        }
        s.close();
        s = new Scanner(f2);
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
        return contains / total;
    }
}

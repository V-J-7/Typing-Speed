package Model;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class TypingTest {
    String[] paragraphs=
            {
                    "The quick brown fox jumps over the lazy dog",
                    "The cat chased the mouse around the house",
                    "She danced under the moonlight, feeling free",
                    "They walked through the forest, hearing the birds sing",
                    "I can't wait to see the new movie this weekend",
                    "The sky was painted with shades of pink and orange at sunset",
            };
    private HashMap<String,Long> statistics =new HashMap<>();
    public void typingTest(){
        Scanner sc=new Scanner(System.in);
        Random random=new Random();
        int index=random.nextInt(paragraphs.length);
        String paragraph=paragraphs[index];
        System.out.println(paragraph);
        Instant start=Instant.now();
        String input=sc.nextLine();
        Instant end=Instant.now();
        Duration duration= Duration.between(start, end);
        long correctCharacters =0;
        for (int i=0;i<input.length();i++){
            if (paragraph.charAt(i)==input.charAt(i)){
                correctCharacters++;
            }
        }
        statistics.put("Time Taken",duration.getSeconds());
        long wpm=(correctCharacters*12)/(duration.getSeconds());
        statistics.put("WPM",wpm);
        statistics.put("Errors",input.length()-correctCharacters);
    }
    public HashMap<String,Long> getStatistics() {
        return statistics;
    }
}

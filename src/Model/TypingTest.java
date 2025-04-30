package Model;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class TypingTest {
    Random random = new Random();
    Scanner sc=new Scanner(System.in);
    String[][] shortLengthParagraphs =
            {
                    //Easy
                    {
                            "The quick brown fox jumps over the lazy dog",
                            "The cat chased the mouse around the house",
                            "She danced under the moonlight feeling free",
                            "They walked through the forest, hearing the birds sing",
                            "The sky was painted with shades of pink and orange at sunset"
                    },

                    //Medium
                    {
                            "The engineer adjusted the settings to optimize performance — it wasn’t easy.",
                            "\"Are you coming to the meeting tomorrow?\" she asked, checking her watch.",
                            "His handwriting was almost illegible, but the signature was unmistakable.",
                            "After hours of work, they finally solved the puzzle — the solution was right in front of them.",
                            "The teacher wrote “excellent” on the paper and added a star (well deserved)"
                    },

                    //Hard
                    {
                            "As the scientist examined the data (which was both complex and extensive), she noticed a subtle anomaly",
                            "\"If we don’t act soon,\" the manager declared, \"we’ll lose everything — time is critical.\"",
                            "The intricate details of the experiment demanded a level of precision that most people couldn’t comprehend.",
                            "While the philosopher pondered the meaning of existence, his thoughts were interrupted by a constant hum",
                            "\"Did you hear the news?\" he asked, barely able to contain his excitement. \"The impossible just happened!\""
                    }
            };

    String[][] mediumLengthParagraphs = {
            // Easy
            {
                    "The dog barked loudly at the mailman who was walking down the street it was a regular occurrence every morning and the dog never got tired of it sometimes the mailman would stop for a moment to pet the dog and it would wag its tail happily",
                    "She opened the door and took a deep breath inhaling the fresh morning air the sky was still a little cloudy but the sun peeked through the clouds with a promise of warmth she stepped outside and walked down the path to the garden",
                    "The children were playing tag in the park running in circles and laughing loudly they dodged trees and jumped over benches the park was filled with the sound of their footsteps and the occasional shout of triumph when someone managed to tag another child",
                    "He sat down at his desk ready to get to work his computer booted up slowly and he had to wait a few minutes before he could start typing the pile of papers on his desk was getting bigger and he knew it was time to tackle the to do list that had been piling up",
                    "The rain began to fall soft at first then harder the sound of raindrops hitting the windows was calming but soon it became louder as the rain turned into a storm the wind picked up and the trees outside began to sway as the sky darkened"
            },

            // Medium
            {
                    "After the meeting she turned to her colleague and said \"I think we can improve this proposal before presenting it to the client What do you think\" He nodded in agreement and they got to work immediately",
                    "As the deadline loomed closer the team scrambled to finish the project Everyone had a part to play some were coding others were writing reports but they all worked toward the same goal",
                    "He stared at the whiteboard contemplating the complex math problem \"I can't figure this out\" he muttered to himself scratching his head Just then his friend walked by and offered a suggestion",
                    "The phone rang It was her boss asking if she was free to discuss the new proposal \"I’ll be right there\" she said grabbing her coat and heading out the door",
                    "The storm was fierce with wind howling and rain pouring down in sheets As they huddled inside one of the group members said \"We need to be ready for whatever comes next The forecast doesn't look good\""
            },

            // Hard
            {
                    "Despite the setbacks the team pressed on determined to meet the deadline The last minute changes had thrown off their schedule but they were confident that they could finish on time after all they had done it before",
                    "\"Did you hear the news\" he asked eyes wide with excitement \"They've just announced the new policy and it could change everything for us\" He paused waiting for the reaction The room fell silent",
                    "The report was due tomorrow and the pressure was mounting They had worked long hours editing and revising the draft but there was still so much to be done It felt like they were running out of time",
                    "As the board members sat in silence the CEO spoke up stating that the company’s future would depend on the decision made that day \"This could be a make or break moment\" she said glancing at everyone around the table",
                    "The investigation was far from over but they had gathered enough evidence to make a preliminary conclusion However some questions remained unanswered What had really happened that night And who was responsible for the discrepancies"
            }
    };

    String[][] longLengthParagraphs = {
            // Easy
            {
                    "The sun was setting behind the hills casting a golden glow over the landscape as the wind began to pick up gently rustling the leaves on the trees and causing the flowers to sway in rhythm with the breeze the warm evening air filled his lungs as he walked slowly down the path leading toward the small cottage nestled by the lake where his family used to spend their summers long ago",
                    "Every morning she would wake up early to watch the sunrise from her bedroom window the sky would gradually change from a dark blue to soft shades of pink and orange as the world around her slowly began to come alive the birds would start chirping and the air would become crisp and fresh with the promise of a new day ahead she always enjoyed those peaceful moments before the hustle of the day began",
                    "They had been friends for years but lately things had started to feel different there were unspoken tensions between them and small disagreements seemed to pop up more frequently though they tried to brush it off and continue as they always had the underlying change was noticeable still they found themselves spending more time together than apart reflecting on the shared memories that made their friendship unique",
                    "The city streets were crowded with people rushing in every direction cars honking and buses screeching to a halt as the sounds of the busy urban environment filled the air he navigated his way through the crowd dodging people on their phones and street vendors hawking their goods all while keeping his focus on the towering building ahead where he was headed for an important meeting",
                    "The mountain trail was steep and challenging but the view at the top made it all worth it after hours of hiking they reached the summit just as the sun was beginning to set casting a warm glow over the valley below the landscape stretched out for miles in every direction and for a moment everything felt peaceful and serene as if the world had slowed down just for them"
            },

            // Medium
            {
                    "After years of hard work and dedication, she had finally earned the promotion she had been striving for. It wasn’t an easy road; countless late nights, difficult decisions, and sacrifices had come with the territory, but she had made it. As she sat at her new desk, looking out at the view of the bustling city below, she couldn’t help but reflect on how far she had come. It was a proud moment, but she knew the real work was just beginning.",
                    "The old library had always been a quiet and comforting place for him, a place where he could escape into the world of books and lose himself in stories that seemed far more interesting than the real world. But as he sat there one afternoon, he noticed something strange—a book that had never been there before, lying on the table. It looked worn and aged, as if it had been sitting there for years waiting to be discovered, and he felt an odd sense of curiosity stir within him.",
                    "As the storm raged outside, the group gathered around the fireplace inside the cozy cabin. The wind howled through the trees, and the rain beat against the windows, but the warmth of the fire and the company of friends kept them safe and secure. They shared stories, laughed together, and enjoyed each other’s company, all while the weather outside seemed to grow more intense. It was a night that they would remember for a long time, a reminder of the bond they shared in the face of the elements.",
                    "The concert hall was packed with people, all eagerly waiting for the performance to begin. The lights dimmed, and the audience fell into a hush as the orchestra took their seats. He could feel the anticipation building in the air, the collective excitement of everyone in the room. When the first notes of the symphony rang out, it was as if time stood still, and for the next few hours, they were all carried away by the music, lost in its beauty and power.",
                    "The project was nearly complete, but there was still so much left to do. As he looked at the final draft of the report, he realized that there were several sections that still needed improvement. It wasn’t just about finishing the task—it was about doing it right. He could feel the pressure mounting, but he remained focused, knowing that the final product would be worth the effort. As the deadline approached, he tightened his grip on the work ahead, determined to finish strong."
            },

            // Hard
            {
                    "The investigation was beginning to unravel, but they were nowhere near the answers they sought. Evidence was scattered, and leads that seemed promising at first now appeared to be dead ends. Yet, they pressed on, determined to uncover the truth no matter the cost. The deeper they dug, the more complex the case became, and the more they realized that everything was connected in ways they hadn’t even imagined. They couldn’t afford to stop now—not when they were so close to understanding the full scope of what had happened.",
                    "It had been a long, grueling journey to get here, but they had finally reached the summit. The path had been rocky, and there were times when they doubted they would make it, but the view from the top was worth every bit of struggle. As they stood there, taking in the breathtaking landscape, they couldn’t help but feel a sense of accomplishment and awe. There was something about standing on top of the world, looking down at the vast expanse below, that made all the hardships feel insignificant in comparison.",
                    "\"Are you sure we should go through with this?\" she asked, her voice shaking slightly as she looked at the complex blueprint in front of her. The plan was risky, and there was no telling what might go wrong, but the stakes were higher than they had ever been. \"We’ve come this far, and we can’t turn back now,\" he replied, his voice steady and determined. They exchanged a glance, knowing that this decision would change everything, for better or worse.",
                    "The night was dark, and the only sounds were the occasional rustle of leaves in the wind and the distant calls of nocturnal animals. They walked silently through the forest, each step carefully measured as they navigated the winding trail. The weight of the situation hung heavy in the air, and no one spoke for what felt like an eternity. Finally, one of them broke the silence. \"We need to be careful—this isn’t over yet.\"",
                    "It was an unexpected turn of events, one that left everyone in the room speechless. The meeting had started off as just another routine discussion, but now the fate of the project—and possibly the company—was in jeopardy. No one had anticipated such a dramatic shift in direction, and the pressure to find a solution was mounting by the second. As the room fell into silence, each person sat, weighing the consequences of the new proposal, and wondering how they would proceed."
            }
    };


    private HashMap<String,Integer> statistics =new HashMap<>();

    public void shortTests(int level){
        String[] paragraphs= shortLengthParagraphs[level];
        int index=random.nextInt(paragraphs.length);
        String paragraph=paragraphs[index];
        printWrapped(paragraph, 80);
        Instant start=Instant.now();
        String input=sc.nextLine();
        Instant end=Instant.now();
        Duration duration= Duration.between(start, end);
        int correctCharacters =0;
        int minLength=Math.min(paragraph.length(),input.length());
        for (int i=0;i<minLength;i++){
            if (paragraph.charAt(i)==input.charAt(i)){
                correctCharacters++;
            }
        }
        int wpm=(correctCharacters*12)/(int)(duration.getSeconds());
        statistics.put("WPM",wpm);
        statistics.put("Errors",input.length()-correctCharacters);
        System.out.println("WPM:"+wpm);
        System.out.println("Errors:"+(input.length()-correctCharacters));
    }

    public void mediumTests(int level){
        String[] paragraphs= mediumLengthParagraphs[level];
        int index=random.nextInt(paragraphs.length);
        String paragraph=paragraphs[index];
        printWrapped(paragraph, 80);
        Instant start=Instant.now();
        String input=sc.nextLine();
        Instant end=Instant.now();
        Duration duration= Duration.between(start, end);
        int correctCharacters =0;
        int minLength=Math.min(paragraph.length(),input.length());
        for (int i=0;i<minLength;i++){
            if (paragraph.charAt(i)==input.charAt(i)){
                correctCharacters++;
            }
        }
        int wpm=(correctCharacters*12)/(int)(duration.getSeconds());
        statistics.put("WPM",wpm);
        statistics.put("Errors",input.length()-correctCharacters);
        System.out.println("WPM:"+wpm);
        System.out.println("Errors:"+(input.length()-correctCharacters));
    }

    public void longTests(int level){
        String[] paragraphs= longLengthParagraphs[level];
        int index=random.nextInt(paragraphs.length);
        String paragraph=paragraphs[index];
        printWrapped(paragraph, 80);
        Instant start=Instant.now();
        String input=sc.nextLine();
        Instant end=Instant.now();
        Duration duration= Duration.between(start, end);
        int correctCharacters =0;
        int minLength=Math.min(paragraph.length(),input.length());
        for (int i=0;i<minLength;i++){
            if (paragraph.charAt(i)==input.charAt(i)){
                correctCharacters++;
            }
        }
        int wpm=(correctCharacters*12)/(int)(duration.getSeconds());
        statistics.put("WPM",wpm);
        statistics.put("Errors",input.length()-correctCharacters);
        System.out.println("WPM:"+wpm);
        System.out.println("Errors:"+(input.length()-correctCharacters));
    }

    public int getStatistic(String statistic) {
        return statistics.get(statistic);
    }
    private void printWrapped(String text, int lineWidth) {
        for (int i = 0; i < text.length(); i += lineWidth) {
            System.out.println(text.substring(i, Math.min(text.length(), i + lineWidth)));
        }
    }
}

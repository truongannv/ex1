import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String randomWord() {
        Random random = new Random();
        int index = random.nextInt(Hangman.words.length);
        String Word = Hangman.words[index];
        return Word;
    }

    public static boolean checkGuess(char input, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (input == str.charAt(i)) {
                return true;
            }
        }
        return false;
    }
    
    public static String updatePlaceholder(String str, char input,Integer i) {
        String newStr = "";
        char c = str.charAt(i);
        newStr = str.substring(0,i)+input+str.substring(i+1);
        return newStr;
    }

    public static void main(String[] args) {
        String Word = randomWord();
        String newWord = "";
        for (int i = 0; i < Word.length(); i++) {
            newWord = newWord + "_";
        }

        int flag = 0;
        int wordCorrect = 0;
        char Guess = ' ';
        System.out.println("Guess: " + Guess);
        String misses = "";

        while (flag<=6 && wordCorrect<Word.length()){
            String gallow = Hangman.gallows[flag];
            System.out.println(gallow);
            System.out.println("Word: " + newWord);
            System.out.println("Misses: " + misses);
            Scanner sc = new Scanner(System.in);
            System.out.println("Guess:");
            String str = sc.next();
            Guess = str.charAt(0);
            boolean check = checkGuess(Guess, Word);
            Integer index = null;

            if (check==false) {
                misses = misses + Guess;
                flag++;
            }else{
                wordCorrect++;
                for (int i = 0; i < Word.length(); i++) {
                    if(Guess==Word.charAt(i)){
                        index = i;
                        break;
                    }
                }
                newWord = updatePlaceholder(newWord,Guess,index);
                System.out.println(newWord);
            }

            if(wordCorrect==Word.length()){
                System.out.println("GOOD WORK!");
            }
            if(flag>6){
                System.out.println(gallow);
                System.out.println("RIP!");
                System.out.println("The word was: "+Word);
            }
        }
    }
}
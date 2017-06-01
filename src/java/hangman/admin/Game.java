package hangman.admin;

import hangman.data.WordGetter;
import hangman.data.WordDataBase;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
 
    private Random generator;  
    private int state;  
    
    public String word; 
    private StringBuffer displayWord; 
    private ArrayList<String> wordlist;  
    private List<Character> letterList = new ArrayList<Character>();
    
    public Game() {
        generator = new Random();
        wordlist=null;
        word = randomWord();
        createDisplayWord();
        state=1;       
        letterList.clear();
        
    }
    
    public int getState(){
        return state;
    }
    
    public String getWord(){
        return word;
    }
    
    public List<Character> getLetterList(){
        System.out.println(letterList);
        return letterList;
    }
    
    public String getDisplayWord(){
        return displayWord.toString();
    }
    
    public void startNewGame() {
        state = 1;
        word = randomWord(); 
        createDisplayWord();
        letterList.clear();
    }
    
    
    public int playGame(char guess) {
            System.out.println(isValidInput(guess));
            
            guess = Character.toLowerCase(guess);
            
            if(!isValidInput(guess) || isUsed(guess)) 
            {
              return 4;   // case for bad input
            }
            
            letterList.add(guess);
            
            boolean correctGuess = updateDisplayWord(guess);
            
            if (correctGuess==false) { 
                state++;
                if (state==7) {
                    // lost game
                    return 3;
                } else {
                    return 2; // bad guess
                }
            } else if ( displayWord.indexOf("_") >= 0) {
               return 0; // continue game, with good guess
            } else {
               return 1; // all characters has been guessed, user has won game.
            }
    }
    
    
    public boolean isValidInput(char guess)
    {       
        return Character.isLetter(guess); // check if it's valid return fasle if it's not
    }
    
    public boolean isUsed(char guess)
    {
        return letterList.indexOf(guess) > -1; //returns true if guess is in letterList
    }
    
    
    
    private boolean updateDisplayWord(char guess){
        boolean correctGuess = false;
        for (int i=0; i<word.length(); i++) {
            if (word.charAt(i)==guess){
                    displayWord.replace(2*i, 2*i+1, Character.toString(guess)); 
                    correctGuess=true;
                }
        }
        return correctGuess;
        
    }

    private void createDisplayWord(){
        displayWord = new StringBuffer("_");
        for (int i=1; i<word.length(); i++) {
            displayWord.append(" _");
        }
   
    }
    
    private String randomWord() {
        WordGetter w = WordDataBase.selectWord(generator.nextInt(100));
        String newWord = w.getWord();
        return newWord;
    }

}

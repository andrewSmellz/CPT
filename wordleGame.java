
//WordleGame.java class to implement the game logic
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

class WordleGame extends Player {
  // declare variables and objects
  Random r;
  File textFile;
  File completedWords;
  FileReader in;
  BufferedReader readFile;
  FileReader in2;
  BufferedReader readFile2;
  ArrayList<String> wordList;
  ArrayList<String> CompletedWordList;

  /**
   * constructor
   * pre: none
   * post: A WordleGame object created. all objects and variables initialized
   */
  public WordleGame() {
    r = new Random();
    textFile = new File("wordBank.txt");
    completedWords = new File(getUsername() + ".txt");// username.txt is list of completed words
    wordList = new ArrayList<String>();
    CompletedWordList = new ArrayList<String>();
  }

  /**
   * selectWord method to select a word from the word bank
   * pre:none
   * post:a random word is selected and returned
   */
  public String selectWord() {
    String Playword = "";
    String word;
    try {
      in = new FileReader(textFile);
      readFile = new BufferedReader(in);
      while ((word = readFile.readLine()) != null) {// read each line of text until there are no more
        wordList.add(word.trim());// add all words to an arraylist
      }
      if (completedWords.exists()) {// if user already has a list of completed words
        in2 = new FileReader(completedWords);
        readFile2 = new BufferedReader(in2);
        while ((word = readFile2.readLine()) != null) {// read each line of text until there are no more
          CompletedWordList.add(word.substring(0, 6).trim());// add your completed words to an arraylist
        }
        in2.close();
        readFile2.close();
      }
      in.close();
      readFile.close();

      // catch exceptions
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist or could not be found");
      System.err.println("FileNotFoundException: " + e.getMessage());

    } catch (IOException e) {
      System.out.println("problem reading file");
      System.err.println("IOException: " + e.getMessage());
    }
    if (completedWords.exists()) {// if user has a completed words list
      do {// keep choosing new random words while the chosen word is contained within the completed words list
        Playword = wordList.get(r.nextInt(wordList.size()));
      } while (CompletedWordList.contains(Playword));
    } else {// if user has no completed words list, take first chosen word
      Playword = wordList.get(r.nextInt(wordList.size()));
    }
    return Playword.toLowerCase();// return chosen word
  }

  /**
   * checkCorrect method to check if guess is correct
   * pre:guess,secret word, and attempts are passed in
   * post: boolean true if correct,false is incorrect is returned
   */
  public boolean checkCorrect(String guess, String secretWord, int attempts) {
    if (guess.toLowerCase().equals(secretWord.trim().toLowerCase())) {// check if words equal
      writeToFile(secretWord.trim(), attempts);// call writeToFile method from Player class to document completed word
      return true;// return true since correct guess
    } else {// incorrect guess so return false
      return false;
    }
  }

  /**
   * checkCharIndexGreen method to check if a letter is in the correct spot in the word
   * pre:character being checked, secret word, and current position being checked are passed in
   * post:integer of index returned
   */
  public int checkCharIndexGreen(char guess, String secretWord, int position) {
    if (secretWord.charAt(position) == guess) {// if the guess character equals the character in the secret word at current position
      return (secretWord.indexOf(guess));// return the index
    } else {// otherwise return -1
      return -1;
    }
  }

  /**
   * checkCharIndexYellow method to check if a letter is in the word but in the wrong spot
   * pre:character being checked and secret word are passed in
   * post:boolean true if character in word or false if character not in word returned
   */
  public boolean checkCharIndexYellow(char guessChar, String secretWord) {
    if (secretWord.indexOf(guessChar) != -1) {// check if character is in word
      return true;// if it is return true
    } else {
      return false;// otherwise return false
    }
  }
}
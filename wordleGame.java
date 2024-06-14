import java.io.*;
import java.util.ArrayList;
import java.util.Random;

class wordleGame {
  Random r;
  File textFile;
  File completedWords;
  FileReader in;
  BufferedReader readFile;
  FileReader in2;
  BufferedReader readFile2;
  Player player;
  ArrayList<String> wordList;
  ArrayList<String> CompletedWordList;

  public wordleGame() {
    r = new Random();
    textFile = new File("wordBank.txt");
    completedWords = new File(player.getUsername() + ".txt");
    wordList = new ArrayList<String>();
    CompletedWordList = new ArrayList<String>();
    player = new Player(); // Instantiate Player with username from UserSettings
  }

  public String selectWord() {
    String Playword = "";
    String word;
    try {
      in = new FileReader(textFile);
      readFile = new BufferedReader(in);
      while ((word = readFile.readLine()) != null) {// read each line of text until there are no more
        wordList.add(word.trim());
      }
      if (completedWords.exists()) {
        in2 = new FileReader(completedWords);
        readFile2 = new BufferedReader(in2);
        while ((word = readFile2.readLine()) != null) {// read each line of text until there are no more
          CompletedWordList.add(word.substring(0, 6).trim());
        }
        System.out.println(CompletedWordList);
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
    if (completedWords.exists()) {
      do {
        Playword = wordList.get(r.nextInt(wordList.size()));
      } while (CompletedWordList.contains(Playword));
    } else {
      Playword = wordList.get(r.nextInt(wordList.size()));
    }
    return Playword;
  }

  public boolean checkCorrect(String guess, String secretWord, int attempts) {
    if (guess.toLowerCase().equals(secretWord.trim())) {
      System.out.println("you win");
      player.writeToFile(secretWord.trim(), attempts);
      return true;
    } else {
      System.out.println("guess does not match secretWord");
      return false;
    }
  }

  public int checkCharIndexGreen(char guess, String secretWord, int position) {
    if (secretWord.charAt(position) == guess) {
      return (secretWord.indexOf(guess));
    } else {
      return -1;
    }
  }

  public boolean checkCharIndexYellow(char guessChar, String secretWord,String guess) {
    int occurences=0;
    char[] secretWordLetters = secretWord.toCharArray();
    for(char i:secretWordLetters){
      if(i==guessChar){
        occurences++;
      }
    }
    if(occurences==1){
      return false;
    }
    if (secretWord.indexOf(guessChar) != -1) {
      return true;
    } else {
      return false;
    }
  }
}
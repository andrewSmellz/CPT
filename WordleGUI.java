
//WordleGUI.java class to handle the GUI of the main game
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class WordleGUI extends Player implements ActionListener {
  // declare variables and objects
  WordleGame game;
  JFrame frame;
  JPanel panel1;
  JPanel panel2;
  JSplitPane splitPane;
  JButton settingsBTN;
  JButton[] buttons;
  JLabel[] lettersLabels;
  JLabel[] labels;
  int attempts;
  String secretWord, guess;
  ArrayList<Integer> numsPressed;// tracks which buttons were pressed based on the index in the keyboard array

  /**
   * constructor
   * pre: none
   * post:WordleGUI object is created and all objects and variables are
   * initialized
   */
  public WordleGUI() {
    game = new WordleGame();
    secretWord = game.selectWord();
    guess = "";
    attempts = 0;
    numsPressed = new ArrayList<Integer>();
    String[] keyboard = new String[] { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H",
        "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M", "ENTER", "DELETE" };// text for all keyboard buttons
    frame = new JFrame("Wordle");// create frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create all GUI components

    // set up panels and splitpane
    panel1 = new JPanel();
    panel2 = new JPanel();
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel1, panel2);
    splitPane.setDividerLocation(375);
    splitPane.setOneTouchExpandable(false);
    panel1.setLayout(new GridLayout(7, 5, 10, 5));
    panel2.setLayout(new GridLayout(3, 0, 10, 5));
    panel1.setBackground(new Color(193, 224, 223));
    panel2.setBackground(new Color(193, 224, 223));

    buttons = new JButton[28];// create all buttons in an array
    labels = new JLabel[4];// create label array

    // settings button
    settingsBTN = new JButton("settings");
    settingsBTN.addActionListener(this);

    for (int i = 0; i < buttons.length; i++) {// add all buttons to panel
      buttons[i] = new JButton();
      buttons[i].addActionListener(this);
      buttons[i].setFont(new Font("comic sans", Font.BOLD, 16));
      buttons[i].setText(keyboard[i]);
      panel2.add(buttons[i]);
    }

    for (int i = 0; i < labels.length; i++) {// add top labels to GUI
      labels[i] = new JLabel();
      if (i == 2) {
        labels[2].setText("Wordle");
        labels[2].setHorizontalAlignment(JLabel.CENTER);
        labels[2].setFont(new Font("comic sans", Font.BOLD, 26));
      }
      panel1.add(labels[i]);
    }

    panel1.add(settingsBTN);// add settings button

    lettersLabels = new JLabel[30];// create and add labels for where guessed letters will go
    for (int i = 0; i < lettersLabels.length; i++) {
      lettersLabels[i] = new JLabel("", SwingConstants.CENTER);
      lettersLabels[i].setFont(new Font("comic sans", Font.BOLD, 26));
      lettersLabels[i].setOpaque(true); // Make labels opaque so background color is visible
      lettersLabels[i].setBackground(Color.WHITE);
      lettersLabels[i].setForeground(Color.BLACK);
      panel1.add(lettersLabels[i]);
    }

    frame.setContentPane(splitPane);
    frame.setSize(1200, 600);
    frame.setVisible(true);
  }

  /**
   * event handler to handle button clicks
   * pre:none
   * post:all actions from buttons being clicked have been performed
   */
  public void actionPerformed(ActionEvent e) {
    System.out.println(secretWord);// print the secret word for testing purposes
    for (int i = 0; i < buttons.length; i++) {// loop through keyboard buttons
      if (e.getSource() == buttons[i]) {
        if (i != 26 && i != 27) { // Not enter or delete
          if (guess.length() < 5) {// if less than 6 letters have already been pressed
            numsPressed.add(i);// keep track of numbers pressed
            guess += buttons[i].getText().toLowerCase();// add letter to your guess
            // loop which loops through current row of letters
            // goes through row until it finds a label without text in it, this is where the
            // pressed letter will be put
            for (int j = 5 * attempts; j < 5 * attempts + 5; j++) {
              if (lettersLabels[j].getText().equals("")) {
                lettersLabels[j].setText(buttons[i].getText());
                break;// breaks out of loop when finds first empty label
              }
            }
          }
        } else if (i == 27) { // delete
          if (guess.length() > 0) {
            numsPressed.remove(guess.length() - 1);// remove from numbers pressed
            guess = guess.substring(0, guess.length() - 1);// remove letter from guess word
            // loop through current row of letters starting from end
            // loops until it finds a label that has some content on it, then removes that
            // content
            for (int j = 5 * attempts + 5; j >= 5 * attempts; j--) {
              if (!lettersLabels[j].getText().equals("")) {
                lettersLabels[j].setText("");
                break;// break out of loop when label found
              }
            }
          }
        } else if (i == 26) { // enter
          attempts++;// increase number of attempts
          if (attempts < 6) {
            if (guess.length() == 5) {// guess is full

              if (game.checkCorrect(guess, secretWord, (attempts))) {// call checkCorrect method to check if correct
                // if correct disable all buttons
                for (int q = 0; q < buttons.length; q++) {
                  buttons[q].setEnabled(false);
                }
                // loop through current letters and turn them correct letter colour - default is
                // green
                for (int q = 5 * (attempts - 1); q < 5 * (attempts - 1) + 5; q++) {
                  lettersLabels[q].setBackground(new Color(red1, green1, blue1));
                }

                // display an dialogue box option pane showing how many attempts you took and
                // asking if you want to play again
                String message = "You won, it took you " + attempts
                    + " attempts. \nwould you like to play again or log out";

                Object[] options = { "play again", "log out" };

                int choice = JOptionPane.showOptionDialog(
                    null,
                    message,
                    "Game Result",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
                if (choice == JOptionPane.YES_OPTION) {
                  reset();// if want to play again, call reset method
                } else if (choice == JOptionPane.NO_OPTION) {
                  // if dont want to play again, close GUI and open the loginin GUI again
                  frame.dispose();
                  new WordleLogin();
                }

              } else {// if guess was not correct
                for (int j = 0; j < 5; j++) {
                  // check if letter is in correct placement
                  char letter = guess.toLowerCase().charAt(j);
                  int pos = game.checkCharIndexGreen(letter, secretWord, j);
                  if (pos != -1) {// if correct placement, turn letter correct placement colour - default green
                    lettersLabels[5 * (attempts - 1) + j].setBackground(new Color(red1, green1, blue1));

                  } else {// check if letter is in word but wrong spot
                    if (game.checkCharIndexYellow(letter, secretWord)) {
                      // if so change letter to incorrect placment colour - default yellow
                      lettersLabels[5 * (attempts - 1) + j].setBackground(new Color(red2, green2, blue2));
                    } else {// letter not in word

                      // letter not in word so set incorrect letter colour - default gray
                      lettersLabels[5 * (attempts - 1) + j].setBackground(new Color(red3, green3, blue3));

                    }
                  }
                }
              }
              guess = "";// reset guess
              numsPressed.clear();// clear numbers pressed
            } else {
              System.out.println("Guess is not 5 characters long");// guess was not long enough
            }
          } else {// ran out of attempts

            // display an dialogue box option pane showing how many attempts you took and
            // asking if you want to play again
            String message = "You ran out of attempts and lost.\nwould you like to play again or log out.";

            Object[] options = { "play again", "log out" };

            int choice = JOptionPane.showOptionDialog(
                null,
                message,
                "Game Result",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
            if (choice == JOptionPane.YES_OPTION) {
              reset();// if want to play again, call reset method
            } else if (choice == JOptionPane.NO_OPTION) {
              // if dont want to play again, close GUI and open the loginin GUI again
              frame.dispose();
              new WordleLogin();
            }
          }
        }
      } else if (e.getSource() == settingsBTN) {// settings button was pressed
        new SettingsGUI();// open up settings GUI
        break;
      }
    }
  }

  /**
   * reset method to reset for a new round
   * pre:none
   * post:game is reset for a new round
   */
  public void reset() {
    // reset variables
    attempts = 0;
    guess = "";
    // reset buttons
    for (int i = 0; i < buttons.length; i++) {
      buttons[i].setBackground(Color.WHITE);
      buttons[i].setEnabled(true);
    }
    // reset labels
    for (int i = 0; i < lettersLabels.length; i++) {
      lettersLabels[i].setText("");
      lettersLabels[i].setBackground(Color.WHITE);
    }
    // generate new secret word
    secretWord = game.selectWord();
  }

}

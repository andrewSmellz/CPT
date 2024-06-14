import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class WordleGUI extends Player implements ActionListener {
  wordleGame game;
  JFrame frame;
  JPanel panel1;
  JPanel panel2;
  JSplitPane splitPane;
  JButton settingsBTN;
  JButton[] buttons;
  JLabel[] lettersLabels;
  JLabel[] labels;
  int guessNum;
  String secretWord, guess;
  ArrayList<Integer> numsPressed;

  public WordleGUI() {
    game = new wordleGame();
    secretWord = game.selectWord();
    guess = "";
    guessNum = 0;
    numsPressed = new ArrayList<Integer>();
    String[] keyboard = new String[] { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H",
        "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M", "ENTER", "DELETE" };
    frame = new JFrame("Wordle");// create frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    labels = new JLabel[4];

    settingsBTN = new JButton("settings");
    settingsBTN.addActionListener(this);

    for (int i = 0; i < buttons.length; i++) {
      buttons[i] = new JButton();
      buttons[i].addActionListener(this);
      buttons[i].setFont(new Font("comic sans", Font.BOLD, 16));
      buttons[i].setText(keyboard[i]);
      panel2.add(buttons[i]);
    }

    for (int i = 0; i < labels.length; i++) {
      labels[i] = new JLabel();
      if (i == 2) {
        labels[2].setText("Wordle");
        labels[2].setHorizontalAlignment(JLabel.CENTER);
        labels[2].setFont(new Font("comic sans", Font.BOLD, 26));
      }
      panel1.add(labels[i]);
    }
    panel1.add(settingsBTN);
    lettersLabels = new JLabel[30];

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

  public void actionPerformed(ActionEvent e) {
    System.out.println(secretWord);
    for (int i = 0; i < buttons.length; i++) {
      if (e.getSource() == buttons[i]) {
        if (i != 26 && i != 27) { // Not ENTER or DELETE
          if (guess.length() < 5) {
            numsPressed.add(i);
            guess += buttons[i].getText().toLowerCase();
            System.out.println("current guess is " + guess);
            for (int j = 5 * guessNum; j < 5 * guessNum + 5; j++) {
              if (lettersLabels[j].getText().equals("")) {
                lettersLabels[j].setText(buttons[i].getText());
                break;
              }
            }
          }
        } else if (i == 27) { // DELETE
          if (guess.length() > 0) {
            numsPressed.remove(guess.length() - 1);
            guess = guess.substring(0, guess.length() - 1);

            System.out.println("current guess is " + guess);
            for (int j = 5 * guessNum + 4; j >= 5 * guessNum; j--) {
              if (!lettersLabels[j].getText().equals("")) {
                lettersLabels[j].setText("");
                break;
              }
            }
          }
        } else if (i == 26) { // ENTER
          if (guess.length() == 5) {
            guessNum++;
            if (game.checkCorrect(guess, secretWord, (guessNum) )) {

              for (int q = 0; q < buttons.length; q++) {
                buttons[q].setEnabled(false);
              }
              for (int q = 5 * (guessNum-1); q < 5 * (guessNum-1) + 5; q++) {
                lettersLabels[q].setBackground(new Color(red1, green1, blue1));
              }
              for (int j = 0; j < 5; j++) {
                buttons[numsPressed.get(j)].setBackground(new Color(red1, green1, blue1));
              }

              String message = "You won, it took you " + guessNum 
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
                reset();
              } else if (choice == JOptionPane.NO_OPTION) {
                frame.dispose();
                new WordleLogin();
              }

            } else {
              System.out.println("went into else block");
              for (int j = 0; j < 5; j++) {
                char letter = guess.toLowerCase().charAt(j);
                int pos = game.checkCharIndexGreen(letter, secretWord, j);
                if (pos != -1) {
                  lettersLabels[5 * (guessNum-1) + j].setBackground(new Color(red1, green1, blue1));
                  buttons[numsPressed.get(j)].setBackground(new Color(red1, green1, blue1));
                } else {
                  if (game.checkCharIndexYellow(letter, secretWord)) {
                    lettersLabels[5 * (guessNum-1) + j].setBackground(new Color(red2, green2, blue2));
                    buttons[numsPressed.get(j)].setBackground(new Color(red2, green2, blue2));
                  } else {
                    lettersLabels[5 * (guessNum-1) + j].setBackground(new Color(red3, green3, blue3));
                    buttons[numsPressed.get(j)].setBackground(new Color(red3, green3, blue3));
                    buttons[numsPressed.get(j)].setEnabled(false);
                  }
                }
              }
            }
            
            guess = "";
            numsPressed.clear();
          } else {
            System.out.println("Guess is not 5 characters long");
          }
        }
      } else if (e.getSource() == settingsBTN) {
        new SettingsGUI();
        break;
      }
    }
  }

  public void reset() {
    guessNum = 0;
    guess = "";
    for (int i = 0; i < buttons.length; i++) {
      buttons[i].setBackground(Color.WHITE);
      buttons[i].setEnabled(true);
    }
    for (int i = 0; i < lettersLabels.length; i++) {
      lettersLabels[i].setText("");
      lettersLabels[i].setBackground(Color.WHITE);
    }
    secretWord = game.selectWord();
  }

}

//WordleLogin.java is a class which provides functionality for the login menu

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordleLogin implements ActionListener {
    // declare objects and variables
    JFrame frame;
    JTextField usernameField;
    JButton loginButton;
    JButton quitButton;
    JLabel usernameLabel;

    /**
     * constructor
     * pre: none
     * post: A WordleLogin object created. all objects and variables initialized
     */
    public WordleLogin() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create all buttons,labels and panel then add to frame
        usernameField = new JTextField(20);
        loginButton = new JButton("Login");
        usernameLabel = new JLabel("Username:");
        quitButton = new JButton("quit");
        quitButton.addActionListener(this);
        loginButton.addActionListener(this);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(quitButton);
        panel.add(loginButton);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);// places the frame in the middle of the screen
        frame.setVisible(true);
    }

    /**
     * event handler to handle button clicks
     * pre:none
     * post:all actions from buttons being clicked have been performed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {// if login button is pressed
            String username = usernameField.getText().trim();// get text from textfield
            if (!username.equals("")) {// check if something was input
                Player.setUsername(username); // Set username with the setUsername method of the Player class
                frame.dispose(); // Close login window
                new WordleGUI(); // Open main game GUI
            } else {// did not input a username
                JOptionPane.showMessageDialog(frame, "Please enter a username.");
            }
        } else if (e.getSource() == quitButton) {// quit button is pressed
            frame.dispose();// close login GUI, quitting the game
        }
    }

}

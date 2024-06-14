import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordleLogin implements ActionListener {
    private JFrame frame;
    private JTextField usernameField;
    private JButton loginButton;
    private JButton quitButton;
    private JLabel usernameLabel;

    public WordleLogin() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        if (e.getSource() == loginButton) {
            if (!username.isEmpty()) {
                Player.setUsername(username); // Set username in UserSettings
                frame.dispose(); // Close login window
                new WordleGUI(); // Open main GUI with username
                System.out.println(username);
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a username.");
            }
        } else if (e.getSource() == quitButton) {
            frame.dispose();
        }
    }

}

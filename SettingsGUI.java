import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsGUI extends Player implements ActionListener {
    Player player;
    JFrame frame;
    JPanel panel;
    JLabel[] labels;
    JComboBox<String>[] comboBoxs;
    JButton saveBTN;
    JButton loadColoursBTN;

    
    public SettingsGUI() {
        player = new Player();
        frame = new JFrame("settings");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 5));
        saveBTN= new JButton("save");
        saveBTN.addActionListener(this);
        loadColoursBTN= new JButton("load colours from save");
        loadColoursBTN.addActionListener(this);
        labels = new JLabel[3];
        comboBoxs = new JComboBox[3];

        labels[0] = new JLabel("correct letter colour");
        comboBoxs[0] = new JComboBox<String>(new String[] { "green", "purple" });
        labels[1] = new JLabel("incorrect letter placement colour");
        comboBoxs[1] = new JComboBox<String>(new String[] { "yellow", "teal" });
        labels[2] = new JLabel("incorrect letter colour");
        comboBoxs[2] = new JComboBox<String>(new String[] { "gray", "orange" });
        for (int i = 0; i < 3; i++) {
            comboBoxs[i].addActionListener(this);
            panel.add(labels[i]);
            panel.add(comboBoxs[i]);
        }
        panel.add(saveBTN);
        panel.add(loadColoursBTN);
        frame.setContentPane(panel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            if (e.getSource() == comboBoxs[i]) {
                String selectedColor = (String) comboBoxs[i].getSelectedItem();

                switch (i) {
                    case 0:
                        if (selectedColor.equals("green")) {
                            red1 = 37;
                            green1 = 184;
                            blue1 = 37;
                        } else if (selectedColor.equals("purple")) {
                            red1 = 189;
                            green1 = 65;
                            blue1 = 242;
                        }
                        break;
                    case 1:
                        if (selectedColor.equals("yellow")) {
                            red2 = 227;
                            green2 = 213;
                            blue2 = 59;
                        } else if (selectedColor.equals("teal")) {
                            red2 = 75;
                            green2 = 180;
                            blue2 = 184;
                        }

                        break;
                    case 2:
                        if (selectedColor.equals("gray")) {
                            red3 = 143;
                            green3 = 142;
                            blue3 = 139;
                        } else if (selectedColor.equals("orange")) {
                            red3 = 230;
                            green3 = 154;
                            blue3 = 55;
                        }
                        break;
                }
            }
        }if(e.getSource()==saveBTN){
            player.writeColours();
            frame.dispose();
        }else if(e.getSource()==loadColoursBTN){
            readColours();
        }
    }
}

//Player class to handle information about the player

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Player {
    // declare variables
    int red1 = 37;
    public static int green1 = 184;
    public static int blue1 = 37;
    public static int red2 = 227;
    public static int green2 = 213;
    public static int blue2 = 59;
    public static int red3 = 143;
    public static int green3 = 142;
    public static int blue3 = 139;
    public static String username;
    String fileName;
    Calendar calendar;

    /**
     * constructor
     * pre: none
     * post: A Player object created. all objects and variables initialized
     */
    public Player() {
        calendar = Calendar.getInstance();
        fileName = "";
    }

    /**
     * method to write a completed word to the players file
     * pre:completed word and attempts taken are input
     * post:competion of word with stats is written to file
     */
    public void writeToFile(String content, int attempts) {
        fileName = username.toLowerCase() + ".txt";// name the file as the username.txt
        File textFile = new File(fileName);
        FileWriter out;
        BufferedWriter writeFile;
        // check if a file for the user already exists, if not create one
        try {
            if (!textFile.exists()) {
                textFile.createNewFile();
            }
            // contents writen to file are
            // word completed - date and time completed - attempts taken
            out = new FileWriter(fileName, true);
            writeFile = new BufferedWriter(out);
            writeFile.write(content + " - date completed: " + calendar.getTime() + " - attempts: " + attempts);
            writeFile.newLine();
            writeFile.close();
            out.close();

            // catch exceptions
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found");
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("problem writing to file");
            System.err.println("IOException: " + e.getMessage());
        }
    }

    /**
     * method to get username
     * pre:none
     * post:username is returned
     */
    public static String getUsername() {
        return username;
    }

    /**
     * method to set username
     * pre:username is input
     * post:username is set
     */
    public static void setUsername(String usernameIn) {
        Player.username = usernameIn;
    }

    /**
     * method to write a users selected colour options to their colour file
     * pre:none
     * post:username is returned
     */
    public void writeColours() {
        fileName = username.toLowerCase() + "_Colour.txt";// file name is username_Colour.txt
        File textFile = new File(fileName);
        FileWriter out = null;
        BufferedWriter writeFile = null;
        try {// check if a file already exists for the user, if not, create one
            if (!textFile.exists()) {
                textFile.createNewFile();
            }
            // all colour settings are written to the file
            out = new FileWriter(fileName);
            writeFile = new BufferedWriter(out);
            writeFile.write(String.valueOf(red1));
            writeFile.newLine();
            writeFile.write(String.valueOf(green1));
            writeFile.newLine();
            writeFile.write(String.valueOf(blue1));
            writeFile.newLine();
            writeFile.write(String.valueOf(red2));
            writeFile.newLine();
            writeFile.write(String.valueOf(green2));
            writeFile.newLine();
            writeFile.write(String.valueOf(blue2));
            writeFile.newLine();
            writeFile.write(String.valueOf(red3));
            writeFile.newLine();
            writeFile.write(String.valueOf(green3));
            writeFile.newLine();
            writeFile.write(String.valueOf(blue3));
            writeFile.newLine();
            writeFile.close();
            out.close();

            // catch exceptions
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found");
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("problem writing to file");
            System.err.println("IOException: " + e.getMessage());
        }
    }

    /**
     * method to get read colour settings from a users colour file
     * pre:none
     * post:users colour settings are read and set
     */
    public void readColours() {
        FileReader in;
        BufferedReader readFile;
        fileName = username.toLowerCase() + "_Colour.txt";// file name is username_Colour.txt
        File textFile = new File(fileName);
        String word;
        ArrayList<Integer> colours = new ArrayList<Integer>();

        try {
            in = new FileReader(textFile);
            readFile = new BufferedReader(in);
            while ((word = readFile.readLine()) != null) {// read each line of text until there are no more
                colours.add(Integer.valueOf(word));// add each colour value to an arraylist
            }

            in.close();
            readFile.close();
            // set all colour values
            red1 = colours.get(0);
            green1 = colours.get(1);
            blue1 = colours.get(2);
            red2 = colours.get(3);
            green2 = colours.get(4);
            blue2 = colours.get(5);
            red3 = colours.get(6);
            green3 = colours.get(7);
            blue3 = colours.get(8);

            // catch exceptions
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found");
            System.err.println("FileNotFoundException: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("problem reading file");
            System.err.println("IOException: " + e.getMessage());
        }

    }

}

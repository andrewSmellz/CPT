import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Player  {
    public static int red1 = 37;
    public static int green1 = 184;
    public static int blue1 = 37;
    public static int red2 = 227;
    public static int green2 = 213;
    public static int blue2 = 59;
    public static int red3 = 143;
    public static int green3 = 142;
    public static int blue3 = 139;
    private static String username;
    private String fileName;
    Calendar calendar;

    public Player() {
        calendar = Calendar.getInstance();
        fileName = "";
    }

    public void writeToFile(String content, int attempts) {
        fileName = username.toLowerCase() + ".txt";
        File textFile = new File(fileName);
        FileWriter out;
        BufferedWriter writeFile;
        try {
            if (!textFile.exists()) {
                textFile.createNewFile();
            }

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

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Player.username = username;
    }

    public void writeColours() {
        fileName = username.toLowerCase() + "_Colour.txt";
        File textFile = new File(fileName);
        FileWriter out = null;
        BufferedWriter writeFile = null;
        try {
            if (!textFile.exists()) {
                textFile.createNewFile();
            }

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

    public void readColours() {
        FileReader in;
        BufferedReader readFile;
        fileName = username.toLowerCase() + "_Colour.txt";
        File textFile = new File(fileName);
        String word;

        try {
            ArrayList<Integer> colours = new ArrayList<Integer>();
            in = new FileReader(textFile);
            readFile = new BufferedReader(in);
            while ((word = readFile.readLine()) != null) {// read each line of text until there are no more
                colours.add(Integer.valueOf(word));
            }

            in.close();
            readFile.close();
            red1=colours.get(0);
            green1=colours.get(1);
            blue1=colours.get(2);
            red2=colours.get(3);
            green2=colours.get(4);
            blue2=colours.get(5);
            red3=colours.get(6);
            green3=colours.get(7);
            blue3=colours.get(8);
            
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

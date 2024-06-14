import java.io.*;
import java.util.Calendar;
public class Player {
    private static String  username;
    private String fileName;
    Calendar calendar;
    // Constructor that accepts username
    public Player() {
        calendar = Calendar.getInstance();
        fileName = username.toLowerCase() + ".txt";
    }

    public void writeToFile(String content,int attempts) {
        File textFile = new File(fileName);
        FileWriter out = null;
        BufferedWriter writeFile = null;
        try {
            if (!textFile.exists()) {
                textFile.createNewFile();
            }

            out = new FileWriter(fileName,true);
      writeFile = new BufferedWriter(out);
      writeFile.write(content + " - date completed: " + calendar.getTime() + " - attempts: " + attempts);
      writeFile.newLine();
      writeFile.close();
      out.close();
      
      //catch exceptions
    }catch(FileNotFoundException e){
      System.out.println("File does not exist or could not be found");
      System.err.println("FileNotFoundException: " + e.getMessage());
    }catch(IOException e){
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

}

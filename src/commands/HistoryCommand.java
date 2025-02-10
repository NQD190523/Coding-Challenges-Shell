package commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HistoryCommand {
    private static final String HISTORY_FILE = System.getProperty("user.home") + "/.ccsh_history";
    public static List<String> history = new java.util.ArrayList<String>();
    public static void loadHistory(){
        try{
            Path path = Paths.get(HISTORY_FILE);
            if (Files.exists(path)) {
                history.clear();   
                history.addAll(Files.readAllLines(path));
            }
        } catch (IOException e) {
            System.err.println("Error loading history: " + e.getMessage());
        }
    }

    // Add command to history and save it immediately
    public static void addToHistory(String command) {
        if (!command.trim().isEmpty()) {
            history.add(command);
            saveHistoryCommand();  // Append only this command
        }
    }

    public static void printHistoryCommand(){
        for(int i = 0; i<history.size(); i++){
            System.out.println(HistoryCommand.history.get(i));
        }
    }
    
    public static void saveHistoryCommand() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE));
            for (String command: history){
                writer.write(command);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving history: " + e.getMessage());
        } 
    }
}

package commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class HandlePipeLine {

    public static void handlePipeLine(String commands) {

        // Get the current working directory
        File currentDir = new File(System.getProperty("user.dir"));
        
        String fullCommand = "powershell.exe -Command  \"" + commands + "\"";

        System.out.println("Executing: " + fullCommand);

        ProcessBuilder builder = new ProcessBuilder("powershell.exe", "-Command", commands);

         // Set the working directory for the process
         builder.directory(currentDir);
        
         builder.redirectErrorStream(true);
        try {
            Process process =  builder.start();
            
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while((line = reader.readLine()) !=null) {
                    System.out.println(line);
                }
                process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}

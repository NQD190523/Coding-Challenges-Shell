package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HandlePipeLine {

    public static void handlePipeLine(String commands) {
        
        String[] commandList = commands.split("\\|");
        List<ProcessBuilder> builders = new ArrayList<>();
        
        for(String command : commandList){
            String trimmedCommand = command.trim();
            // Format command correctly for PowerShell execution
            String[] newCommand = {"powershell.exe", "-Command", trimmedCommand};
            builders.add(new ProcessBuilder(newCommand));
        }
        try {
            List<Process> processes =  ProcessBuilder.startPipeline(builders);
            Process lastProcess = processes.get(processes.size() - 1);

            try(InputStream inputStream = lastProcess.getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while((line = reader.readLine()) !=null) {
                    System.out.println(line);
                }
            }
            
            for(Process process : processes) {
                process.waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import commands.CatCommand;
import commands.CdCommand;
import commands.ExportCommand;
import commands.HandlePipeLine;
import commands.LsCommand;
import commands.PwdCommand;

public class App {

    public static void main(String[] args) throws Exception {
        
        Boolean running = false;
        Scanner scanner = new Scanner(System.in);
        String currentDirectory = System.getProperty("user.dir");

        // Welcome message
        while (!running) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("shellCC")) {
                running = true;
                System.out.println("Welcome to shellCC! Type 'exit' to quit.");
            } else {
                System.out.println("Unknown command. Type 'shellCC' to start.");
            }
        }

        // Main loop
        while(running){
            System.out.print("shellCC> ");
            String command = scanner.nextLine().trim();
            // blank input handle
            if(command.isEmpty()){
                continue;
            }
            //exit command
            if(command.equalsIgnoreCase("exit")){
                System.out.println("Exiting shellCC. Goodbye!");
                break;
            }

            if(command.contains("|")){
                HandlePipeLine.handlePipeLine(command);
                continue;
            }

            String tokens[] = command.split("\\s+");
            String commandType = tokens[0];
            String[] arguements = Arrays.copyOfRange(tokens, 1, tokens.length);
            try{
                switch (commandType) {
                    case "ls":
                        LsCommand.printFileNames();
                        break;
                    case "pwd":
                        PwdCommand.printCurrentDirectory();
                        break;
                    case "cd":
                        CdCommand.changeDirectory(arguements[0]);
                        break;
                    case "export":
                        ExportCommand.exportVariable(arguements[0], arguements[1]);
                        break;
                    case "cat":
                        CatCommand.printFileContent(currentDirectory, arguements);
                    default:
                        break;
                } 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}

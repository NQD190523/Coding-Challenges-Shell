import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import commands.LsCommand;
import commands.PwdCommand;

public class App {

    public static void main(String[] args) throws Exception {
        
        Boolean running = false;
        Scanner scanner = new Scanner(System.in);

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
            //exit command
            if(command.equalsIgnoreCase("exit")){
                System.out.println("Exiting shellCC. Goodbye!");
                break;
            }
            //print file in directory
            else if(command.equalsIgnoreCase("ls")){
                LsCommand.printFileNames();
            //print current directory
            } else if (command.equalsIgnoreCase("pwd")) {
                PwdCommand.printCurrentDirectory();
            //print file content using external command
            } else if (command.contains("cat")) {
                String[] request = command.split(" ");
                if (request.length == 2) {
                    Process pb = new ProcessBuilder( "powershell.exe","cat", request[1].toString()).start();
                    pb.waitFor();
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(pb.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                    pb.destroy();
                } else {
                    System.out.println("Invalid command. Usage: cat <filename>");
                }
            } else {
                System.out.println("No such file or directory (os error 2)");
            }
        }
        scanner.close();
    }
}

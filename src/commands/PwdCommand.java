package commands;

public class PwdCommand {
    
    public static void printCurrentDirectory() {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
    }
}
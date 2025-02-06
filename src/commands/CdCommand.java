package commands;

public class CdCommand {

    public static void changeDirectory(String path) {
        String currentDirectory = System.getProperty("user.dir");
        String newDirectory = currentDirectory + "\\" + path;
        System.setProperty("user.dir", newDirectory);
    }
}

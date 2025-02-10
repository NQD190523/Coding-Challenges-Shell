package commands;

import java.io.File;

public class CdCommand {

    public static void changeDirectory(String path) {
        String currentDirectory = System.getProperty("user.dir");
        File currentDir = new File(currentDirectory);
        File newDir;
        if(path.equals("..")) {
             newDir = currentDir.getParentFile();
        } else {
            newDir = new File(currentDirectory + "\\" + path);
        }
        if(newDir.exists() && newDir.isDirectory() && newDir != null) {
            System.setProperty("user.dir", newDir.getAbsolutePath());
        } else {
            System.out.println("Directory not found: " + path);
        }
    }
}

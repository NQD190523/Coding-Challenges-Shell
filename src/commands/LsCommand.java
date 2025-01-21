package commands;

import java.io.File;

public class LsCommand {
    public static void printFileNames(){
        //Get current directory
        String currentDirectory = System.getProperty("user.dir");

        //Get all files in current directory
        File[] files = new File(currentDirectory).listFiles();
        StringBuilder fileNames = new StringBuilder();
        for(File file : files){
            fileNames.append(file.getName()).append(" ");
        }
        if(fileNames.length() == 0){
            System.out.println( "No files found");
        }
        else{
            System.out.println(  fileNames.toString());
        }
    }
}
package commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CatCommand {
    public static void printFileContent(String currentDiretory, String[] fileNames) throws IOException {
        if(fileNames.length == 0){
            throw new IOException("Usage: cat <file>");
        }
        for(String fileName : fileNames){
            Path filePath = Paths.get(currentDiretory).resolve(fileName).normalize();
            if(!filePath.toFile().exists() || Files.isDirectory(filePath)){
                throw new IOException("File not found: " + fileName);
            }
            Files.lines(filePath).forEach(System.out::println);
        }
    }
}

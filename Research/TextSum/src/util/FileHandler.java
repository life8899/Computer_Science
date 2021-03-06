package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler
{
    public static List<Path> getPathsInDirectory(Path directoryPath) throws IOException
    {
        return Files.walk(directoryPath).filter(p -> !p.toFile().isDirectory()).filter(p -> !p.toFile().getName().startsWith(".")).collect(Collectors.toList());
    }

    public static List<String> readFileAsList(Path filePath) throws IOException
    {
        return Files.readAllLines(filePath).stream().collect(Collectors.toList());
    }

    public static String readFileAsString(Path filePath) throws IOException
    {
        return Files.readAllLines(filePath).stream().collect(Collectors.joining());
    }

    public static void createDirectory(Path directoryPath)
    {
        File directoryFile = directoryPath.toFile();
        if (!directoryFile.exists()) {
            directoryFile.mkdir();
        }
    }

    public static void writeString(Path filePath, String string) throws IOException
    {
        Files.write(filePath, string.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    public static void writeList(Path filePath, List<String> list) throws IOException
    {
        Files.write(filePath, list, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }
}
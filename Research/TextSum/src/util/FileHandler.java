package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {

    private File outFile;
    private FileMode mode;

    public FileHandler(File outFile, FileMode mode)
    {
        this.outFile = outFile;
        this.mode = mode;
    }

    public FileHandler(String outFilePath, FileMode mode)
    {
        this.outFile = new File(outFilePath);
        this.mode = mode;
    }

    public FileHandler (Path outFilePath, FileMode mode)
    {
        this.outFile = outFilePath.toFile();
        this.mode = mode;
    }

    public boolean initFile() throws IOException
    {
        if (this.outFile.exists()) {
            this.outFile.delete();
        }
        return this.outFile.createNewFile();
    }

    public List<Path> readPathsFromDirectory() throws IOException
    {
        ArrayList<Path> paths = new ArrayList<>();
        for (Object o : Files.walk(this.outFile.toPath()).toArray()) {
            Path p = (Path)o;
            if (!p.toFile().isDirectory() && !p.toFile().getName().startsWith(".")) {
                paths.add(p);
            }
        }
        return paths;
    }

    public String readFileAsString() throws IOException
    {
        return Files.readAllLines(this.outFile.toPath()).stream().collect(Collectors.joining());
    }

    public List<String> readFileAsList() throws IOException
    {
        return Files.readAllLines(this.outFile.toPath());
    }

    public void writeStringToFile(String str) throws IOException
    {
        if (this.mode != FileMode.READ) {
            if (this.initFile()) {
                FileWriter fileWriter;
                if (this.mode == FileMode.APPEND)
                    fileWriter = new FileWriter(outFile.getAbsolutePath(), true);
                else
                    fileWriter = new FileWriter(outFile.getAbsolutePath());
                fileWriter.write(str);
                fileWriter.close();
            } else {
                throw new IOException("IO Error: The file could not be initialized.");
            }
        } else {
            throw new IOException("Cannot write to a file with the mode FileMode.READ");
        }
    }

    public void writeListToFile(List<String> strings, boolean separateByNewLine) throws IOException
    {
        for (String str : strings) {
            this.writeStringToFile((separateByNewLine) ? (str + "\n\n") : str);
        }
    }
}
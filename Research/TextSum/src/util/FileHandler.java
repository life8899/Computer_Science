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
	private boolean initializedForWriting;

    public FileHandler(File outFile, FileMode mode) throws IOException
    {
        this.outFile = outFile;
        this.mode = mode;
	    this.initializedForWriting = false;
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

    public boolean initFileForWriting() throws IOException
    {
	    boolean initialized = false;
	    if (this.mode != FileMode.READ) {
		    if (this.mode == FileMode.DELETE_BEFORE_APPEND) {
			    if (this.outFile.exists()) {
				    if (this.outFile.delete()) {
					    initialized = this.outFile.createNewFile();
				    }
			    } else {
				    initialized = this.outFile.createNewFile();
			    }
		    } else if (this.mode == FileMode.APPEND) {
			    initialized = this.outFile.exists() || this.outFile.createNewFile();
		    }
	    } else {
		    throw new IOException("Cannot Write to a File with FileMode READ");
        }
	    this.initializedForWriting = initialized;
	    return initialized;
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

    public void writeStringToFile(String str, boolean addNewLine) throws IOException
    {
		if (this.initializedForWriting) {
			FileWriter fileWriter;
			fileWriter = new FileWriter(this.outFile, true);
			fileWriter.write(str);
			if (addNewLine)
				fileWriter.write("\n");
			fileWriter.close();
		} else {
			throw new IOException("The FileHandler is not initialized for writing");
		}
    }

    public void writeListToFile(List<String> strings, boolean addNewLine) throws IOException
    {
	    for (String string : strings) {
		    this.writeStringToFile(string, addNewLine);
	    }
    }
}
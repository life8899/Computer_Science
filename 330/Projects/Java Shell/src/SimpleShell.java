import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Emulates a Terminal Shell
 *
 * @author Nicholas Alexander - Part 1
 * @author Alec Murray - Part 2
 * @version 10/4/2014
 */
public class SimpleShell
{

	/**
	 * Starts a Shell
	 *
	 * @param args Runtime Arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		String commandLine;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		ProcessBuilder builder = new ProcessBuilder();
		builder.directory(new File(System.getProperty("user.dir")));

		System.out.println("Welcome! Use \"exit\" command to Quit");

		while (true)
		{
			System.out.print("jsh: " + builder.directory().getCanonicalPath() + ">");
			commandLine = console.readLine();
			ArrayList<String> parsedCommand = parse(commandLine);


			if (commandLine.equals("exit"))
				System.exit(0);
			else if (commandLine.equals(""))
				continue;

			try
			{
				if (parsedCommand.contains("cd"))
				{
					File newDir = changeDirectory(parsedCommand, builder.directory().getAbsolutePath());
					if (newDir == null)
						System.out.println("Directory not found");
					else
						builder.directory(newDir.getAbsoluteFile());
					continue;
				}
				builder.command(parsedCommand);
				Process process = builder.start();
				System.out.print(readCommandOutput(process.getInputStream()));
			}
			catch (IOException ioe)
			{
				System.out.println("Command '" + parsedCommand.get(0) + "' not found");
			}
		}
	}

	/**
	 * Parses Command Line input to distinguish command and parameters.
	 *
	 * @param commandLine Direct Command Line Input
	 * @return List beginning with the Command followed by Parameters
	 */
	private static ArrayList<String> parse(String commandLine)
	{
		if (commandLine != null)
		{
			// Regular Expression to conserve spaces within Double Quotes
			ArrayList<String> command = new ArrayList<String>(Arrays.asList(commandLine.split("[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)")));
			for (String element : command)
			{
				if (element.startsWith("-"))
					element.replaceFirst("-", "");
			}
			return command;
		}
		return null;
	}

	/**
	 * Reads Output from Process execution and displays to the console window
	 *
	 * @param processInputStream Input Stream of the Process which contains Process output
	 * @return Output of the Process as a String
	 * @throws IOException
	 */
	private static String readCommandOutput(InputStream processInputStream) throws IOException
	{
		String output = "";
		Reader reader = new InputStreamReader(processInputStream);
		int charVal;
		while ((charVal = reader.read()) != -1)
			output += (char)charVal;
		reader.close();
		return output;
	}

	/**
	 * Allows user to change directory using both Relative and Absolute Addressing
	 *
	 * @param command Command List as returned from parse method
	 * @param currentDirPath Current Working Directory of the Process
	 * @return New Working Directory or null if nonexistent
	 */
	private static File changeDirectory(ArrayList<String> command, String currentDirPath)
	{
		if (command.size() > 1)
		{
			String newDirPath = command.get(1);
			File dir;
			if (newDirPath.contains("\""))
				newDirPath = newDirPath.replaceAll("\"", "");
			if (newDirPath.startsWith("/"))
				dir = new File(newDirPath);
			else
				dir = new File(currentDirPath + "/" + newDirPath);
			if (dir.exists())
				return dir.getAbsoluteFile();
			else
				return null;
		}
		return new File(System.getProperty("user.home"));
	}
}
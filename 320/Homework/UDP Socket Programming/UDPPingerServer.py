# UDPPingerServer.py
import random
from socket import *

# Create a UDP Socket
server_socket = socket(AF_INET, SOCK_DGRAM) # SOCK_DGRAM for UDP
server_socket.bind(('', 12000)) # Assign IP and Port Number

print("Server Initialized\n")

while True:
    rand = random.randint(0, 10)
    message, address = server_socket.recvfrom(1024) # Listen for packets
    message = message.upper() # Display message
    print(message + " from " + address[0])
    print("")
    if rand < 4:
        continue # Consider Packet as Lost
    else:
        server_socket.sendto(message, address) # Server Responds
from socket import *
import datetime

PORT_NUMBER = 12000
IP_ADDRESS = "10.106.109.57"
TIMEOUT = 1
PACKETS_TO_TRANSMIT = 10

client_socket = socket(AF_INET, SOCK_DGRAM)
client_socket.settimeout(TIMEOUT)

print("\nTransmitting Packets\n")

for seq_num in range(1, PACKETS_TO_TRANSMIT + 1):
    try:
        send_time = datetime.datetime.now()
        message = "ping " + str(seq_num) + " " + send_time.time().strftime("%H:%M:%S:%f")
        client_socket.sendto(message, (IP_ADDRESS, PORT_NUMBER))

        response, response_address = client_socket.recvfrom(1024)
        response_time = datetime.datetime.now()

        rtt = response_time - send_time

        print(response + " from " + response_address[0] + " (ACK)")
        print("Packet " + str(seq_num) + " RTT: " + str(rtt.total_seconds())) + " seconds"
    except timeout:
        print("Request Timed Out: Packet " + str(seq_num) + " Lost")
    print("")

print("Transmission Complete")
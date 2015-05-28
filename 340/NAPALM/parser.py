from packet import *
from address import *
from util import *

TCP_DUMP_PATH = "/Users/Nick/Developer/Computer_Science/340/Assignments/NAPALM/tcpdump.txt"


def output(file, string):
    if file is None:
        print(string)
    else:
        file.write(string)
        print("File Saved Successfully!")


def print_user_menu():
    print("1) All Packets")
    print("2) Protocol Usage")
    print("3) FTP Server IP Addresses")
    print("4) FTP Account Credentials")
    print("5) IP Address Communications")
    print("6) MAC Addresses")
    print("!) Set Output File Path")
    print("?) Display Instructions")
    print("-1) Exit")


def print_instructions():
    print("Enter the number of the operation you want to execute.")
    print("By default, all output is directed to the standard output.")
    print("If you would like to write to an output file, first enter an exclamation point (!) to set the file path.")
    print("After setting the file path, add an exclamation point (!) after any selection to write the output to file.")
    print("For example, use \"4!\" to write all FTP account credentials to the output file.")
    print("Note: Some operations may take upwards of 1 minute to execute. Please be patient.")


def print_packet_total(packet_list, file=None):
    string = "+" + "-"*21 + "|\n"
    string += "|{0:^21}|\n".format(separate_thousands(len(packet_list)) + " Packets")
    string += "+" + "-"*21 + "|\n"
    string += "\n"
    output(file, string)



def print_all_packets(packets_list, file=None):
    string = "+" + "-"*75 + "|\n"
    string += "|{0:<10}{1:^15}{2:^20}{3:^20}{4:^10}|\n".format("Number", "T+", "Sender", "Receiver", "Protocol")
    string += "+" + "-"*75 + "|\n"
    for packet in packets_list:
        string += "|{0:<10}{1:^15}{2:^20}{3:^20}{4:^10}|\n".format(packet.packet_number, "{0:.2f}".format(packet.timestamp), packet.sender_ip, packet.receiver_ip, packet.protocol)
    string += "+" + "-"*75 + "|\n"
    string += "\n"
    output(file, string)


def print_packets_in_range(packets_list, first_packet, last_packet, file=None):
    string = "+" + "-"*75 + "|\n"
    string += "|{0:^10}{1:^15}{2:^20}{3:^20}{4:^10}|\n".format("Number", "T+", "Sender", "Receiver", "Protocol")
    string += "+" + "-"*75 + "|\n"
    for i in range(first_packet-1, last_packet):
        packet = packets_list[i]
        string += "|{0:^10}{1:^15}{2:^20}{3:^20}{4:^10}|\n".format(packet.packet_number, "{0:.2f}".format(packet.timestamp), packet.sender_ip, packet.receiver_ip, packet.protocol)
    string += "+" + "-"*75 + "|\n"
    string += "\n"
    output(file, string)


def print_protocol_usage(sorted_protocol_usage_dict, packets_list, file=None):
    string = "+" + "-"*45 + "+\n"
    string += "|{0:<15}{1:^15}{2:^15}|\n".format("Protocol", "Count", "Pct")
    string += "+" + "-"*45 + "+\n"
    for protocol, usage in sorted_protocol_usage_dict.items():
        usage_str = "{0:05.2f}".format(round(((usage / len(packets_list)) * 100), 2))
        string += "|{0:<15}{1:>15}{2:^15}|\n".format(protocol, (str(usage) + " / " + str(len(packets_list))), usage_str)
    string += "+" + "-"*45 + "+\n"
    string += "\n"
    output(file, string)


def print_all_receivers(sender_address, communications, file=None):
    string = ""
    if address_is_ip(sender_address):
        try:
            string += "+" + "-"*15 + "+\n"
            string += "|{0:^15}|\n".format(sender_address)
            string += "+" + "-"*15 + "+\n"
            receivers = communications[sender_address]
            for receiver in receivers:
                string += "|{0:^15}|\n".format(receiver)
            string += "+" + "-"*15 + "+\n"
            string += "\n"
        except KeyError:
            print(sender_address + " was not found in the log!")
            return
    elif sender_address == "*":
        for sender, receivers in communications.items():
            string += "+" + "-"*15 + "+\n"
            string += "|{0:^15}|\n".format(sender)
            string += "+" + "-"*15 + "+\n"
            receivers = communications[sender]
            for receiver in receivers:
                string += "|{0:^15}|\n".format(receiver)
            string += "+" + "-"*15 + "+\n"
            string += "\n"
    else:
        print(sender_address + " is not a valid IP Address")
        return
    output(file, string)


def print_ftp_credentials(accounts, file=None):
    string = "+" + "-"*40 + "+\n"
    string += "|{0:^20}{1:^20}|\n".format("User", "Password")
    string += "+" + "-"*40 + "+\n"
    for username, password in accounts.items():
        string += "|{0:^20}{1:^20}|\n".format(username, password)
    string += "+" + "-"*40 + "+\n"
    string += "\n"
    output(file, string)


def print_ftp_server_details(ftp_server_ips, file=None):
    string = "+" + "-"*35 + "+\n"
    string += "|{0:^35}|\n".format("FTP Server IP Addresses")
    string += "+" + "-"*35 + "+\n"
    for ftp_server_ip in ftp_server_ips:
        string += "|{0:^35}|\n".format(ftp_server_ip)
    string += "+" + "-"*35 + "+\n"
    string += "\n"
    output(file, string)


def print_ip_to_mac_map(ip_to_mac_dict, file=None):
    string = "+" + "-"*40 + "+\n"
    string += "|{0:^20}{1:^20}|\n".format("IP Address", "MAC Address")
    string += "+" + "-"*40 + "+\n"
    for ip, mac in ip_to_mac_dict.items():
        string += "|{0:^20}{1:^20}|\n".format(ip, mac)
    string += "+" + "-"*40 + "+\n"
    string += "\n"
    output(file, string)


def find_all_communications(packets_list):
    communications = OrderedDict()  # Sender IP: [Receiver IPs]
    for packet in packets_list:
        if address_is_ip(packet.sender_ip):
            if packet.sender_ip not in communications.keys():
                communications[packet.sender_ip] = []
            if address_is_ip(packet.receiver_ip) and packet.receiver_ip not in communications[packet.sender_ip]:
                communications[packet.sender_ip].append(packet.receiver_ip)
    return communications


def track_ftp_server_ips(packets_list):
    ftp_server_ips = []
    for packet in packets_list:
        if packet.protocol == "FTP":
            if "Response" in packet.info and packet.sender_ip not in ftp_server_ips:
                ftp_server_ips.append(packet.sender_ip)
    return ftp_server_ips


def track_ftp_accounts(packets_list):
    accounts = OrderedDict()  # Username: Password
    for packet in packets_list:
        if packet.protocol == "FTP":
            if "Request: USER" in packet.info:
                user_request_packet = packet
                username = user_request_packet.info.split(" ")[-1]
                if username not in accounts.keys():
                    accounts[username] = ""
                for pass_request_response_packet in packets_list[user_request_packet.packet_number+1:]:
                    if "Response: 331" in pass_request_response_packet.info:
                        for pass_attempt_packet in packets_list[user_request_packet.packet_number+1:]:
                            if "Request: PASS" in pass_attempt_packet.info:
                                for pass_verify_packet in packets_list[pass_attempt_packet.packet_number+1:]:
                                    if "Response: 230" in pass_verify_packet.info:
                                        accounts[username] = pass_attempt_packet.info.split(" ")[-1]
                                        break
                                break
                        break
    return accounts


def track_mac_addresses(packets_list):
    ip_to_mac = {}  # IP Address: MAC Address
    for packet in packets_list:
        if packet.protocol == "ARP":
            if "Who has" not in packet.info:
                _, ip, _, _, mac = packet.info.split(" ")
                if ip not in ip_to_mac.keys():
                    ip_to_mac[ip] = mac
    return ip_to_mac


def generate_protocol_usage(packets_list):
    protocol_usage = {}  # Protocol: Times Used
    for packet in packets_list:
        if packet.protocol in protocol_usage.keys():
            protocol_usage[packet.protocol] += 1
        else:
            protocol_usage[packet.protocol] = 1
    return protocol_usage


def extract_packets(tcp_dump_file):
    packets = []
    for line in tcp_dump_file:
        components = [s for s in line.split() if s != ""]
        packet_number = components[0]
        time_since_start = components[1]
        sender_ip = components[2]
        receiver_ip = components[4]
        protocol = components[5]
        info = " ".join(components[6:])
        packets.append(Packet(int(packet_number), float(time_since_start), sender_ip, receiver_ip, protocol, info))
    return packets


def main():
    packets = extract_packets(open(input("Path to TCP Dump: "), 'r'))
    out_file = None
    print_instructions()
    print()
    running = True
    while running:
        print_user_menu()
        select = input("Number of Operation to Execute: ")
        if select == "1" or select == "1!":  # Print All Packets
            if "!" in select and out_file is not None:
                print_all_packets(packets, file=out_file)
            else:
                print_all_packets(packets)
        elif select == "2" or select == "2!":  # Print Protocol Usage
            if "!" in select and out_file is not None:
                print_protocol_usage(sort_dict_by_value(generate_protocol_usage(packets)), packets, file=out_file)
            else:
                print_protocol_usage(sort_dict_by_value(generate_protocol_usage(packets)), packets)
        elif select == "3" or select == "3!":  # Print FTP Server Details
            if "!" in select and out_file is not None:
                print_ftp_server_details(track_ftp_server_ips(packets), file=out_file)
            else:
                print_ftp_server_details(track_ftp_server_ips(packets))
        elif select == "4" or select == "4!":  # Print FTP Account Credentials
            if "!" in select and out_file is not None:
                print_ftp_credentials(track_ftp_accounts(packets), file=out_file)
            else:
                print_ftp_credentials(track_ftp_accounts(packets))
        elif select == "5" or select == "5!":  # Print IP Address Communications
            if "!" in select and out_file is not None:
                print_all_receivers(input("Enter IP Address or * for All Addresses: "), find_all_communications(packets), file=out_file)
            else:
                print_all_receivers(input("Enter IP Address or * for All Addresses: "), find_all_communications(packets))
        elif select == "6" or select == "6!":  # Print MAC Addresses
            if "!" in select and out_file is not None:
                print_ip_to_mac_map(track_mac_addresses(packets), file=out_file)
            else:
                print_ip_to_mac_map(track_mac_addresses(packets))
        elif select == "!":  # Set Output File
            file_path = input("Output File Path: ")
            try:
                out_file = open(file_path, 'w')  # Initialize the file to blank state
                out_file.close()
                print("File Loaded!")
                out_file = open(file_path, 'a')  # Open the file with appending writes
            except FileNotFoundError:
                print("Error: File Not Found")
            except IOError:
                print("Error: IO Operation Failed")
        elif select == "?":
            print_instructions()
        elif select == "-1":  # Exit
            running = False
        else:  # Invalid Operation
            print("Invalid Operation. Try Again.\n")
        print()
    if out_file is not None:
        out_file.close()
    print("Goodbye!")

main()
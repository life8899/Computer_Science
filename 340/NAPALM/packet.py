class Packet:
    """
    Represents a packet from a TCP Dump
    """
    def __init__(self, packet_number, timestamp, sender_ip, receiver_ip, protocol, info):
        self.packet_number = packet_number
        self.timestamp = timestamp
        self.sender_ip = sender_ip
        self.receiver_ip = receiver_ip
        self.protocol = protocol
        self.info = info

    def __str__(self):
        return "Packet\t{0:>6}\tafter\t{1:^10}\tfrom\t{2:<15}\t\tto\t{3:<15}\t\tusing protocol\t{4:<8}".format(self.packet_number, "{0:.2f}".format(self.timestamp), self.sender_ip, self.receiver_ip, self.protocol)

    def __repr__(self):
        return self.__str__()
# Set of useful functions for dealing with IP and MAC Addresses


def hex_ip(ip_address):
    """
    Returns a Hexadecimal representation of an IP address
    :param ip_address: Decimal IP Address
    :return: Hexadecimal IP Address
    """
    if ip_is_hex(ip_address):
        return ip_address
    else:
        h_ip = ""
        for chunk in ip_address.split("."):
            h_ip += str(hex(int(chunk))) + ":"
        return h_ip[:-1].replace("0x", "")


def decimal_ip(ip_address):
    """
    Returns a Decimal representation of an IP Address
    :param ip_address: Hexadecimal IP Address
    :return: Decimal IP Address
    """
    if ip_is_dec(ip_address):
        return ip_address
    else:
        d_ip = ""
        for chunk in ip_address.split(":"):
            d_ip += str(int(chunk, 16)) + "."
        return d_ip[:-1]


def ip_is_dec(ip_address):
    """
    Tests if an IP Address is in Decimal Format
    :param ip_address: IP Address to test
    :return: True if IP Address is in Decimal Format
    """
    if "." in ip_address:
        return True
    else:
        return False


def ip_is_hex(ip_address):
    """
    Tests if an IP Address is in Hexadecimal Format
    :param ip_address: IP Address to test
    :return: True if IP Address is in Hexadecimal Format
    """
    if ":" in ip_address:
        return True
    else:
        return False


def address_is_ip(s):
    """
    Tests if a given string is an IP Address
    :param s: Address string to test
    :return: True if the string is a proper IP Address
    """
    if ":" in s:
        if len(s.split(":")) > 4:
            return False
        else:
            return True
    elif "." in s:
        if len(s.split(".")) > 4:
            return False
        else:
            return True
    else:
        return False


def address_is_mac(address):
    """
    Tests if a given string is a MAC Address
    :param address: Address string to test
    :return: True if the string is a proper MAC Address
    """
    return not address_is_ip(address)
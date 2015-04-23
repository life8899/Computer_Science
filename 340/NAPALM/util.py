from collections import OrderedDict
import locale
import datetime

# Set of general purpose functions


def sort_dict_by_value(dict_to_sort):
    """
    Sorts a dictionary into an ordered dictionary based on the values
    :param dict_to_sort: Dictionary to be sorted
    :return: Ordered Dictionary sorted by the values
    """
    return OrderedDict(sorted(dict_to_sort.items(), key=lambda e: e[1]))


def sort_dict_by_key(dict_to_sort):
    """
    Sorts a dictionary into an ordered dictionary based on the keys
    :param dict_to_sort: Dictionary to be sorted
    :return: Ordered Dictionary sorted by the keys
    """
    return OrderedDict(sorted(dict_to_sort.items(), key=lambda e: e[0]))


def separate_thousands(number):
    """
    Formats a number into a string with the thousands separator
    :param number: Number to be formatted
    :return: String representation of the number with the thousands separator
    """
    locale.setlocale(locale.LC_ALL, '')
    return locale.format("%d", number, grouping=True)


def current_time():
    """
    Returns a locale-based representation of the current time
    :return: Locale-based representation of the current time
    """
    return datetime.datetime.now().strftime("%I:%M:%S %p")
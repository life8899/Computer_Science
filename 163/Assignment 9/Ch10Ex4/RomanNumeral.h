#include <string>

class RomanNumeral {

private:
	std::string numeral;
	int decimal;
	int valueOf(char);
	char valueOf(int);

public:
	RomanNumeral();
	RomanNumeral(std::string);
	RomanNumeral(int);
	std::string convertToNumeral();
	int convertToDecimal();
	std::string asNumeral();
	int asDecimal();

};
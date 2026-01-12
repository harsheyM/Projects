//Harshitha Mahesh
//dal267662

#include "Term.h"
#include <iostream>
#include <sstream>
#include "Fraction.h"

Term::Term(int c, int e) {
	coefficient = Fraction(c, 1);
	exponent = e;
}

bool Term::operator<(const Term& other) {
	if (this->getExponent() < other.getExponent()) {
		return true;
	}
	return false;
}

bool Term::operator>(const Term& other) {
	if (this->getExponent() > other.getExponent()) {
		return true;
	}
	return false;
}

Term Term::operator+(const Term& other) {
    Fraction newCoeff = this->getCoefficient() + other.getCoefficient();
    return Term(newCoeff, this->getExponent());
}


ostream& operator<<(ostream& out, const Term& t) {
	const Fraction& coeff = t.getCoefficient();
	int exp = t.getExponent();

	//Check the coefficient
	ostringstream coeffS;
	coeffS << coeff;
	string coeffStr = coeffS.str();

	bool isOne = coeffStr == "1";
	bool isNegOne = coeffStr == "-1";
	
	//If coefficient is zero, just print 0 and return
    if (coeff.numerator == 0) {
        out << "0";
        return out;
    }

	//Print the coefficients
	if (exp != 0) {
		if (isOne) {
			//Don't do anything
		} else if (isNegOne) {
			out << "-";
		} else {
			out << coeffStr;
		}
	} else {
		out << coeffStr;
	}

	//Handle exponent
	if (exp == 0) {
		//Nothing to print
	}
	else if (exp == 1) {
		out << "x";
	}
	else {
		out << "x^" << exp;
	}

	return out;
}

string Term::toString() {
    ostringstream out;
    out << this->getCoefficient() << "x^" << this->getExponent();
    
    return out.str();

}
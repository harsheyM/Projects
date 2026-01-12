//Harshitha Mahesh
//dal267662

#ifndef FRACTION_H
#define FRACTION_H

#include <iostream>
#include <numeric>

using namespace std;

struct Fraction {
	int numerator;
	int denominator;

	void setDenominator(int d) {
		denominator = d;
	}

	void setNumerator(int n) {
		numerator = n;
	}

	//Used to find the greatest common denominator
	int gcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}

	//Automatically simplifies the fractions using gcd method
	Fraction(int num = 0, int denom = 1) {
		int g = gcd(num, denom);

		if (denom < 0) {
			numerator = -num / g;
		} else {
			numerator = num / g;
		}

		denominator = abs(denom) / g;
	}

	//Comparison
	bool operator<(const Fraction& other) {
		return (static_cast<long long>(numerator) * other.denominator) < (static_cast<long long>(other.numerator) * denominator);
	}
	bool operator>(const Fraction& other) {
		return (static_cast<long long>(numerator) * other.denominator) > (static_cast<long long>(other.numerator) * denominator);
	}
	bool operator==(const Fraction& other) {
		return (static_cast<long long>(numerator) * other.denominator) == (static_cast<long long>(other.numerator) * denominator);
	}

	//Overloaded operators
	Fraction operator+(const Fraction& other) {
		int num = this->numerator * other.denominator + other.numerator * this->denominator;
		int denom = this->denominator * other.denominator;
		return Fraction(num, denom);
	}

	Fraction operator-(const Fraction& other) {
		int num = this->numerator * other.denominator - other.numerator * this->denominator;
		int denom = this->denominator * other.denominator;
		return Fraction(num, denom);
	}

	Fraction operator*(const Fraction& other) {
		return Fraction(this->numerator * other.numerator, this->denominator * other.denominator);
	}

	Fraction operator/(const Fraction& other) {
		return Fraction(this->numerator * other.denominator, this->denominator * other.numerator);
	}

	friend std::ostream& operator<<(std::ostream& out, const Fraction& f) {
		int num = f.numerator;
		int denom = f.denominator;
		
        //Deals with negative coefficients when the negative is in the denominator
		if (denom < 0) {
			num *= -1;
			denom *= -1;
		}
        
        //Checks if its a rational number or integer
		if (denom == 1)
			out << num;
		else
			out << "(" << num << "/" << denom << ")";
		return out;
	}
};

#endif
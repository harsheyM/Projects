//Harshitha Mahesh
//dal267662

#include <iostream>
#include <string>
using namespace std;

class Fraction {
public:
    //numerator
    int num; 
    //denominator, always positive
    int den; 

    //Constructor
    Fraction(int n = 0, int d = 1) {
        //prevent division by zero
        if (d == 0) d = 1; 
        num = n;
        den = d;
        simplify();       
    }

    //Compute greatest common divisor
    static int gcd(int a, int b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return (a == 0 ? 1 : a);
    }

    //Simplify the fraction
    void simplify() {
        if (num == 0) { 
            den = 1; 
            return; 
        }
        
        int g = gcd(num, den);
        num /= g;
        den /= g;
        
        //keep denominator positive
        if (den < 0) { 
            den = -den;
            num = -num;
        }
    }

    //Arithmetic operators
    Fraction operator+(const Fraction& other) const {
        return Fraction(num * other.den + other.num * den, den * other.den);
    }

    Fraction operator-(const Fraction& other) const {
        return Fraction(num * other.den - other.num * den, den * other.den);
    }

    Fraction operator*(const Fraction& other) const {
        return Fraction(num * other.num, den * other.den);
    }

    Fraction operator/(const Fraction& other) const {
        return Fraction(num * other.den, den * other.num);
    }

    bool isZero() const { 
        return num == 0; 
    }

    double toDouble() const { 
        return static_cast<double>(num) / den; 
    }

    string str() const {
        if (den == 1) return to_string(num);
        return "(" + to_string(num) + "/" + to_string(den) + ")";
    }

    bool operator==(const Fraction& other) const {
        return num == other.num && den == other.den;
    }

    friend ostream& operator<<(ostream& out, const Fraction& f) {
        out << f.str();
        return out;
    }
};

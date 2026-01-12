//Harshitha Mahesh
//dal267662

#include <iostream>
#include <string>
#include <cmath>
#include "Term.h"

bool Term::sameExponent(const Term& other) const {
	return type == TermType::POLY && other.type == TermType::POLY && exp == other.exp;
}

bool Term::operator<(const Term& other) const {
	if (exp != other.exp) return exp < other.exp;
	return orderEncountered < other.orderEncountered;
}

bool Term::operator>(const Term& other) const {
	if (exp != other.exp) return exp > other.exp;
	return orderEncountered > other.orderEncountered;
}

std::ostream& operator<<(std::ostream& out, const Term& t) {
    //If coefficient is zero, print nothing
    if (t.coeff.isZero()) { return out; }

    //Handle negative sign
    bool negative = t.coeff.num < 0;
    Fraction absCoeff = t.coeff;
    if (negative) { absCoeff.num = -absCoeff.num; }

    if (negative) { out << "-"; }

    if (t.type == TermType::POLY) {
        //Polynomial term
        if (t.exp == 0) {
            //Just the constant
            out << absCoeff;
        } else {
            //Only show coefficient if it's not 1
            if (!(absCoeff.num == 1 && absCoeff.den == 1)) {
                out << absCoeff;
            }
            out << "x";
            if (t.exp != 1) {
                out << "^" << t.exp;
            }
        }
    } else {
        //Trigonometric term
        //Only print coefficient if it's not 1
        if (!(absCoeff.num == 1 && absCoeff.den == 1)) {
            out << absCoeff << " ";
        }
        //inner coefficient
        out << (t.type == TermType::TRIG_SIN ? "sin " : "cos ");
        out << (t.innerK == 1 ? "x" : std::to_string(t.innerK) + "x");
    }

    return out;
}

//Harshitha Mahesh
//dal267662

#ifndef TERM_H
#define TERM_H

#include <iostream>
#include <string>
#include <cmath>
#include "Fraction.h"


//Determines if trig or not
enum class TermType { POLY, TRIG_SIN, TRIG_COS };

class Term {
public:
    Fraction coeff;
    int exp;
    TermType type;
    int innerK;
    int orderEncountered;
    
    //Constructors
    Term() : coeff(0,1), exp(0), type(TermType::POLY), innerK(1), orderEncountered(-1) {}
    Term(const Fraction& c, int e) : coeff(c), exp(e), type(TermType::POLY), innerK(1), orderEncountered(-1) {}
    Term(const Fraction& c, TermType t, int k, int order = -1) : coeff(c), exp(-9999), type(t), innerK(k), orderEncountered(order) {}

    //Comparisn/Overloaded Operators
    bool sameExponent(const Term& other) const;

    bool operator<(const Term& other) const;

    bool operator>(const Term& other) const;

    friend std::ostream& operator<<(std::ostream& os, const Term& t);

};

#endif

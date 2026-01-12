//Harshitha Mahesh
//dal267662

#ifndef TERM_H
#define TERM_H
#include <iostream>
#include "Fraction.h"

class Term {
    private: 
    Fraction coefficient;
    int exponent;
    
    public:
    //Constructor
    Term() { coefficient = 0; exponent = 0; }
    Term(Fraction c, int e) { coefficient = c; exponent = e; }
    Term(int c, int e);
    
    //Accessor
    Fraction getCoefficient() const { return coefficient; }
    int getExponent() const { return exponent; }
    
    //Mutators
    void setCoefficient(Fraction c) {coefficient = c;}
    void setExponent(int e) {exponent = e;}
    
    //Comparisn/CompareTo Methods
    bool operator<(const Term& other);
    bool operator>(const Term& other);
    
    //toString
    string toString();
    
    //Operators
    Term operator+(const Term& other);
    friend std::ostream& operator<<(std::ostream&, const Term&);

};
#endif
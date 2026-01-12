//Harshitha Mahesh
//dal267662

#include "Term.h"
#include "BinTree.h"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <utility>
#include <sstream>
#include <format>
#include <iomanip>
#include <algorithm>
#include <cmath>



using namespace std;

//Used to split apart the line we're reading to get Terms
vector<string> split(const string& input);

//Used to integrate each term
Term integratedTerm(Term term1);

//Used to find definite integral
double evaluateTerm(const Term& t, int x);

//Convert fraction to decimal
double toDecimal(const Fraction& f);



int main()
{
	cout << "Enter filename: ";
	string fileName;
	cin >> fileName;

	ifstream input;
	string line;
	//check if file is valid
	input.open(fileName);
	if (!input)
	{
		return 1;
	}

	//Review the file line at a time
	while (getline(input, line)) {
		BinTree<Term> tree;

		//Split the line into terms
		vector<string> terms = split(line);

		//Remove all whitespace characters from each term
		for (auto &t : terms) {
			t.erase(remove_if(t.begin(), t.end(), ::isspace), t.end());
		}




		pair<int, int> bounds;
		bool isBounds = false;


		//Check if the integral is definite
		if (terms[0].length() > 1) {
			isBounds = true;
			int index = terms[0].find("|");

			//Lower bound
			bounds.first = stoi(terms[0].substr(0, index));

			//Upper bound
			bounds.second = stoi(terms[0].substr(index + 1));
		}

		//The term has x^ in it, so we're trying to find the coefficient and exponent
		for (int i = 1; i < terms.size(); i++) {

			//if the index is a plus or minus (referring to addition or subtraction)
			if (terms[i] == "+" || terms[i] == "-" || terms[i] == "|" || terms[i] == "dx") {
				continue;
			}

			//Find the indexes of those values in the term
			int indexX = terms[i].find("x");
			int indexE = terms[i].find("^");

			int coefficient;
			int exponent;

            //Used to store the coefficient as a string
			string coeffStr;

            //Handle different cases for the coefficient
			if (indexX == string::npos) {
				//if term has no x
				coeffStr = terms[i];
				exponent = 0;
			} else {
				//if term has x
				if (indexX == 0) {
				    //plain "x" or "x^n"
					coeffStr = "1"; 
				} else if (indexX == 1 && terms[i][0] == '-') {
				    //"-x" or "-x^n"
					coeffStr = "-1"; 
				} else if (indexX == 1 && terms[i][0] == '+') {
				    //"+x" or "+x^n"
					coeffStr = "1"; 
				} else {
					coeffStr = terms[i].substr(0, indexX); 
				}

				// Determine exponent
				if (indexE == string::npos) {
				    //x with no ^
					exponent = 1; 
				} else {
				    //x^n
					exponent = stoi(terms[i].substr(indexE + 1)); 
				}
			}

			try {
				coefficient = stoi(coeffStr);

				// Check for previous "-" sign in expression
				if (i > 0 && terms[i - 1] == "-") {
					coefficient *= -1;
				}

			} catch (const invalid_argument&) {
				cerr << "Invalid coefficient in term: " << terms[i] << endl;
				continue;
			}


			//add the term to the tree to be sorted
			Term term1(coefficient, exponent);
			tree.insert(term1);
		}

		//returns the sorted tree
		vector<Term> parsedTerms = tree.inOrderTraversal();
		bool first = true;

		double definiteResult;

		//if there are bounds, use these to calculate
		double upperSum = 0.0;
		double lowerSum = 0.0;

		for (int i = parsedTerms.size() - 1; i >= 0; --i) {
			//returns the terms of the tree
			Term integrated = integratedTerm(parsedTerms[i]);

			//returns it in the correct format
			ostringstream out;
			out << integrated;
			string termStr = out.str();

			//Checks if the term is negative or not
			bool isNegative = termStr.size() >= 2 && termStr[0] == '(' && termStr[1] == '-';

			//Prints out the correct information
			if (first) {
				cout << termStr;
				first = false;
			} else {
				if (isNegative) {
					cout << " - " << "(" << termStr.substr(2);
				} else if (termStr[0] == '-') {
					cout << " - " << termStr.substr(1);
				} else {
					cout << " + " << termStr;
				}
			}

			//Calculates the upperbound and lowerbound sums of the intergral
			//Returns the fraction in decimal form
			if (isBounds) {
				upperSum += evaluateTerm(integrated, bounds.second);
				lowerSum += evaluateTerm(integrated, bounds.first);
			}

		}

		if (isBounds) {
			cout << ", " << bounds.first << "|" << bounds.second << " = ";

			definiteResult = upperSum - lowerSum;
			//Converts the fraction to a decimal
			cout << fixed << setprecision(3) << definiteResult << "\n";
		}
		else {
			cout << " + C\n";
		}
	}

	input.close();
	return 0;
}

vector<string> split(const string& input) {
	vector<string> elements;
	string expr = input;

	//Add bounds to vector (eg 1|3)
	int spacePos = expr.find(" ");

	//Checks where the space is and uses that info to add the bounds
	if (spacePos != string::npos) {
		string bounds = expr.substr(0, spacePos);
		elements.push_back(bounds);
		//Remove bounds from expression
		expr = expr.substr(spacePos + 1);
	}

	//Add dx
	size_t dxPos = expr.find("dx");
	if (dxPos != string::npos) {
		string beforeDx = expr.substr(0, dxPos);
		expr = beforeDx;
		elements.push_back("dx");
	}

	//Add the remaining terms
	string current;
	for (size_t i = 0; i < expr.length(); ++i) {
		//Individually reviews the chars
		char c = expr[i];

		//If its an operand, add seperatly
		//If the operand is seen, push the term in
		//makes sure that we don't split a negative exponent
		if ((c == '+' || c == '-') && i > 0 && expr[i - 1] != '^') {
			// Only split on + or - if it's NOT part of an exponent
			if (!current.empty()) {
				elements.push_back(current);
				current.clear();
			}
			elements.push_back(string(1, c));
		} else {
			current += c;
		}
	}


	if (!current.empty()) {
		elements.push_back(current);
	}

	return elements;
}


Term integratedTerm(Term term1) {
	Fraction c = term1.getCoefficient();
	int e = term1.getExponent();
	term1.setExponent(e + 1);
	term1.setCoefficient(Fraction(c.numerator, e + 1));

	return term1;
}

double evaluateTerm(const Term& t, int x) {
    //Calculates the fraction 
    double coeff = static_cast<double>(t.getCoefficient().numerator) / t.getCoefficient().denominator;
    int exp = t.getExponent();

    if (x == 0 && exp < 0) {
        return 0.0; //avoid division by zero
    }
    
    //Calculates the answer
    return coeff * pow(static_cast<double>(x), exp);
}




double toDecimal(const Fraction& f) {
	return static_cast<double>(f.numerator) / f.denominator;
}

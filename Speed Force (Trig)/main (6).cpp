//Harshitha Mahesh
//dal267662

#include <bits/stdc++.h>
#include "Term.h"
#include "BinTree.h"
using namespace std;

static string trim(string s);

vector<string> splitTerms(const string& expr);

Term parseTerm(const string& term, int trigOrder);

int safeParseInt(const string& s, int defaultVal);


int main() {
	cout << "Enter filename: ";
	string file;
	getline(cin, file);

	ifstream fin(trim(file));
	if (!fin) {
		cerr << "File not found.\n";
		return 1;
	}

	string line;
	int trigCount = 0;

	//Traverse through every line in the file
	while (getline(fin, line)) {
		//remove white psace
		line = trim(line);
		//if there's nothing in the file skip to next
		if (line.empty()) {
			continue;
		}

		//Parse through the line
		//Checks the bounds
		bool definite = false;
		int lower = 0, upper = 0;
		string expr;

		//Remove trailing "dx"
		size_t dx = line.rfind("dx");
		if (dx != string::npos) line.erase(dx);

		//Split at '|'
		size_t pipe = line.find('|');
		//If it doesn't exist (which is never)
		if (pipe == string::npos) {
			expr = line;
		} else {
			//divides the expression based on location of pipe
			string left = trim(line.substr(0, pipe));
			string right = trim(line.substr(pipe + 1));
			size_t idx;
			try {
				// Try parsing lower bound
				int tempLower = stoi(left, &idx);
				if (idx == left.size()) {
					lower = tempLower;

					// Parse upper bound
					stringstream ss(right);
					ss >> upper;

					definite = true;

					// Extract remaining expression
					string rest;
					getline(ss, rest);
					expr = trim(rest);
				} else {
					// If left contains junk after number, treat as indefinite
					expr = right;
				}
			} catch (const std::exception& e) {
				// If parsing fails, treat as indefinite
				expr = right;
			}


		}
		//removes white space
		expr = trim(expr);

		//create tree
		vector<string> terms = splitTerms(expr);
		BinTree<Term> tree;
		vector<Term> trigTerms;

		//Iterates through each term in order to parse it
		for (auto& tk : terms) {
			Term t = parseTerm(tk, trigCount);
			//checks if its a trig or not
			if (t.type == TermType::TRIG_SIN || t.type == TermType::TRIG_COS) {
				trigTerms.push_back(t);
				trigCount++;
			}
			tree.insert(t);
		}

		//integrate terms
		vector<Term> collected;
		tree.inorderCollect(collected);

		vector<Term> polyInt;
		//if terms are polynomials
		for (auto& t : collected) {
			if (t.type == TermType::POLY) {
				Fraction newCoeff = t.coeff / Fraction(t.exp + 1, 1);
				polyInt.emplace_back(newCoeff, t.exp + 1);
			}
		}

		//if terms are trig
		vector<Term> trigInt;
		for (auto& t : trigTerms) {
			//calculates the inner coeff
			Fraction newCoeff = t.coeff / Fraction(t.innerK, 1);
			//saves information
			if (t.type == TermType::TRIG_SIN) {
				trigInt.emplace_back(Fraction(-newCoeff.num, newCoeff.den),
				                     TermType::TRIG_COS, t.innerK, t.orderEncountered);
			}
			else {
				trigInt.emplace_back(newCoeff,
				                     TermType::TRIG_SIN, t.innerK, t.orderEncountered);
			}
		}



		//Format the output
		ostringstream out;
		bool first = true;

		//Polynomial terms
		for (auto& t : polyInt) {
			//check if coefficient is zero
			if (t.coeff.isZero()) {
				continue;
			}
			if (!first) {
				out << (t.coeff.num < 0 ? " - " : " + ");
			}
			else if (t.coeff.num < 0) {
				out << "-";
			}
			Fraction absC = t.coeff;
			if (absC.num < 0) {
				absC.num = -absC.num;
			}

			if (t.exp == 0) {
				out << absC;
			}
			else {
				if (!(absC.den == 1 && absC.num == 1)) {
					out << absC;
				}
				out << "x";
				if (t.exp != 1) {
					out << "^" << t.exp;
				}
			}
			first = false;
		}

		//Trig terms
		for (auto& t : trigInt) {
			//Formats based on first element
			if (!first) {
				out << (t.coeff.num < 0 ? " - " : " + ");
			}
			else if (t.coeff.num < 0) {
				out << "-";
			}
			Fraction absC = t.coeff;
			if (absC.num < 0) {
				absC.num = -absC.num;
			}

			bool omitOne = (absC.den == 1 && absC.num == 1);
			if (!omitOne) {
				out << absC << " ";
			}
			out << (t.type == TermType::TRIG_SIN ? "sin " : "cos ");
			if (t.innerK != 1) {
				out << t.innerK;
			}
			out << "x";
			first = false;
		}

		if (first) {
			out << "0";
		}

		string resultExpr = out.str();

		//Printing it out
		if (!definite) {
			cout << resultExpr << " + C\n";
		} else {
			double upperVal = 0.0, lowerVal = 0.0;
			for (auto& t : polyInt) {
				double c = t.coeff.toDouble();
				upperVal += c * pow(upper, t.exp);
				lowerVal += c * pow(lower, t.exp);
			}
			double val = upperVal - lowerVal;
			cout << resultExpr << ", " << upper << "|" << lower
			     << " = " << fixed << setprecision(3) << val << "\n";
		}
	}

	return 0;
}

//Safe string-to-int conversion
int safeParseInt(const string& s, int defVal) {
	if (s.empty()) return defVal;
	stringstream ss(s);
	int x;
	char leftover;
	if ((ss >> x) && !(ss >> leftover)) return x;
	return defVal;
}


//removes leading and trailing whitespace
static string trim(string s) {
	//remove the leading spaces
	while (!s.empty() && isspace(s.front()))
		s.erase(s.begin());
	//remove the trailing spaces
	while (!s.empty() && isspace(s.back()))
		s.pop_back();
	return s;
}

//Split expression into different terms (+/- separated)
vector<string> splitTerms(const string& expr) {
    vector<string> out;
    string cur;
    for (size_t i = 0; i < expr.size();) {
        cur.clear();

        //Capture the leading sign
        if (expr[i] == '+' || expr[i] == '-') {
            cur += expr[i++];
        }

        //Read until the next '+' or '-' which is not part of exponent
        while (i < expr.size()) {
            if ((expr[i] == '+' || expr[i] == '-') && (i == 0 || expr[i - 1] != '^')) {
                break; // new term
            }
            cur += expr[i++];
        }
        
        //remove whitespace
        cur = trim(cur);
        if (!cur.empty()) {
            out.push_back(cur);
        }
    }
    return out;
}

//Parse a single term (including sin/cos)
Term parseTerm(const string& term, int trigOrder) {
	string t = term;
	//trim left
	t.erase(0, t.find_first_not_of(" \t"));
	//trim right
	t.erase(t.find_last_not_of(" \t") + 1);

	int sign = 1;
	if (!t.empty() && (t[0] == '+' || t[0] == '-')) {
		if (t[0] == '-') sign = -1;
		t = t.substr(1);
		//trim again
		t.erase(0, t.find_first_not_of(" \t"));
	}

	//change it all to lower case for comparsin
	string lower = t;
	for (char& c : lower) c = tolower(c);

	bool isSin = lower.find("sin") != string::npos;
	bool isCos = lower.find("cos") != string::npos;

	if (isSin || isCos) {
		TermType type = isSin ? TermType::TRIG_SIN : TermType::TRIG_COS;
		size_t funcPos = lower.find(isSin ? "sin" : "cos");

		//Coefficient before sin/cos
		Fraction outer(sign, 1);
		string before = t.substr(0, funcPos);
		//remove all spaces to isolate coefficient
		before.erase(0, before.find_first_not_of(" \t"));
		before.erase(before.find_last_not_of(" \t") + 1);
		if (!before.empty()) {
			outer = Fraction(sign * safeParseInt(before, 1), 1);
		}

		//Inner coefficient in trig
		int k = 1;
		string after = t.substr(funcPos + 3);
		after.erase(0, after.find_first_not_of(" \t"));
		after.erase(after.find_last_not_of(" \t") + 1);

		size_t xpos = after.find('x');
		if (xpos != string::npos) {
			//isolate coefficient
			string inside = after.substr(0, xpos);
			inside.erase(0, inside.find_first_not_of(" \t"));
			inside.erase(inside.find_last_not_of(" \t") + 1);
			k = safeParseInt(inside, 1);
		}
		

		return Term(outer, type, k, trigOrder);
	}

	//Polynomial term
	size_t xpos = t.find('x');
	
	Fraction coeff(sign, 1);
	int exp = 0;

	if (xpos == string::npos) {
		coeff = Fraction(sign * safeParseInt(t, 0), 1);
	} else {
		string before = t.substr(0, xpos);
		before.erase(0, before.find_first_not_of(" \t"));
		before.erase(before.find_last_not_of(" \t") + 1);
		if (!before.empty()) {
			coeff = Fraction(sign * safeParseInt(before, 1), 1);
		}

		size_t caret = t.find('^', xpos);
		
		if (caret != string::npos) {
			string expStr = t.substr(caret + 1);
			expStr.erase(0, expStr.find_first_not_of(" \t"));
			expStr.erase(expStr.find_last_not_of(" \t") + 1);
			exp = safeParseInt(expStr, 1);
		} else {
			exp = 1;
		}
	}

	return Term(coeff, exp);
}
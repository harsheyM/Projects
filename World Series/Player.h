//Harshitha Mahesh
//dal267662

#ifndef PLAYER_H
#define PLAYER_H
#include <string>
#include <sstream>
#include <sstream>
#include <format>
#include <iomanip>

using namespace std;


class Player {
public:
    string team;
	string name;
	int hits = 0;
	int walks = 0;
	int strikeOuts = 0;
	int hitByPitch = 0;
	int outs = 0;
	int sacrifices = 0;
	int errors = 0;

    //default constructor
    Player();
    
    //Overloaded constructor
    Player(string);
    Player(string, int);

	string getName() const { return name; }

	void trackData(string username, string result);

	string toString() const;
	
	double getBattingAverage() const;
	
	double getOnBasePercentage() const;
	
	bool operator==(const Player& other) { return this->name == other.name; }
	
	bool operator!=(const Player& other) { return this->name != other.name; }
	
	bool operator<(const Player& other) const { return name < other.name; }
	
	bool operator>(const Player& other) const { return name > other.name; }

    Player operator+(Player other);

#endif
};
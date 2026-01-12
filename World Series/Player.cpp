//Harshitha Mahesh
//dal267662

#include "Player.h"
#include <string>
#include <sstream>
#include <sstream>
#include <format>

//Empty player (new player)
Player::Player() {
	name = "";
	team = "";
	hits = 0;
	walks = 0;
	strikeOuts = 0;
	hitByPitch = 0;
	outs = 0;
	sacrifices = 0;
	errors = 0;
}

//Player with name
Player::Player(string n) {
    name = n;
}

//Player with name and hits
Player::Player(string n, int h) {
	name = n;
	hits = h;
}

Player Player::operator+(Player other) {
	//Adds up all the values and returns new Player object
	Player newPlayer;

	newPlayer.name = name;
	newPlayer.hits = hits + other.hits;
	newPlayer.walks = walks + other.walks;
	newPlayer.strikeOuts = strikeOuts + other.strikeOuts;
	newPlayer.hitByPitch = hitByPitch + other.hitByPitch;
	newPlayer.outs = outs + other.outs;
	newPlayer.sacrifices = sacrifices + other.sacrifices;
	newPlayer.errors = errors + other.errors;

	return newPlayer;
}

void Player::trackData(string username, string result) {
	name = username;

	//tracks how much of each category shows up
	//if it's part of that category it gets updated
	if (result == "HITS") {
		++hits;
	} else if (result == "WALK") {
		++walks;
	} else if (result == "STRIKEOUT") {
		++strikeOuts;
	} else if (result == "HIT BY PITCH") {
		++hitByPitch;
	} else if (result == "OUTS") {
		++outs;
	} else if (result == "SACRIFICE") {
		++sacrifices;
	} else if (result == "ERRORS") {
		++errors;
	}

}

//Batting average is calculated from hits / at-bats
//atBats include hits, strikeouts, outs, and errors
double Player::getBattingAverage() const {
	int atBats = hits + strikeOuts + outs + errors;
	if (atBats == 0) {
		return 0.000;
	}
	else {
	    //complete the calculations
		return ((double)hits) / atBats;

	}
}

//On base percentage = (hits + walks + HBP) / plate appearances
double Player::getOnBasePercentage() const {
    //calculate the data
    int atBats = hits + outs + strikeOuts + errors;
	int plateApp = atBats + walks + hitByPitch + sacrifices;
	if (plateApp == 0) {
		return 0.000;
	}
	else {
	    //complete the calculations
		return (double)(hits + walks + hitByPitch) / plateApp;
	}
}

string Player::toString() const {
	ostringstream out;
	//print out all of the values in order required
	
	//create these variables to hold atBats and plateApp
    int atBats = hits + outs + strikeOuts + errors;
    int plateApp = atBats + walks + hitByPitch + sacrifices;
    
    //set these requirments for organized formatting
	out << setw(12) << left << name << "\t"
        << atBats << "\t"
        << hits << "\t"
        << walks << "\t"
        << strikeOuts << "\t"
        << hitByPitch << "\t"
        << sacrifices << "\t"
        << fixed << setprecision(3) << getBattingAverage() << "\t"
        << fixed << setprecision(3) << getOnBasePercentage() << "\t"
        << plateApp << "\n";
        
    return out.str();

}


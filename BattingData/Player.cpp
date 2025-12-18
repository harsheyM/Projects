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
	hits = 0;
    walks = 0;
	strikeOuts = 0;
	hitByPitch = 0;
	outs = 0;
	sacrifices = 0;
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
	newPlayer.hits   = hits + other.hits;
	newPlayer.walks = walks + other.walks;
	newPlayer.strikeOuts = strikeOuts + other.strikeOuts;
	newPlayer.hitByPitch = hitByPitch + other.hitByPitch;
	newPlayer.outs = outs + other.outs;
	newPlayer.sacrifices = sacrifices + other.sacrifices;

	return newPlayer;
}

void Player::trackData(string username, string data) {
	name = username;
	//Loop tracks how much of each letter shows updatePlayers
	int i;
	for (i = 0; i < data.length(); ++i) {
		char one = data[i];
		switch (one) {
		//This text represents hits
		case 'H':
			++hits;
			break;

		//The text represents walks
		case 'W':
			++walks;
			break;

		//The text represents strike-outs
		case 'K':
			++strikeOuts;
			break;

		//The text represents hit by pitch
		case 'P':
			++hitByPitch;
			break;

		//The text represents outs
		case 'O':
			++outs;
			break;

		//The text represents all the extra/unnecessary letters and sacrifices
		case 'S':
			++sacrifices;
			break;
		}
	}
}

double Player::getBattingAverage() const {
    int total = hits + walks + strikeOuts + hitByPitch + outs + sacrifices;
    int totalOuts = strikeOuts + outs;
	if (hits + outs == 0 || total == 0) {
		stringstream convert;
		convert << fixed << setprecision(3) << 0.000;
		return stod(convert.str());
	}
	else {
		stringstream convert;
		convert << fixed << setprecision(3) << ((double) hits) / (hits + totalOuts);
		return stod(convert.str());

	}
}

double Player::getOnBasePercentage() const {
    int total = hits + walks + strikeOuts + hitByPitch + outs + sacrifices;
	if (hits + outs == 0 || total == 0) {
		return 0.000;
	}
	else {
		double convert = ((double) (hits + walks + hitByPitch)) / (total);
		return convert;
	}
}

string Player::toString() const {
	ostringstream out;
	//Using this switch to quickly print out all of the values since we know the order
	int totalOuts = strikeOuts + outs;
	out << name + "\t";
	out << (hits + totalOuts) << "\t";

	for (int k = 1; k < 7; k++) {
		switch (k) {
		//Determine which extension label should be used
		case 1:
			out << hits << "\t";
			break;
		case 2:
			out << walks << "\t";
			break;
		case 3:
			out << strikeOuts << "\t";
			break;
		case 4:
			out << hitByPitch << "\t";
			break;
		case 5:
			out << sacrifices << "\t";
		default:
			break;
		}
	}


	out << fixed << setprecision(3) << getBattingAverage() << "\t";
	out << fixed << setprecision(3) << getOnBasePercentage();
	out << "\n";

	return out.str();
}


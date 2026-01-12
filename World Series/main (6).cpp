//Harshitha Mahesh
//dal267662

#include <iostream>
#include <fstream>
#include <sstream>
#include <unordered_map>
#include <vector>
#include <algorithm>
#include <iomanip>
#include "Player.h"
using namespace std;


//All functions needed:
//set up the key map
unordered_map<string, string> setUpKeys(const string& filename);

//add all the players
void processPlay(const string& line, const unordered_map<string, string>& keyMap,
                 unordered_map<string, Player>& homePlayers,
                 unordered_map<string, Player>& awayPlayers);

//display the team stats
void displayTeamStats(const unordered_map<string, Player>& teamMap, const string& teamName);

//Find the top three places
vector<pair<double, vector<string>>> topThreePlayers(const vector<Player>& players,
        const string& category);

//comparator to break ties
bool comparePlayers(const pair<double, string>& left, const pair<double, string>& right);

//display the league leaders
void displayLeagueLeaders(const vector<Player>& allPlayers);

int main()
{
	string playByPlay;
	cin >> playByPlay;

	ifstream input;

	//check if file is valid
	input.open(playByPlay);
	if (!input)
	{
		cout << "Not found";
		return 1;
	}

	//set up the keys
	unordered_map<string, string> keys = setUpKeys("keyfile.txt");

	//set up the players and split them into home versus away
	unordered_map<string, Player> homePlayers, awayPlayers;
	string temp;

	while(getline(input, temp)) {
		if (!temp.empty()) {
			processPlay(temp, keys, homePlayers, awayPlayers);
		}
	}

	//Show all the teams statistics
	displayTeamStats(awayPlayers, "AWAY");
	displayTeamStats(homePlayers, "HOME");

	//Combine all the players into one vector so that it's easier to organize
	vector<Player> allPlayers;
	for (auto& entry : homePlayers) {
		const string& n = entry.first;
		const Player& p = entry.second;
		allPlayers.push_back(p);
	}

	for (auto& entry : awayPlayers) {
		const string& n = entry.first;
		const Player& p = entry.second;
		allPlayers.push_back(p);
	}

	//Print out the league leaders
	displayLeagueLeaders(allPlayers);

	input.close();
	return 0;
}

//this gets rid of white spaces and extra characters
static inline string trim(const string& s) {
	size_t start = s.find_first_not_of(" \t\r\n");
	if (start == string::npos) {
		return "";
	}
	size_t end = s.find_last_not_of(" \t\r\n");
	return s.substr(start, end - start + 1);
}

//Load the keyfile into a hash map
unordered_map<string, string> setUpKeys(const string& filename) {
	//holds the values
	unordered_map<string, string> results;

	ifstream file(filename);
	string line, currentCategory;

	//go through the key file line by line
	while (getline(file, line)) {
		string data = trim(line);
		if (data.empty()) {
			continue;
		}
		//find the category
		if (data.rfind("##", 0) == 0) {

			//remove leading ##
			string category = data.substr(2);

			//remove the trailing ##
			size_t pos = category.find("#");
			if (pos != string::npos) {
				category = category.substr(0, pos);
			}
			currentCategory = trim(category);


		} else {
			//adds the information
			//put these codes in that category
			string data = trim(line);
			results[data] = currentCategory;
		}
	}
	return results;
}


//updates the player's stats
void processPlay(const string& line, const unordered_map<string, string>& keyMap,
                 unordered_map<string, Player>& homePlayers,
                 unordered_map<string, Player>& awayPlayers) {

	//Break the line up into sections that can be coded
	stringstream ss(line);
	string team, name, playCode;
	ss >> team;
	ss >> name;
	ss >> playCode;


	//find the category using the keyMap
	auto val = keyMap.find(playCode);

	//if the key isn't present
	if (val == keyMap.end()) {
		cerr << "Warning: Unknown play code '" << playCode << "' for player " << name << "not equal to" << endl;
		return;
	}
	string result = val->second;



	//Update the certain team's player map
	if (team == "H") {
		//If player not in homePlayers create a new Player object
		if (homePlayers.find(name) == homePlayers.end()) {
			homePlayers[name] = Player(name);
			homePlayers[name].team = "HOME";
		}

		//Update stats for this player
		homePlayers[name].trackData(name, result);
	} else {
		//If player not in awayPlayers create a new Player object
		if (awayPlayers.find(name) == awayPlayers.end()) {
			awayPlayers[name] = Player(name);
			awayPlayers[name].team = "AWAY";
		}

		//Update stats for this player
		awayPlayers[name].trackData(name, result);
	}
}

//display the team names in alphabetical order
void displayTeamStats(const unordered_map<string, Player>& teamMap, const string& teamName) {
	vector<string> names;
	cout << teamName << endl;
	//add the player names from the hashmpa to the vector
	for (const auto& entry : teamMap) {
		names.push_back(entry.first);
	}

	//organize the names alphabetically
	sort(names.begin(), names.end());

	//displays all the info for each team member
	for (const auto& n : names) {
		cout << teamMap.at(n).toString();
	}
	//blank line after printing out the team
	cout << endl;

}

//Comparator function for sorting both numerical and alphabetical
bool comparePlayers(const pair<double, string>& left, const pair<double, string>& right, const vector<Player>& allPlayers) {
	//First compare the stat values
	if (left.first > right.first) {
		return true;
	}
	if (left.first < right.first) {
		return false;
	}

	//If values are equal, check team (away before home)
	const Player* p1 = nullptr;
	const Player* p2 = nullptr;

	//iterate through the players to find the player
	for (const auto& p: allPlayers) {
		if (p.getName() == left.second) {
			p1 = &p;
		}
		if (p.getName() == right.second) {
			p2 = &p;
		}
	}

	//check the teams
	int flagL = (p1 && p1->team == "AWAY") ? 0 : 1;
	int flagR = (p2 && p2->team == "AWAY") ? 0 : 1;

	if (flagL != flagR) {
		return flagL < flagR;
	}

	//If on the same team and equal, compare names alphabetically
	return left.second < right.second;
}

vector<pair<double, vector<string>>> topThreePlayers(const vector<Player>& players,
        const string& category)
{
	//Collect all the values for each category
	vector<pair<double, string>> values;
	for (const auto& p : players) {
		double val = 0.0;
		if (category == "BA") {
			val = p.getBattingAverage();
		} else if (category == "OBP") {
			val = p.getOnBasePercentage();
		} else if (category == "HITS") {
			val = p.hits;
		} else if (category == "WALKS") {
			val = p.walks;
		} else if (category == "K") {
			val = p.strikeOuts;
		} else if (category == "HBP") {
			val = p.hitByPitch;
		}

		values.push_back({val, p.getName()});
	}

	//Sort differently depending on category
	if (category == "K") {
		//Fewer strikeouts is better
		sort(values.begin(), values.end(),
		    [&](const pair<double,string>& a, const pair<double,string>& b) {
			    if (a.first != b.first) { 
			      //ascending
			      return a.first < b.first; 
			    }
			    return comparePlayers(a, b, players);
		    });
	} else {
		//All other categories: higher is better
		//Sort by value first and then alphabetically
		sort(values.begin(), values.end(),
		    [&](const pair<double,string>& a, const pair<double,string>& b) {
			    return comparePlayers(a, b, players);
		    });
	}



	//Group into top three places
	vector<pair<double, vector<string>>> topThree;
	int namesDisplaced = 0;

	for (size_t i = 0; i < values.size(); i++) {
		double val = values[i].first;
		string name = values[i].second;

		if (topThree.empty() || topThree.back().first != val) {
			//New stat value which means new place
			//already have 3 places
			if (namesDisplaced >= 3) {
				break;
			}
			vector<string> names;
			names.push_back(name);
			//add to the vector
			topThree.push_back(make_pair(val, names));
		} else {
			//Same stat value so add to existing place
			topThree.back().second.push_back(name);
		}
		namesDisplaced++;
	}

	return topThree;
}

void displayLeagueLeaders(const vector<Player>& allPlayers) {
	cout << "LEAGUE LEADERS" << endl;

	//Define categories with labels
	vector<pair<string,string>> categories = {
		{"BATTING AVERAGE", "BA"},
		{"ON-BASE PERCENTAGE", "OBP"},
		{"HITS", "HITS"},
		{"WALKS", "WALKS"},
		{"STRIKEOUTS", "K"},
		{"HIT BY PITCH", "HBP"}
	};

	//Loop through each category
	for (size_t i = 0; i < categories.size(); i++) {
		string name = categories[i].first;
		string key = categories[i].second;

		cout << name << endl;

		//Get top three leaders for this category
		//call topThreePlayers for this information
		vector<pair<double, vector<string>>> leaders = topThreePlayers(allPlayers, key);

		//Print each leader line
		for (size_t j = 0; j < leaders.size(); j++) {
			//grab the value
			double statValue = leaders[j].first;
			//grab the leaders
			const vector<string>& names = leaders[j].second;

			//make sure all the values have three decimals
			if (key == "BA" || key == "OBP") {
				cout << fixed << setprecision(3) << statValue << "\t";
			}
			else {
				cout << static_cast<int>(statValue) << "\t";
			}


			for (size_t k = 0; k < names.size(); k++) {
				//if there's more than one leader in one place
				if (k > 0) {
					cout << ", ";
				}
				//prints out name
				cout << names[k];
			}
			cout << endl;
		}
		//prints a blank line after each category
		cout << endl;
	}


}





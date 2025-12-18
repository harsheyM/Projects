//Harshitha Mahesh
//dal267662


#include <iostream>
#include <fstream>
#include <format>
#include <sstream>
#include <iomanip>
#include "Player.h"
#include "LinkList.h"
#include <vector>
#include <algorithm>
using namespace std;

string leaders(LinkList<Player>& list);

int main()
{
	string fileName;
	cin >> fileName;

	ifstream input;
	//check if file is valid
	input.open(fileName);
	if (!input)
	{
		return 1;
	}

	LinkList<Player> list;
	string temp;

	while(getline(input, temp)) {
		Player playerT;
		int index = temp.find(' ');
		playerT.trackData(temp.substr(0, index), temp.substr(index + 1));
		if (list.search(playerT)) {
			Player equal = list.remove(playerT);
			playerT = equal + playerT;
		}
		list.insert(playerT);
	}

	list.bubbleSort();
	cout << list.toString();
	cout << leaders(list);
	input.close();
	return 0;
}

string leaders(LinkList<Player>& list) {
	//Batting Average
	//On-Base Percentage
	//Hits
	//Walks
	//Strikeouts (least)
	//Hit By Pitch

	vector<Player> myVector = list.listToArray();

	string leaders = "LEAGUE LEADERS\n";

	//count the iterations
	int iter = 1;
	while(iter <= 6) {
		switch (iter) {
		case 1:
		{
			string leader = "\nBATTING AVERAGE \n";
			int i = 0;
			int names1 = 0;
			int names2 = 0;
			int names3 = 0;

			// Sort by numbers in ascending order
            //if the numbers are the same (in the same place) - organize alphabetically
			sort(myVector.begin(), myVector.end(), [](const Player& a, const Player& b) {
                if (a.getBattingAverage() == b.getBattingAverage()){
                    return a.getName() < b.getName();
                }
				return a.getBattingAverage() > b.getBattingAverage();
			});
            //these set up values for comparisn for the different places: this is continued for each category

            //Need to have long cases for each one because the function name is different for each loop
			double max = myVector[0].getBattingAverage();
			double secondMax = myVector[1].getBattingAverage();
            double thirdMax = myVector[2].getBattingAverage();
			if (max == secondMax) {
			    secondMax = myVector[2].getBattingAverage();
                thirdMax = myVector[3].getBattingAverage();
			}
            if (secondMax == thirdMax) {
                thirdMax = myVector[4].getBattingAverage();
            }
            

			//This loop runs unless the number of first place winners is 3 
            //or both first place and second place add up to three 
            //or all three places are filled
			while(names1 < 3 && names2 < 3) {
				if (i >= myVector.size()) {
					break;
				}
				else {
                    ostringstream battingAverage;
                    battingAverage << fixed << setprecision(3) << myVector[i].getBattingAverage();
					if (names1 > 0 && max == myVector[i].getBattingAverage()) {
						//To check if comma is needed
							leader += ", " + myVector[i].getName();
							++names1;
					}
					else if (max == myVector[i].getBattingAverage()) {
					    leader += battingAverage.str() + " " + myVector[i].getName();
						++names1;
					}
					else if (secondMax == myVector[i].getBattingAverage()) {
						if (names2 > 0) {
							leader += ", " + myVector[i].getName();
							++names2;
						}
						else {
                            leader += "\n" + battingAverage.str() + " " + myVector[i].getName();
							++names2;
						}

					}
					else if (thirdMax == myVector[i].getBattingAverage()) {
                        if (names1 + names2 >= 3) {
                            break;
                        }
						if (names3 > 0) {
							leader += ", " + myVector[i].getName();
							++names3;
						}
						else {
							leader +=  "\n" + battingAverage.str() + " " + myVector[i].getName();
							++names3;
						}
					}
				}
                i++;
			}
            //These if statements are if there is a tie of more than three players
			if (myVector.size() > 3 && myVector[3].getBattingAverage() == max) {
			    int count = 3;
			    while(myVector[count].getBattingAverage() == max) {
                    ostringstream battingAverage;
                    battingAverage << fixed << setprecision(3) << myVector[i].getBattingAverage();
			        leader += ", "+ myVector[i].getName();
			        ++count;
			    }
			}
            if (myVector.size() > 3 && myVector[i].getBattingAverage() == secondMax) {
			    int count = i;
			    while(myVector[count].getBattingAverage() == secondMax) {
                    ostringstream battingAverage;
                    battingAverage << fixed << setprecision(3) << myVector[i].getBattingAverage();
			        leader += ", "+ myVector[i].getName();
			        ++count;
			    }
			}
			leaders += leader + "\n";
			break;
		}
		case 2:
		{
			string leader = "\nON-BASE PERCENTAGE\n";
			int i = 0;
			int names1 = 0;
			int names2 = 0;
			int names3 = 0;

			// Sort by numbers in ascending order
			sort(myVector.begin(), myVector.end(), [](const Player& a, const Player& b) {
                if (a.getOnBasePercentage() == b.getOnBasePercentage()){
                    return a.getName() < b.getName();
                }
				return a.getOnBasePercentage() > b.getOnBasePercentage();
			});
			double max = myVector[0].getOnBasePercentage();
			double secondMax = myVector[1].getOnBasePercentage();
            double thirdMax = myVector[2].getOnBasePercentage();
			if (max == secondMax) {
			    secondMax = myVector[2].getOnBasePercentage();
                thirdMax = myVector[3].getOnBasePercentage();
			}
            if (secondMax == thirdMax) {
                thirdMax = myVector[4].getOnBasePercentage();
            }
			//This loop only runs three times: for three names
			while(names1 != 3 && names2 != 3) {
				if (i >= myVector.size()) {
					break;
				}
				else {
                    ostringstream onBasePercentage;
                    onBasePercentage << fixed << setprecision(3) << myVector[i].getOnBasePercentage();
					if (names1 > 0 && max == myVector[i].getOnBasePercentage()) {
						//To check if comma is needed
							leader += ", " + myVector[i].getName();
							++names1;
					}
					else if (max == myVector[i].getOnBasePercentage()) {
					    leader += onBasePercentage.str() + " " + myVector[i].getName();
						++names1;
					}
					else if (secondMax == myVector[i].getOnBasePercentage()) {
						if (names2 > 0) {
							leader += ", " + myVector[i].getName();
							++names2;
						}
						else {
							leader +=  "\n" + onBasePercentage.str() + " " + myVector[i].getName();
							++names2;
						}

					}
					else if (thirdMax == myVector[i].getOnBasePercentage()) {
                        if (names1 + names2 >= 3) {
                            break;
                        }
						if (names3 > 0) {
							leader += ", " + myVector[i].getName();
							++names3;
						}
						else {
							leader +=  "\n" + onBasePercentage.str() + " " + myVector[i].getName();
							++names3;
						}
					}
				}
                ++i;
			}
			if (myVector.size() > 3 && myVector[3].getOnBasePercentage() == max) {
			    int count = 3;
			    while(myVector[count].getOnBasePercentage() == max) {
                    ostringstream onBasePercentage;
                    onBasePercentage << fixed << setprecision(3) << myVector[i].getOnBasePercentage();
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
            if (myVector.size() > 3 && myVector[i].getOnBasePercentage() == secondMax) {
			    int count = i;
			    while(myVector[count].getOnBasePercentage() == secondMax) {
                    ostringstream onBasePercentage;
                    onBasePercentage << fixed << setprecision(3) << myVector[i].getOnBasePercentage();
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
			leaders += leader + "\n";
			break;
		}
		case 3:
		{
			string leader = "\nHITS \n";
			int i = 0;
			int names1 = 0;
			int names2 = 0;
			int names3 = 0;

			// Sort by numbers in ascending order
			sort(myVector.begin(), myVector.end(), [](const Player& a, const Player& b) {
                if (a.hits == b.hits){
                    return a.getName() < b.getName();
                }
				return a.hits > b.hits;
			});
			int max = myVector[0].hits;
			int secondMax = myVector[1].hits;
            int thirdMax = myVector[2].hits;
			if (max == secondMax) {
			    secondMax = myVector[2].hits;
                thirdMax = myVector[3].hits;
			}
            if (secondMax == thirdMax) {
                thirdMax = myVector[4].hits;
            }
			while(names1 != 3 && names2 != 3) {
				if (i >= myVector.size()) {
					break;
				}
				else {
					if (names1 > 0 && max == myVector[i].hits) {
						//To check if comma is needed
							leader += ", " + myVector[i].getName();
							++names1;
					}
					else if (max == myVector[i].hits) {
					    leader += to_string(myVector[i].hits) + " " + myVector[i].getName();
						++names1;
					}
					else if (secondMax == myVector[i].hits){
						if (names2 > 0) {
							leader += ", " + myVector[i].getName();
							++names2;
						}
						else {
							leader +=  "\n" + to_string(myVector[i].hits) + " " + myVector[i].getName();
							++names2;
						}

					}
					else if (thirdMax == myVector[i].hits) {
                        if (names1 + names2 >= 3) {
                            break;
                        }
						if (names3 > 0) {
							leader += ", " + myVector[i].getName();
							++names3;
						}
						else {
							leader +=  "\n" + to_string(myVector[i].hits) + " " + myVector[i].getName();
							++names3;
						}
					}
				}
                ++i;
			}
			if (myVector.size() > 3 && myVector[3].hits == max) {
			    int count = 3;
			    while(myVector[count].hits == max) {
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
            if (myVector.size() > 3 && myVector[i].hits == secondMax) {
			    int count = i;
			    while(myVector[count].hits == secondMax) {
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
			leaders += leader + "\n";
			break;
		}
		case 4:
		{
			string leader = "\nWALKS\n";
			int i = 0;
			int names1 = 0;
			int names2 = 0;
			int names3 = 0;

			// Sort by numbers in ascending order
			sort(myVector.begin(), myVector.end(), [](const Player& a, const Player& b) {
                if (a.walks == b.walks){
                    return a.getName() < b.getName();
                }
				return a.walks > b.walks;
			});
			double max = myVector[0].walks;
			double secondMax = myVector[1].walks;
            double thirdMax = myVector[2].walks;
			if (max == secondMax) {
			    secondMax = myVector[2].walks;
                thirdMax = myVector[3].walks;
			}
            if (secondMax == thirdMax) {
                thirdMax = myVector[4].walks;
            }
			while(names1 != 3 && names2 != 3) {
				if (i >= myVector.size()) {
					break;
				}
				else {
					if (names1 > 0 && max == myVector[i].walks) {
						//To check if comma is needed
							leader += ", " + myVector[i].getName();
							++names1;
					}
					else if (max == myVector[i].walks) {
					    leader += to_string(myVector[i].walks) + " " + myVector[i].getName();
						++names1;
					}
					else if (secondMax == myVector[i].walks) {
						if (names2 > 0) {
							leader += ", " + myVector[i].getName();
							++names2;
						}
						else {
							leader +=  "\n" + to_string(myVector[i].walks) + " " + myVector[i].getName();
							++names2;
						}

					}
					else if (thirdMax == myVector[i].walks) {
                        if (names1 + names2 >= 3) {
                            break;
                        }
						if (names3 > 0) {
							leader += ", " + myVector[i].getName();
							++names3;
						}
						else {
							leader +=  "\n" + to_string(myVector[i].walks) + " " + myVector[i].getName();
							++names3;
						}
					}
				}
                ++i;
			}
			if (myVector.size() > 3 && myVector[3].walks == max) {
			    int count = 3;
			    while(myVector[count].walks == max) {
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
            if (myVector.size() > 3 && myVector[i].walks == secondMax) {
			    int count = i;
			    while(myVector[count].walks == secondMax) {
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
			leaders += leader + "\n";
			break;
		}
        //This case is different because instead of greater its less than
		case 5:
		{
			string leader = "\nSTRIKEOUTS\n";
			int i = 0;
			int names1 = 0;
			int names2 = 0;
			int names3 = 0;

			// Sort by numbers in ascending order
			sort(myVector.begin(), myVector.end(), [](const Player& a, const Player& b) {
                if (a.strikeOuts == b.strikeOuts){
                    return a.getName() < b.getName();
                }
				return a.strikeOuts < b.strikeOuts;
			});
			double max = myVector[0].strikeOuts;
			double secondMax = myVector[1].strikeOuts;
            double thirdMax = myVector[2].strikeOuts;
			if (max == secondMax) {
			    secondMax = myVector[2].strikeOuts;
                thirdMax = myVector[3].strikeOuts;
			}
            if (secondMax == thirdMax) {
                thirdMax = myVector[4].strikeOuts;
            }
			
			while(names1 != 3 && names2 != 3) {
				if (i >= myVector.size()) {
					break;
				}
				else {
					if (names1 > 0 && max == myVector[i].strikeOuts) {
						//To check if comma is needed
							leader += ", " + myVector[i].getName();
							++names1;
					}
					else if (max == myVector[i].strikeOuts) {
					    leader += to_string(myVector[i].strikeOuts) + " " + myVector[i].getName();
						++names1;
					}
					else if (secondMax == myVector[i].strikeOuts) {
						if (names2 > 0) {
							leader += ", " + myVector[i].getName();
							++names2;
						}
						else {
							leader +=  "\n" + to_string(myVector[i].strikeOuts) + " " + myVector[i].getName();
							++names2;
						}

					}
					else if (thirdMax == myVector[i].strikeOuts) {
                        if (names1 + names2 >= 3) {
                            break;
                        }
						if (names3 > 0) {
							leader += ", " + myVector[i].getName();
							++names3;
						}
						else {
							leader +=  "\n" + to_string(myVector[i].strikeOuts) + " " + myVector[i].getName();
							++names3;
						}
					}
				}
                ++i;
			}
			if (myVector.size() > 3 && myVector[3].strikeOuts == max) {
			    int count = 3;
			    while(myVector[count].strikeOuts == max) {
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
            if (myVector.size() > 3 && myVector[i].strikeOuts == secondMax) {
			    int count = i;
			    while(myVector[count].strikeOuts == secondMax) {
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
			leaders += leader + "\n";
			break;
		}
		case 6:
		{
			string leader = "\nHIT BY PITCH\n";
			int i = 0;
			int names1 = 0;
			int names2 = 0;
			int names3 = 0;

			// Sort by numbers in ascending order
			sort(myVector.begin(), myVector.end(), [](const Player& a, const Player& b) {
                if (a.hitByPitch == b.hitByPitch){
                    return a.getName() < b.getName();
                }
				return a.hitByPitch > b.hitByPitch;
			});
			double max = myVector[0].hitByPitch;
			double secondMax = myVector[1].hitByPitch;
            double thirdMax = myVector[2].hitByPitch;
			if (max == secondMax) {
			    secondMax = myVector[2].hitByPitch;
                thirdMax = myVector[3].hitByPitch;
			}
            if (secondMax == thirdMax) {
                thirdMax = myVector[4].hitByPitch;
            }
			while(names1 != 3 && names2 != 3) {
				if (i >= myVector.size()) {
					break;
				}
				else {
					if (names1 > 0 && max == myVector[i].hitByPitch) {
						//To check if comma is needed
							leader += ", " + myVector[i].getName();
							++names1;
					}
					else if (max == myVector[i].hitByPitch) {
					    leader += to_string(myVector[i].hitByPitch) + " " + myVector[i].getName();
						++names1;
					}
					else if (secondMax == myVector[i].hitByPitch) {
						if (names2 > 0) {
							leader += ", " + myVector[i].getName();
							++names2;
						}
						else {
							leader +=  "\n" + to_string(myVector[i].hitByPitch) + " " + myVector[i].getName();
							++names2;
						}

					}
					else if (thirdMax == myVector[i].hitByPitch) {
                        if (names1 + names2 >= 3) {
                            break;
                        }
						if (names3 > 0) {
							leader += ", " + myVector[i].getName();
							++names3;
						}
						else {
							leader +=  "\n" + to_string(myVector[i].hitByPitch) + " " + myVector[i].getName();
							++names3;
						}
					}
				}
                ++i;
			}
			if (myVector.size() > 3 && myVector[3].hitByPitch == max) {
			    int count = 3;
			    while(myVector[count].hitByPitch == max) {
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
            if (myVector.size() > 3 && myVector[i].hitByPitch == secondMax) {
			    int count = i;
			    while(myVector[count].hitByPitch == secondMax) {
			        leader += ", " + myVector[i].getName();
			        ++count;
			    }
			}
			leaders += leader + "\n";
			break;
		}
		}
		++iter;

	}
	return leaders;
}

//Harshitha Mahesh
//dal267662

#include <string>
#ifndef BEETLE_H
#define BEETLE_H
#include "Creature.h"

//Beetle class is a derived class derived from the base class - Creature
class Beetle : public Creature
{
public:
    //constructor and destructor
    
    //empty constructor
    Beetle() : Creature(){}

    //Overloaded constructor
    Beetle(bool h, bool b, int turn) : Creature(h, b, turn){}

    //empty destructor
    virtual ~Beetle();
    
    //Methods
    std::string move(int northDist, int eastDist, int southDist, int westDist);
    std::string breed(bool N, bool E, bool S, bool W);
    bool starve(int num);
    
    //returns number of turns since the creature last ate
    //ate method resets numTurnsSinceAte to 0
    int getNumTurnsSinceAte(){ return numTurnsSinceAte; }
    void didNotEat(){numTurnsSinceAte++;}
    void ate(){numTurnsSinceAte = 0;}

};
#endif
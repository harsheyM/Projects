//Harshitha Mahesh
//dal267662
#ifndef CREATURE_H
#define CREATURE_H
#include <string>

class Creature
{
protected:
    bool hasMoved = false;
    bool hasBred = false;
    int numTurnsSinceAte = 0;

public:
    //Constructor
    Creature(){} //empty constructor
    Creature(bool h, bool b, int turn) : hasMoved(h), hasBred(b), numTurnsSinceAte(turn) {}
    
    virtual ~Creature(){} //empty destructor
    
    //Methods

    //pure virtual move function which will be overridden in Ant and Beetle
    virtual std::string move(int,int,int,int) = 0;
    //pure virtual breed function which will be overridden in Ant and Beetle
    virtual std::string breed(bool, bool, bool, bool) = 0;

    
    //sets hasMoved to true, indicating that the creature has moved in the turn
    void moved(){hasMoved = true;}
    //sets hasBred to true, indicating that the creature has bred in the turn
    void bred(){hasBred = true;}
    //resetMovedAndBred method sets hasMoved and hasBred to false
    void resetMovedAndBred()
    {
        hasMoved = false;
        hasBred = false;
    };

    
    //returns hasMoved - whether or not the creature has moved in the turn
    bool hasMovedOnce(){return hasMoved;}
    
    //returns hasBred - whether or not the creature has bred in the turn
    bool hasBredOnce(){return hasBred;}
    

    
};
#endif
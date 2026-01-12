//Harshitha Mahesh
//dal267662

#include <string>
#ifndef ANT_H
#define ANT_H
#include "Creature.h"

class Ant : public Creature
{
public:
    
    //Constructor
    Ant() : Creature(){}
    Ant(bool h, bool b, int turn) : Creature(h, b, turn){}


    //empty destructor
    virtual ~Ant() {}
    
    //Methods
    std::string move(int northDist, int eastDist, int southDist, int westDist);
    std::string breed(bool, bool, bool, bool);
};
#endif